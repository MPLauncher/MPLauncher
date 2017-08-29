/*
   Copyright 2017 MPLauncher Team

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.mplauncher.launcher.controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    @FXML
    private StackPane mainMenu;

    @FXML
    private Circle userAvatar;

    @FXML
    private Label userName;

    @FXML
    private Circle userOnline;

    @FXML
    private Text menuButtonIconLEFT;

    @FXML
    private Text menuButtonIconRIGHT;

    @FXML
    private ImageView discordLogo;

    @FXML
    private JFXRippler menuButton;

    @FXML
    private JFXListView menuList;

    @FXML
    private Label menuListIcon;

    @FXML
    private Label menuListText;

    @FXML
    private Text newsTitle;

    @FXML
    private Label newsArticle;

    @FXML
    private Text newsAuthor;

    @FXML
    private Text newsTime;

    @FXML
    private Pane mainTop;

    private static double xOffset;
    private static double yOffset;

    @FXML
    public void initialize() throws Exception {

        //Set USERNAME
        Image img = new Image(getClass().getClassLoader().getResource("gaben.jpg").toString());
        userAvatar.setFill(new ImagePattern(img));
        userName.setText("Łowca wiaderek :kappa:");
        userOnline.getStyleClass().setAll("userOnline_GREEN");

        JFXHelpers.fadeTransition(Duration.millis(250), menuButtonIconLEFT, 0.0, 1.0);

        Image img2 = new Image(getClass().getClassLoader().getResource("DiscordLogo.png").toString());
        discordLogo.setImage(img2);

        //Set NEWS*
        newsTitle.setText("NOWY WYGLĄD?");
        newsArticle.setText("Witajcie gracze i graczki!" + System.lineSeparator() + System.lineSeparator() +
                "Jako, iż nasza ekipa robi wszystko ze starannością i dbałością dla was, postanowiłem rozpocząć tworzenie nowego stylu launchera!" + System.lineSeparator() +
                "Styl przybrał nazwę &quot;Callipso&quot; i prawdopodobnie do 15-30 dni uda mi się stworzyć jego layout." + System.lineSeparator() +
                "Na obecną chwilę mogę napisać, iż szykuje się pare dodatków w nowym wyglądzie, całkowita zmiana stylu oraz pełno eastereggów." + System.lineSeparator() + System.lineSeparator() +
                "Czytaj więcej.");
        newsAuthor.setText("IceMeltt");
        newsTime.setText("~miesiąc temu");

        menuList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Node nodeIn : ((GridPane)newValue).getChildren()) {
                if (nodeIn instanceof Label) {
                    System.out.println("New Selection -> ID: " + menuList.getSelectionModel().getSelectedIndex() + " == " + ((Label) nodeIn).getText());
                }
            }
        });

        // Allow to drag entire app via namePane
        mainTop.setOnMousePressed(event -> {
            xOffset = MPLauncherBootstrap.start_stage.getX() - event.getScreenX();
            yOffset = MPLauncherBootstrap.start_stage.getY() - event.getScreenY();
        });
        mainTop.setOnMouseDragged(event -> {
            MPLauncherBootstrap.start_stage.setX(event.getScreenX() + xOffset);
            MPLauncherBootstrap.start_stage.setY(event.getScreenY() + yOffset);
        });
    }

    @FXML
    private void closeClicked() {
        Platform.exit();
    }

    @FXML
    private void discordLogoClicked() throws URISyntaxException {
        JFXHelpers.openWebpage(new URI("https://discord.gg/C5pkDan"));
    }

    @FXML
    private void menuButtonClicked() {
        menuButton.setDisable(true);

        if (menuListText.getOpacity() == 1.0) {
            JFXHelpers.fadeTransition(Duration.millis(125), userName, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(125), menuListText, 1.0, 0.0, (ActionEvent) -> {
                menuListText.setMinWidth(0.0);

                Timeline animations = new Timeline();
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuButtonIconLEFT.opacityProperty(), 0.0)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuButtonIconRIGHT.opacityProperty(), 1.0)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userAvatar.radiusProperty(), 16)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.radiusProperty(), 2)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.translateXProperty(), 11.9)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.translateYProperty(), 3.6)));

                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuListIcon.minWidthProperty(), 100.0)));

                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(mainMenu.prefWidthProperty(), 91)));
                animations.setOnFinished(event -> menuButton.setDisable(false));
                animations.play();
            });
        } else if (menuListText.getOpacity() == 0.0) {
            Timeline animations = new Timeline();
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuButtonIconLEFT.opacityProperty(), 1.0)));
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuButtonIconRIGHT.opacityProperty(), 0.0)));
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userAvatar.radiusProperty(), 41)));
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.radiusProperty(), 6)));
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.translateXProperty(), 30.0)));
            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(userOnline.translateYProperty(), 9.0)));

            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(menuListIcon.minWidthProperty(), 30.0)));

            animations.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(mainMenu.prefWidthProperty(), 220)));
            animations.setOnFinished((ActionEvent) -> {
                menuListText.setMinWidth(70.0);
                JFXHelpers.fadeTransition(Duration.millis(125), userName, 0.0, 1.0);
                JFXHelpers.fadeTransition(Duration.millis(125), menuListText, 0.0, 1.0, event -> menuButton.setDisable(false));
            });
            animations.play();
        }
    }
}
