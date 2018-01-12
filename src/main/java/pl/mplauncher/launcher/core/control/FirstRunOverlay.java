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
package pl.mplauncher.launcher.core.control;

import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.core.config.ConfigUtils;
import pl.mplauncher.launcher.core.helper.JFXHelpers;

import java.io.File;
import java.net.URL;

public class FirstRunOverlay extends Stage {

    private static final Logger logger = LogManager.getLogger(FirstRunOverlay.class);

    public enum InstallationType {
        Classic,
        OwnLocation,
        Portable
    }

    private JFXRadioButton classic;

    private JFXRadioButton ownLocation;
    private JFXTextField location;

    private JFXRadioButton portable;
    private JFXComboBox<String> driveList;

    public FirstRunOverlay() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        Scene scene = new Scene(dialogLayout);
        scene.setFill(Color.TRANSPARENT);

        URL style = Thread.currentThread().getContextClassLoader().getResource("styles/main.css");
        if (style != null) {
            scene.getStylesheets().add(style.toExternalForm());
        } else {
            logger.error("Couldn't load CSS style for the FirstRunOverlay form!");
        }

        this.getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo-cropped.png")));
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);

        dialogLayout.getStyleClass().add("firstRunContent");

        Text title = new Text();
        title.getStyleClass().addAll("fontSemiBold", "fontSize12");
        title.setText("Konfiguracja Launchera".toUpperCase());
        dialogLayout.setHeading(title);

        VBox vBox = new VBox();
        vBox.setMinHeight(160.0);
        dialogLayout.setBody(vBox);

        /* CLASSIC */
        HBox classicOptionContainer = new HBox();
        classicOptionContainer.setAlignment(Pos.CENTER_LEFT);
        classicOptionContainer.getStyleClass().add("radioHBox");

        classic = new JFXRadioButton();
        classic.setSelected(true);

        TextFlow textFlow = new TextFlow();
        Text line_1 = new Text();
        line_1.setText("KLASYCZNIE" + System.lineSeparator());
        line_1.getStyleClass().addAll("fontBold", "fontSize10");

        Text line_2 = new Text();
        line_2.setText("(instalacja w " + ConfigUtils.getDefaultDataLocation().getPath() + ")");
        line_2.getStyleClass().addAll("fontLight", "fontSize9", "dimmy");
        textFlow.getChildren().addAll(line_1, line_2);
        classicOptionContainer.getChildren().addAll(classic, textFlow);
        vBox.getChildren().add(classicOptionContainer);
        /* CLASSIC */

        /* OWNLOCATION */
        HBox ownLocationOptionContainer = new HBox();
        ownLocationOptionContainer.setAlignment(Pos.CENTER_LEFT);
        ownLocationOptionContainer.getStyleClass().add("radioHBox");

        ownLocation = new JFXRadioButton();

        TextFlow textFlow1 = new TextFlow();
        Text oL_line1 = new Text();
        oL_line1.setText("WŁASNA LOKALIZACJA (instalacja np. na innym dysku)" + System.lineSeparator());
        oL_line1.getStyleClass().addAll("fontBold", "fontSize10");

        Text oL_line2 = new Text();
        oL_line2.setText("(KLIKNIJ ABY ZMIENIĆ)");
        oL_line2.getStyleClass().addAll("fontLight", "fontSize9", "dimmy");
        textFlow1.getChildren().addAll(oL_line1, oL_line2);
        ownLocationOptionContainer.getChildren().addAll(ownLocation, textFlow1);

        vBox.getChildren().add(ownLocationOptionContainer);

        HBox ownLocationContent = new HBox();
        ownLocationContent.setPadding(new Insets(10.0, 0.0, 0.0, 40.0));
        ownLocationContent.setVisible(false);
        ownLocationContent.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().add(ownLocationContent);

        Text selectLocation = new Text();
        selectLocation.setText("Wybierz lokalizacje".toUpperCase());
        selectLocation.getStyleClass().addAll("fontRegular", "fontSize9");

        location = new JFXTextField();
        location.setText(ConfigUtils.getDefaultDataLocation().getParentFile().getAbsolutePath());
        location.getStyleClass().addAll("fontLight", "fontSize9");
        location.setOnMouseClicked(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz lokalizacje launchera");
            directoryChooser.setInitialDirectory(new File(location.getText()));
            File selectedLocation = directoryChooser.showDialog(this);
            if (selectedLocation != null) {
                location.setText(selectedLocation.getAbsolutePath());
            }
        });
        HBox.setMargin(location, new Insets(0, 0, 0, 13));
        ownLocationContent.getChildren().addAll(selectLocation, location);
        /* OWNLOCATION */

        /* PORTABLE */
        HBox portableOptionContainer = new HBox();
        portableOptionContainer.setAlignment(Pos.CENTER_LEFT);
        portableOptionContainer.getStyleClass().add("radioHBox");

        portable = new JFXRadioButton();

        TextFlow textFlow2 = new TextFlow();
        Text p_line1 = new Text();
        p_line1.setText("PRZENOŚNA (PORTABLE)" + System.lineSeparator());
        p_line1.getStyleClass().addAll("fontBold", "fontSize10");

        Text p_line2 = new Text();
        p_line2.setText("(INSTALACJA NA PENDRIVE, DZIAŁA NA KAŻDYM PC)");
        p_line2.getStyleClass().addAll("fontLight", "fontSize9", "dimmy");
        textFlow2.getChildren().addAll(p_line1, p_line2);
        portableOptionContainer.getChildren().addAll(portable, textFlow2);

        //Disabled because in DEV mode without Jar file it'll not work.
        if (MPLauncher.class.getPackage().getImplementationVersion() == null) {
            portable.setDisable(true);
        }

        vBox.getChildren().add(portableOptionContainer);

        HBox portableContent = new HBox();
        portableContent.setPadding(new Insets(10.0, 0.0, 10.0, 40.0));
        portableContent.setVisible(false);
        portableContent.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().add(portableContent);

        Text selectLocation1 = new Text();
        selectLocation1.setText("Wybierz lokalizacje".toUpperCase());
        selectLocation1.getStyleClass().addAll("fontRegular", "fontSize9");

        driveList = new JFXComboBox<>();
        driveList.setPromptText("Wybierz");
        driveList.getItems().addAll("D:/", "E:/", "G:/");
        driveList.getStyleClass().addAll("fontLight", "fontSize9");
        HBox.setMargin(driveList, new Insets(0, 0, 0, 13));
        portableContent.getChildren().addAll(selectLocation1, driveList);
        /* PORTABLE */

        Text warning = new Text();
        warning.setText("Uwaga!");
        warning.getStyleClass().addAll("fontRegular", "fontSize10");
        VBox.setMargin(warning, new Insets(0, 0, 0, 15));

        Text warningMessage = new Text();
        warningMessage.setText("Ustawienia prezentowane powyżej są permamentne. Dokonaj słusznego wyboru.");
        warningMessage.getStyleClass().addAll("fontRegular", "fontSize9");
        VBox.setMargin(warningMessage, new Insets(0, 0, 0, 15));

        vBox.getChildren().addAll(warning, warningMessage);

        HBox okButtons = new HBox();
        okButtons.setAlignment(Pos.CENTER_RIGHT);
        dialogLayout.setActions(okButtons);

        JFXButton ok = new JFXButton();
        ok.getStyleClass().addAll("playButton", "fontSemiBold", "fontSize10", "textFillWhite");
        ok.setPrefWidth(70.0);
        ok.setPrefHeight(30.0);
        ok.setButtonType(JFXButton.ButtonType.RAISED);
        ok.setOnAction(action -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 0.0, event -> this.close()));
        ok.setText("ZAPISZ");
        okButtons.getChildren().add(ok);

        ToggleGroup toggleGroup = new ToggleGroup();
        classic.setToggleGroup(toggleGroup);
        ownLocation.setToggleGroup(toggleGroup);
        portable.setToggleGroup(toggleGroup);

        ownLocationContent.visibleProperty().bind(ownLocation.selectedProperty());
        ownLocationContent.managedProperty().bind(ownLocation.selectedProperty());
        portableContent.visibleProperty().bind(portable.selectedProperty());
        portableContent.visibleProperty().bind(portable.selectedProperty());

        this.setAlwaysOnTop(true);
        this.centerOnScreen();
        this.setOpacity(0.0);
        this.setOnShown(event -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 1.0));
        this.showAndWait();
    }

    public InstallationType getResult() {
        if (classic.isSelected()) {
            return InstallationType.Classic;
        }

        if (ownLocation.isSelected()) {
            return InstallationType.OwnLocation;
        }

        if (portable.isSelected()) {
            return InstallationType.Portable;
        }

        return null;
    }

    public String getLocation() {
        if (ownLocation.isSelected()) {
            return location.getText();
        } else if (portable.isSelected()) {
            return driveList.getSelectionModel().getSelectedItem();
        } else {
            return ConfigUtils.getDefaultDataLocation().getAbsolutePath();
        }
    }
}
