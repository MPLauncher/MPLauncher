/*
 * Copyright 2017-2019 MPLauncher Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mplauncher.launcher.core.screen.layout;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.apache.commons.lang3.Validate;
import org.ocpsoft.prettytime.PrettyTime;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.api.mp.component.dto.News;
import pl.mplauncher.launcher.core.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.core.enums.ModpackType;
import pl.mplauncher.launcher.core.helper.JFXHelpers;
import pl.mplauncher.launcher.core.helper.Placeholder;
import pl.mplauncher.launcher.core.manager.Managers;
import pl.mplauncher.launcher.core.manager.ModpackManager;
import pl.mplauncher.launcher.core.screen.MainScreen;
import pl.mplauncher.launcher.core.screen.Screen;
import pl.mplauncher.launcher.core.screen.layout.component.*;

import java.net.URL;
import java.util.Date;

public class MainLayout extends Layout {

    private StackPane mainStackPane = new StackPane();
    public Label menuListIcon = new Label();
    public Label menuListText = new Label();
    private StackPane mainMenu = new StackPane();
    private Circle userAvatar = new Circle();
    private Circle userOnline = new Circle();
    private Label userName = new Label();
    public JFXListView<MenuItem> menuList = new JFXListView<>();
    private JFXRippler closeRippler = new JFXRippler();
    public GridPane centerGridPane = new GridPane();
    public ImageView discordLogo = new ImageView();
    private JFXRippler menuButton = new JFXRippler();
    private FontAwesomeIconView menuButtonIconLEFT = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_LEFT, "25px");
    private FontAwesomeIconView menuButtonIconRIGHT = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT, "25px");
    public JFXListView<ServerItem> favoriteServerList;
    public JFXListView<ServerItem> otherServerList;
    //Elements
    private StackPane firstSPinCenterGP = new StackPane();
    public Text rightCenterFirstText = new Text();
    private Text launcherVersion = new Text();
    private StackPane menuButtonSP = new StackPane();
    public ScrollPane serverListleftSite;

    //ModPack views
    public JFXListView<ModpackItem> vanillaModpackList;
    public JFXListView<ModpackItem> ftbModpackList;
    public JFXListView<ModpackItem> ownModpackList;
    public JFXListView<ModpackItem> kenpackModpackList;
    public JFXListView<ModpackItem> otherModpackList;

    public MainLayout(Screen screen) {
        super(screen);
    }

    //Initializer
    public void initialize() {
        VBox mainForm = new VBox();
        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneCenter = new BorderPane();
        StackPane firstSPTopGP = new StackPane();
        StackPane topBackground = new StackPane();
        StackPane mainTop = new StackPane();
        TextFlow topContent = new TextFlow();
        Text firstTextmainTopTF = new Text();
        Text secondTextmainTopTF = new Text();
        Circle menuButtonBackground = new Circle();

        mainForm.setMinSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        mainForm.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        mainForm.setPrefSize(1178.0, 722.0);

        URL style = Thread.currentThread().getContextClassLoader().getResource("styles/main.css");
        if (style != null) {
            mainForm.getStylesheets().add(style.toExternalForm());
        } else {
            screen.logger.error("Couldn't load CSS style for the MainScreen form!");
        }

        mainStackPane.setAlignment(Pos.TOP_LEFT);
        mainStackPane.getStyleClass().add("mainForm");

        menuListIcon.setVisible(false);
        menuListIcon.setMinWidth(30.0);
        menuListText.setVisible(false);
        menuListText.setMinWidth(70.0);

        borderPane.setMaxSize(BorderPane.USE_PREF_SIZE, BorderPane.USE_PREF_SIZE);
        borderPane.setPrefSize(1178.0, 722.0);

        mainMenu.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        mainMenu.getStyleClass().add("mainMenu");
        StackPane.setAlignment(mainMenu, Pos.TOP_LEFT);
        BorderPane.setAlignment(mainMenu, Pos.CENTER);

        userAvatar.setRadius(41.0);
        StackPane.setMargin(userAvatar, new Insets(21.0, 0.0, 0.0, 0.0));

        userOnline.setRadius(6.0);
        userOnline.setTranslateX(30.0);
        userOnline.setTranslateY(9.0);
        StackPane.setMargin(userOnline, new Insets(21.0, 0.0, 0.0, 0.0));

        userName.getStyleClass().add("userName");
        StackPane.setMargin(userName, new Insets(129.0, 0.0, 0.0, 0.0));

        menuList.setMaxHeight(JFXListView.USE_PREF_SIZE);
        menuList.setPrefHeight(499.0);
        menuList.getStyleClass().add("selectList");
        StackPane.setMargin(menuList, new Insets(169.0, 0.0, 0.0, 0.0));

        closeRippler.setMaxHeight(JFXRippler.USE_PREF_SIZE);
        closeRippler.setPrefHeight(55.0);
        closeRippler.setOnMouseClicked((handler) -> JFXHelpers.doublePropertyAnimation(Duration.millis(500),
                MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, action -> ((MainScreen) screen).closeClicked()));
        StackPane.setMargin(closeRippler, new Insets(667.0, 0.0, 0.0, 0.0));

        BorderPane.setAlignment(borderPaneCenter, Pos.CENTER);

        BorderPane.setAlignment(centerGridPane, Pos.CENTER);

        GridPane.setHalignment(firstSPinCenterGP, HPos.CENTER);
        GridPane.setValignment(firstSPinCenterGP, VPos.CENTER);

        BorderPane.setAlignment(firstSPTopGP, Pos.CENTER);

        topBackground.setAlignment(Pos.TOP_RIGHT);
        topBackground.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        topBackground.setPrefSize(200.0, 150.0);
        topBackground.getStyleClass().add("topBackground");
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.1);
        ca.setContrast(0.1);
        ca.setSaturation(-1.0);
        topBackground.setEffect(ca);
        StackPane.setAlignment(topBackground, Pos.TOP_RIGHT);

        mainTop.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        mainTop.setPrefSize(886.0, 150.0);
        mainTop.getStyleClass().add("mainTop");

        // Allow to drag entire app via namePane
        mainTop.setOnMousePressed(event -> {
            xOffset = MPLauncherBootstrap.getStartStage().getX() - event.getScreenX();
            yOffset = MPLauncherBootstrap.getStartStage().getY() - event.getScreenY();
        });
        mainTop.setOnMouseDragged(event -> {
            MPLauncherBootstrap.getStartStage().setX(event.getScreenX() + xOffset);
            MPLauncherBootstrap.getStartStage().setY(event.getScreenY() + yOffset);
        });
        StackPane.setAlignment(mainTop, Pos.TOP_RIGHT);

        launcherVersion.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        launcherVersion.setStrokeType(StrokeType.OUTSIDE);
        launcherVersion.setStrokeWidth(0.0);
        launcherVersion.getStyleClass().addAll("versionText", "textWhite");
        StackPane.setMargin(launcherVersion, new Insets(19.0, 18.0, 0.0, 0.0));

        topContent.setPrefSize(200.0, 200.0);
        topContent.getStyleClass().add("topContent");

        firstTextmainTopTF.setStrokeType(StrokeType.OUTSIDE);
        firstTextmainTopTF.setStrokeWidth(0.0);
        firstTextmainTopTF.setWrappingWidth(49.37950134277344);
        firstTextmainTopTF.getStyleClass().addAll("mplauncher", "textWhite", "textBold");
        firstTextmainTopTF.setText("MP");

        secondTextmainTopTF.setStrokeType(StrokeType.OUTSIDE);
        secondTextmainTopTF.setStrokeWidth(0.0);
        secondTextmainTopTF.getStyleClass().addAll("mplauncher", "textWhite");
        secondTextmainTopTF.setText("LAUNCHER");

        menuButtonSP.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        menuButtonSP.setPrefSize(50.0, 50.0);
        menuButtonSP.setTranslateY(498.0);
        StackPane.setMargin(menuButtonSP, new Insets(0.0, 0.0, 0.0, -25.0));

        menuButtonBackground.setRadius(25.0);
        menuButtonBackground.getStyleClass().add("menuButtonBackground");

        menuButton.setMaxSize(JFXRippler.USE_PREF_SIZE, JFXRippler.USE_PREF_SIZE);
        menuButton.setPrefSize(30.0, 30.0);
        menuButton.getStyleClass().add("menuButton");
        menuButton.setCursor(Cursor.HAND);
        menuButton.setOnMouseClicked((handler) -> {
            menuButton.setDisable(true);

            if (menuListText.getOpacity() == 1.0) {
                JFXHelpers.fadeTransition(Duration.millis(125), userName, 1.0, 0.0);
                JFXHelpers.fadeTransition(Duration.millis(125), menuListText, 1.0, 0.0, (ActionEvent) -> {
                    menuListText.setMinWidth(0.0);

                    Timeline animations = new Timeline();
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(menuButtonIconLEFT.opacityProperty(), 0.0)));
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(menuButtonIconRIGHT.opacityProperty(), 1.0)));
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(userAvatar.radiusProperty(), 16)));
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(userOnline.radiusProperty(), 2)));
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(userOnline.translateXProperty(), 11.9)));
                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(userOnline.translateYProperty(), 3.6)));

                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(menuListIcon.minWidthProperty(), 100.0)));

                    animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                            new KeyValue(mainMenu.prefWidthProperty(), 91)));
                    animations.setOnFinished(event -> menuButton.setDisable(false));

                    animations.play();
                });
            } else if (menuListText.getOpacity() == 0.0) {
                Timeline animations = new Timeline();
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(menuButtonIconLEFT.opacityProperty(), 1.0)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(menuButtonIconRIGHT.opacityProperty(), 0.0)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(userAvatar.radiusProperty(), 41)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(userOnline.radiusProperty(), 6)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(userOnline.translateXProperty(), 30.0)));
                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(userOnline.translateYProperty(), 9.0)));

                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(menuListIcon.minWidthProperty(), 30.0)));

                animations.getKeyFrames().add(new KeyFrame(Duration.millis(250),
                        new KeyValue(mainMenu.prefWidthProperty(), 220)));
                animations.setOnFinished((ActionEvent) -> {
                    menuListText.setMinWidth(70.0);
                    JFXHelpers.fadeTransition(Duration.millis(125), userName, 0.0, 1.0);
                    JFXHelpers.fadeTransition(Duration.millis(125), menuListText, 0.0, 1.0,
                            event -> menuButton.setDisable(false));
                });

                animations.play();
            }
        });

        menuButtonIconLEFT.setOpacity(0.0);
        menuButtonIconLEFT.setBoundsType(TextBoundsType.VISUAL);
        menuButtonIconLEFT.getStyleClass().add("menuButton");

        menuButtonIconRIGHT.setOpacity(0.0);
        menuButtonIconRIGHT.setBoundsType(TextBoundsType.VISUAL);
        menuButtonIconRIGHT.getStyleClass().add("menuButton");

        //Children
        mainForm.getChildren().add(mainStackPane);
        mainStackPane.getChildren().addAll(menuListIcon, menuListText, borderPane, menuButtonSP);
        borderPane.setLeft(mainMenu);
        borderPane.setCenter(borderPaneCenter);
        menuButtonSP.getChildren().addAll(menuButtonBackground, menuButton);
        mainMenu.getChildren().addAll(userAvatar, userOnline, userName, menuList, closeRippler);
        borderPaneCenter.setCenter(centerGridPane);
        borderPaneCenter.setTop(firstSPTopGP);
        firstSPTopGP.getChildren().addAll(topBackground, mainTop);
        mainTop.getChildren().addAll(launcherVersion, topContent);
        topContent.getChildren().addAll(firstTextmainTopTF, secondTextmainTopTF);
        menuButton.getChildren().addAll(menuButtonIconLEFT, menuButtonIconRIGHT);

        //Binding
        mainMenu.widthProperty().addListener(listener -> menuButtonSP.setTranslateX(mainMenu.getWidth()));

        //Parent
        this.scene = new Scene(mainForm, 1178, 722);
        this.scene.setFill(Color.TRANSPARENT);

        JFXHelpers.fadeTransition(Duration.millis(250), menuButtonIconLEFT, 0.0, 1.0);
    }

    public void setPlayWindow() {
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setPercentWidth(100.0);
        centerGridPane.getColumnConstraints().clear();
        centerGridPane.getColumnConstraints().add(firstCC);

        centerGridPane.getChildren().clear();
        centerGridPane.getChildren().add(new ModpackList(this));
        JFXHelpers.doublePropertyAnimation(Duration.millis(250), centerGridPane.opacityProperty(), 1.0);
    }

    public void setNews(News news) {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.setLocale(MessageBundle.getCurrentLanguage().getLocale());

        setNews(news.getTitle(), new Image(news.getImageURL()), news.getBody(), news.getAuthor(), prettyTime.format(new Date(news.getDate())));
    }

    public void setNews(String newsTitle, Image newsImage, String newsArticle, String newsAuthor, String newsTime) {
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setMinWidth(10.0);
        firstCC.setPercentWidth(75.0);
        ColumnConstraints secondCC = new ColumnConstraints();
        secondCC.setHgrow(Priority.SOMETIMES);
        secondCC.setMinWidth(10.0);
        secondCC.setPercentWidth(25.0);
        secondCC.setPrefWidth(100.0);
        centerGridPane.getColumnConstraints().clear();
        centerGridPane.getColumnConstraints().addAll(firstCC, secondCC);

        centerGridPane.getChildren().addAll(firstSPinCenterGP, new RightOfNews(this));
        this.firstSPinCenterGP.getChildren().clear();
        this.firstSPinCenterGP.getChildren().add(new StartNews(newsTitle, newsImage, newsArticle, newsAuthor, newsTime));
    }

    //NEWS
    public void setNewsList() {
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setPercentWidth(100.0);
        centerGridPane.getColumnConstraints().clear();
        centerGridPane.getColumnConstraints().add(firstCC);

        centerGridPane.getChildren().clear();
        centerGridPane.getChildren().add(new NewsList(this));
        JFXHelpers.doublePropertyAnimation(Duration.millis(250), centerGridPane.opacityProperty(), 1.0);

        Placeholder.populateNewsList(this);
    }

    //SERVERS
    public void setServerList() {
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setPercentWidth(100.0);
        centerGridPane.getColumnConstraints().clear();
        centerGridPane.getColumnConstraints().add(firstCC);

        centerGridPane.getChildren().clear();
        centerGridPane.getChildren().add(new ServerList(this));
        JFXHelpers.doublePropertyAnimation(Duration.millis(250), centerGridPane.opacityProperty(), 1.0);
    }

    public void addServerToFavoriteList(String name, String version, Integer players, Integer maxPlayers) {
        if (favoriteServerList != null) {
            if (!favoriteServerList.isVisible()) {
                favoriteServerList.setVisible(true);
            }

            favoriteServerList.setPrefHeight((favoriteServerList.getItems().size() + 1) * favoriteServerList.getFixedCellSize());
            favoriteServerList.getItems().add(new ServerItem(this, name, version, players + "/" + maxPlayers));
        }
    }

    public void addServerToOtherList(String name, String version, Integer players, Integer maxPlayers) {
        if (otherServerList != null) {
            otherServerList.setPrefHeight((otherServerList.getItems().size() + 1) * otherServerList.getFixedCellSize());
            otherServerList.getItems().add(new ServerItem(this, name, version, players + "/" + maxPlayers));
        } else {
            screen.logger.error("Launcher tried to add server when serverList wasn't initialized yet!");
        }
    }

    public void addModpackToList(ModpackType type, String name, String version, String description) {
        JFXListView<ModpackItem> list;
            switch (type) {
                case VANILLA:
                    list = vanillaModpackList;
                    break;
                case FTB:
                    list = ftbModpackList;
                    break;
                case OWN:
                    list = ownModpackList;
                    break;
                case KENPACK:
                    list = kenpackModpackList;
                    break;
                default:
                    list = otherModpackList;
                    break;

            }

        if (list != null) {
            if (!list.isVisible()) {
                list.setVisible(true);
            }

            list.setPrefHeight((list.getItems().size() + 1) * list.getFixedCellSize());

            ModpackItem item = new ModpackItem(this, name, version, description);
            item.setOnMouseReleased(event -> ModpackList.selectedModpackType = type);
            list.getItems().add(item);
            Managers.getModpackManager().addPack(item);
        } else {
            screen.logger.error("Launcher tried to add Modpack when ModpackList wasn't initialized yet!");
        }
    }

    public void addMenuOption(FontAwesomeIcon glyph, String label) {
        this.menuList.getItems().add(new MenuItem(this, glyph, label));
    }

    public void setCloseOption(String label) {
        this.closeRippler.setControl(new CloseItem(this, label));
        this.closeRippler.setMaskType(JFXRippler.RipplerMask.RECT);
        this.closeRippler.setPosition(JFXRippler.RipplerPos.FRONT);
        this.closeRippler.getStyleClass().add("closeApp");
    }

    public void setRightSite(String first) {
        this.rightCenterFirstText.setText(first);
    }

    public void setLauncherVersion(String version) {
        this.launcherVersion.setText(version);
    }

    public void setUserAvatar(Image avatar) {
        this.userAvatar.setFill(new ImagePattern(avatar));
    }

    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    public void setUserOnline(boolean premium) {
        if (premium) {
            this.userOnline.getStyleClass().setAll("userOnline_GREEN");
        } else {
            this.userOnline.getStyleClass().setAll("userOnline_RED");
        }
    }

    public StackPane getMainStackPane() { return this.mainStackPane; }

}
