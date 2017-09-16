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
package pl.mplauncher.launcher.form;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.net.URL;

class MainDesigner {

    private static final Logger logger = LogManager.getLogger(MainDesigner.class);

    StackPane mainStackPane;
    Label menuListIcon;
    Label menuListText;
    StackPane mainMenu;
    Circle userAvatar;
    Circle userOnline;
    Label userName;
    JFXListView<menuItem> menuList;
    JFXRippler closeRippler;
    GridPane centerGridPane;
    ImageView discordLogo;
    StackPane mainTop;
    JFXRippler menuButton;
    FontAwesomeIconView menuButtonIconLEFT;
    FontAwesomeIconView menuButtonIconRIGHT;
    JFXListView<serverItem> favoriteServerList;
    JFXListView<serverItem> otherServerList;
    //Elements
    private Scene mainScene;
    private StackPane firstSPinCenterGP;
    private Text rightCenterFirstText;
    private Text launcherVersion;
    private StackPane menuButtonSP;
    private ScrollPane serverListleftSite;

    //Initializer
    void initializeComponent() {
        VBox mainForm = new VBox();
        mainStackPane = new StackPane();
        menuListIcon = new Label();
        menuListText = new Label();
        BorderPane borderPane = new BorderPane();
        mainMenu = new StackPane();
        userAvatar = new Circle();
        userOnline = new Circle();
        userName = new Label();
        menuList = new JFXListView<>();
        closeRippler = new JFXRippler();
        BorderPane borderPaneCenter = new BorderPane();
        centerGridPane = new GridPane();
        firstSPinCenterGP = new StackPane();
        rightCenterFirstText = new Text();
        discordLogo = new ImageView();
        StackPane firstSPTopGP = new StackPane();
        StackPane topBackground = new StackPane();
        mainTop = new StackPane();
        launcherVersion = new Text();
        TextFlow topContent = new TextFlow();
        Text firstTextmainTopTF = new Text();
        Text secondTextmainTopTF = new Text();
        menuButtonSP = new StackPane();
        Circle menuButtonBackground = new Circle();
        menuButton = new JFXRippler();
        menuButtonIconLEFT = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_LEFT, "25px");
        menuButtonIconRIGHT = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT, "25px");

        mainForm.setMinSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        mainForm.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        mainForm.setPrefSize(1178.0, 722.0);

        URL style = getClass().getClassLoader().getResource("style_main.css");
        if (style != null) {
            mainForm.getStylesheets().add(style.toExternalForm());
        } else {
            logger.error("Couldn't load CSS style for the Main form!");
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

        Circle circle = new Circle();
        circle.setRadius(4);

        menuButtonIconLEFT.setOpacity(0.0);
        menuButtonIconLEFT.setBoundsType(TextBoundsType.VISUAL);
        menuButtonIconLEFT.getStyleClass().add("menuButton");
        menuButtonIconLEFT.setMouseTransparent(true);

        menuButtonIconRIGHT.setOpacity(0.0);
        menuButtonIconRIGHT.setBoundsType(TextBoundsType.VISUAL);
        menuButtonIconRIGHT.getStyleClass().add("menuButton");
        menuButtonIconRIGHT.setMouseTransparent(true);

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
        this.mainScene = new Scene(mainForm, 1178, 722);
        this.mainScene.setFill(Color.TRANSPARENT);
    }

    void setNews(String newsTitle, Image newsImage, String newsArticle, String newsAuthor, String newsTime) {
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
        RowConstraints firstRC = new RowConstraints();
        firstRC.setMinHeight(10.0);
        firstRC.setVgrow(Priority.SOMETIMES);
        centerGridPane.getRowConstraints().clear();
        centerGridPane.getRowConstraints().add(firstRC);

        centerGridPane.getChildren().addAll(firstSPinCenterGP, new rightofNews());
        this.firstSPinCenterGP.getChildren().clear();
        this.firstSPinCenterGP.getChildren().add(new startNews(newsTitle, newsImage, newsArticle, newsAuthor, newsTime));
    }

    void setServerList() {
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setPercentWidth(100.0);
        centerGridPane.getColumnConstraints().clear();
        centerGridPane.getColumnConstraints().add(firstCC);
        RowConstraints firstRC = new RowConstraints();
        firstRC.setVgrow(Priority.SOMETIMES);
        centerGridPane.getRowConstraints().clear();
        centerGridPane.getRowConstraints().add(firstRC);

        centerGridPane.getChildren().clear();
        centerGridPane.getChildren().add(new serverList());
        JFXHelpers.doublePropertyAnimation(Duration.millis(250), centerGridPane.opacityProperty(), 1.0);
    }

    JFXDialog createInstallerDialog(String status, String percentage, String description) {
        return new InstallerOverlay(status.toUpperCase(), percentage.toUpperCase(), description.toUpperCase());
    }

    void addServerToFavoriteList(String name, String version, Integer players, Integer maxPlayers) {
        if (favoriteServerList != null) {
            if (!favoriteServerList.isVisible()) {
                favoriteServerList.setVisible(true);
            }

            favoriteServerList.setPrefHeight((favoriteServerList.getItems().size() + 1) * favoriteServerList.getFixedCellSize());
            favoriteServerList.getItems().add(new serverItem(name, version, players + "/" + maxPlayers));
        }
    }

    void addServerToOtherList(String name, String version, Integer players, Integer maxPlayers) {
        if (otherServerList != null) {
            otherServerList.setPrefHeight((otherServerList.getItems().size() + 1) * otherServerList.getFixedCellSize());
            otherServerList.getItems().add(new serverItem(name, version, players + "/" + maxPlayers));
        } else {
            logger.error("Launcher tried to add server when serverList wasn't initialized yet!");
        }
    }

    void addMenuOption(FontAwesomeIcon glyph, String label) {
        this.menuList.getItems().add(new menuItem(glyph, label));
    }

    void setCloseOption(String label) {
        this.closeRippler.setControl(new closeItem(label));
        this.closeRippler.setMaskType(JFXRippler.RipplerMask.RECT);
        this.closeRippler.setPosition(JFXRippler.RipplerPos.FRONT);
        this.closeRippler.getStyleClass().add("closeApp");
    }

    void setRightSite(String first) {
        this.rightCenterFirstText.setText(first);
    }

    void setLauncherVersion(String version) {
        this.launcherVersion.setText(version);
    }

    void setUserAvatar(Image avatar) {
        this.userAvatar.setFill(new ImagePattern(avatar));
    }

    void setUserName(String userName) {
        this.userName.setText(userName);
    }

    void setUserOnline(boolean premium) {
        if (premium) {
            this.userOnline.getStyleClass().setAll("userOnline_GREEN");
        } else {
            this.userOnline.getStyleClass().setAll("userOnline_RED");
        }
    }

    public Scene getMainScene() {
        return this.mainScene;
    }

    public StackPane getMainStackPane() { return this.mainStackPane; }

    class menuItem extends GridPane {
        menuItem(FontAwesomeIcon glyph, String label) {
            this.setMouseTransparent(true);

            ColumnConstraints first = new ColumnConstraints();
            first.setHgrow(Priority.NEVER);
            first.percentWidthProperty().bind(menuListIcon.minWidthProperty());
            first.setHalignment(HPos.CENTER);

            ColumnConstraints second = new ColumnConstraints();
            second.setHgrow(Priority.NEVER);
            second.percentWidthProperty().bind(menuListText.minWidthProperty());

            this.getColumnConstraints().addAll(first, second);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(35);
            rowConstraints.setVgrow(Priority.SOMETIMES);

            this.getRowConstraints().add(rowConstraints);
            this.setCursor(Cursor.HAND);

            FontAwesomeIconView icon = new FontAwesomeIconView(glyph, "26px");
            Label text = new Label();
            text.opacityProperty().bind(menuListText.opacityProperty());
            text.setText(label);
            GridPane.setColumnIndex(text, 1);

            this.getChildren().addAll(icon, text);
        }
    }

    private class closeItem extends GridPane {
        closeItem(String label) {
            this.setMouseTransparent(true);
            this.setPadding(new Insets(0.0, 13.0, 13.0, 13.0));

            ColumnConstraints first = new ColumnConstraints();
            first.setHgrow(Priority.NEVER);
            first.percentWidthProperty().bind(menuListIcon.minWidthProperty());
            first.setHalignment(HPos.CENTER);

            ColumnConstraints second = new ColumnConstraints();
            second.setHgrow(Priority.NEVER);
            second.percentWidthProperty().bind(menuListText.minWidthProperty());

            this.getColumnConstraints().addAll(first, second);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(55.0);
            rowConstraints.setVgrow(Priority.NEVER);

            this.getRowConstraints().add(rowConstraints);

            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.POWER_OFF, "26px");
            icon.getStyleClass().add("selectListIcon");
            icon.setMouseTransparent(true);
            Label text = new Label();
            text.setMouseTransparent(true);
            text.opacityProperty().bind(menuListText.opacityProperty());
            text.setText(label);
            GridPane.setColumnIndex(text, 1);

            this.getChildren().addAll(icon, text);
        }
    }

    class startNews extends StackPane {
        startNews(String title, Image image, String article, String author, String time) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getStyleClass().add("newsContent");
            StackPane.setAlignment(this, Pos.CENTER);
            StackPane.setMargin(this, new Insets(25.0, 60.0, 34.0, 65.0));

            StackPane titleSP = new StackPane();
            titleSP.setAlignment(Pos.CENTER_LEFT);
            titleSP.setMaxHeight(StackPane.USE_PREF_SIZE);
            titleSP.setPrefSize(200.0, 53.0);
            titleSP.setPadding(new Insets(0.0, 0.0, 0.0, 16.0));

            Text newsTitle = new Text();
            newsTitle.setStrokeType(StrokeType.OUTSIDE);
            newsTitle.setStrokeWidth(0.0);
            newsTitle.getStyleClass().add("newsTitle");
            newsTitle.setText(title);

            StackPane imageSP = new StackPane();
            imageSP.setMaxHeight(StackPane.USE_PREF_SIZE);
            imageSP.setPrefHeight(212.0);
            imageSP.getStyleClass().add("newsImage");
            imageSP.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            StackPane.setMargin(imageSP, new Insets(53.0, 0.0, 0.0, 0.0));

            StackPane articleSP = new StackPane();
            articleSP.setAlignment(Pos.TOP_LEFT);
            articleSP.setMaxHeight(StackPane.USE_PREF_SIZE);
            articleSP.setPrefHeight(188.0);
            articleSP.setPadding(new Insets(23.0, 16.0, 16.0, 16.0));
            StackPane.setMargin(articleSP, new Insets(266.0, 0.0, 0.0, 0.0));

            Label newsArticle = new Label();
            newsArticle.setWrapText(true);
            newsArticle.getStyleClass().add("newsArticle");
            newsArticle.setText(article);

            StackPane footerSP = new StackPane();
            footerSP.setAlignment(Pos.TOP_RIGHT);
            footerSP.setMaxHeight(StackPane.USE_PREF_SIZE);
            footerSP.setPrefHeight(40.0);
            footerSP.setPadding(new Insets(0.0, 21.0, 0.0, 0.0));
            StackPane.setMargin(footerSP, new Insets(451.0, 0.0, 0.0, 0.0));

            Text newsAuthor = new Text();
            newsAuthor.setStrokeType(StrokeType.OUTSIDE);
            newsAuthor.setStrokeWidth(0.0);
            newsAuthor.getStyleClass().add("newsAuthor");
            newsAuthor.setText(author);

            Text newsTime = new Text();
            newsTime.setStrokeType(StrokeType.OUTSIDE);
            newsTime.setStrokeWidth(0.0);
            newsTime.getStyleClass().add("newsTime");
            StackPane.setAlignment(newsTime, Pos.BOTTOM_RIGHT);
            StackPane.setMargin(newsTime, new Insets(0.0, 0.0, 16.0, 0.0));
            newsTime.setText(time);

            this.getChildren().addAll(titleSP, imageSP, articleSP, footerSP);
            titleSP.getChildren().add(newsTitle);
            articleSP.getChildren().add(newsArticle);
            footerSP.getChildren().addAll(newsAuthor, newsTime);
        }
    }

    class rightofNews extends StackPane {
        rightofNews() {
            this.setAlignment(Pos.TOP_CENTER);
            this.getStyleClass().add("fancyText");
            StackPane.setAlignment(this, Pos.TOP_RIGHT);
            GridPane.setColumnIndex(this, 1);

            rightCenterFirstText.setStrokeType(StrokeType.OUTSIDE);
            rightCenterFirstText.setStrokeWidth(0.0);
            rightCenterFirstText.getStyleClass().add("fancyText_1");
            rightCenterFirstText.setTextAlignment(TextAlignment.CENTER);

            discordLogo.setFitWidth(163.0);
            discordLogo.setFitHeight(55.0);
            discordLogo.setPickOnBounds(true);
            discordLogo.setPreserveRatio(true);
            discordLogo.setTranslateY(215.0);
            discordLogo.setCursor(Cursor.HAND);
            URL imgUrl = getClass().getClassLoader().getResource("DiscordLogo.png");
            if (imgUrl != null) {
                discordLogo.setImage(new Image(imgUrl.toString()));
            } else {
                logger.error("Couldn't load discord logo for the Main form!");
            }


            TextFlow mplauncherText = new TextFlow();
            mplauncherText.setMaxHeight(TextFlow.USE_PREF_SIZE);
            mplauncherText.setPrefHeight(50.0);
            mplauncherText.setTextAlignment(TextAlignment.CENTER);
            mplauncherText.getStyleClass().add("fancyText_3_Container");

            Text mpText = new Text();
            mpText.setStrokeType(StrokeType.OUTSIDE);
            mpText.setStrokeWidth(0.0);
            mpText.setTextAlignment(TextAlignment.CENTER);
            mpText.getStyleClass().addAll("fancyText_3", "textBold");
            mpText.setText("MP");

            Text launcherText = new Text();
            launcherText.setStrokeType(StrokeType.OUTSIDE);
            launcherText.setStrokeWidth(0.0);
            launcherText.setTextAlignment(TextAlignment.CENTER);
            launcherText.getStyleClass().add("fancyText_3");
            launcherText.setText("LAUNCHER.PL");

            this.getChildren().addAll(rightCenterFirstText, discordLogo, mplauncherText);
            mplauncherText.getChildren().addAll(mpText, launcherText);
        }
    }

    class serverList extends StackPane {
        serverList() {
            GridPane.setHalignment(this, HPos.CENTER);
            GridPane.setValignment(this, VPos.CENTER);

            BorderPane container = new BorderPane();
            container.getStyleClass().add("serverMenuBP");
            StackPane.setMargin(container, new Insets(25.0, 60.0, 34.0, 65.0));

            // TOP //
            StackPane topStackPane = new StackPane();
            topStackPane.setPrefHeight(53.0);
            topStackPane.getStyleClass().add("serverMenuTop");

            Label serverListText = new Label();
            serverListText.setText("LISTA SERWERÓW");
            StackPane.setAlignment(serverListText, Pos.CENTER_LEFT);
            StackPane.setMargin(serverListText, new Insets(0.0, 0.0, 0.0, 20.0));
            serverListText.getStyleClass().addAll("fontSemiBold", "fontSize12", "textFillWhite");
            // TOP //

            // CENTER //
            GridPane centerContainer = new GridPane();
            ColumnConstraints cCcolumn1 = new ColumnConstraints();
            cCcolumn1.setHgrow(Priority.SOMETIMES);
            centerContainer.getColumnConstraints().add(cCcolumn1);
            RowConstraints cCrow1 = new RowConstraints();
            cCrow1.setPercentHeight(45.0);
            cCrow1.setVgrow(Priority.SOMETIMES);
            RowConstraints cCrow2 = new RowConstraints();
            cCrow2.setPercentHeight(40.0);
            cCrow2.setVgrow(Priority.SOMETIMES);
            RowConstraints cCrow3 = new RowConstraints();
            cCrow3.setPercentHeight(15.0);
            cCrow3.setVgrow(Priority.SOMETIMES);
            centerContainer.getRowConstraints().addAll(cCrow1, cCrow2, cCrow3);

            StackPane serverImage = new StackPane();
            URL imageUrl = getClass().getClassLoader().getResource("mc.jpg");
            if (imageUrl != null) {
                Image image = new Image(imageUrl.toString());
                serverImage.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, true))));
            } else {
                logger.error("Couldn't set server image!");
            }

            GridPane serverInfo = new GridPane();
            GridPane.setRowIndex(serverInfo, 1);
            ColumnConstraints sIcolumn1 = new ColumnConstraints();
            sIcolumn1.setHgrow(Priority.SOMETIMES);
            serverInfo.getColumnConstraints().add(sIcolumn1);
            RowConstraints sIrow1 = new RowConstraints();
            sIrow1.setPercentHeight(20.0);
            sIrow1.setVgrow(Priority.SOMETIMES);
            RowConstraints sIrow2 = new RowConstraints();
            sIrow2.setPercentHeight(80.0);
            sIrow2.setVgrow(Priority.SOMETIMES);
            serverInfo.getRowConstraints().addAll(sIrow1, sIrow2);

            Text serverName = new Text();
            serverName.setStrokeType(StrokeType.OUTSIDE);
            serverName.setStrokeWidth(0.0);
            serverName.setText("NAJLEPSZY SERWER ŚWIATA"); //Testing purpose
            GridPane.setMargin(serverName, new Insets(0, 0.0, 0.0, 20.0));
            serverName.getStyleClass().addAll("fontSemiBold", "fontSize14", "fillWhite");

            Label serverDescription = new Label();
            serverDescription.setWrapText(true);
            GridPane.setRowIndex(serverDescription, 1);
            GridPane.setValignment(serverDescription, VPos.TOP);
            GridPane.setMargin(serverDescription, new Insets(0.0, 20.0, 0.0, 20.0));
            serverDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse et mauris id arcu sodales lobortis nec sit amet enim. Integer venenatis nunc at purus varius pulvinar. Sed vehicula convallis aliquet. Vestibulum congue vestibulum magna, sit amet tristique neque imperdiet eu.\n" +
                    "\n" +
                    " Sed vehicula convallis aliquet. Vestibulum congue vestibulum magna, sit amet tristique neque imperdiet eu."); //Testing purpose
            serverDescription.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

            GridPane serverOptions = new GridPane();
            GridPane.setRowIndex(serverOptions, 2);
            ColumnConstraints sOcolumn1 = new ColumnConstraints();
            sOcolumn1.setHgrow(Priority.SOMETIMES);
            sOcolumn1.setPercentWidth(60.0);
            ColumnConstraints sOcolumn2 = new ColumnConstraints();
            sOcolumn2.setHgrow(Priority.SOMETIMES);
            sOcolumn2.setPercentWidth(20.0);
            ColumnConstraints sOcolumn3 = new ColumnConstraints();
            sOcolumn3.setHgrow(Priority.SOMETIMES);
            sOcolumn3.setPercentWidth(20.0);
            serverOptions.getColumnConstraints().addAll(sOcolumn1, sOcolumn2, sOcolumn3);
            RowConstraints sOrow1 = new RowConstraints();
            sOrow1.setPercentHeight(100.0);
            sOrow1.setVgrow(Priority.SOMETIMES);
            serverOptions.getRowConstraints().add(sOrow1);
            GridPane.setMargin(serverOptions, new Insets(0.0, 0.0, 20.0, 0.0));

            GridPane leftserverOptions = new GridPane();
            ColumnConstraints lsOcolumn1 = new ColumnConstraints();
            lsOcolumn1.setPercentWidth(100.0);
            lsOcolumn1.setHgrow(Priority.SOMETIMES);
            leftserverOptions.getColumnConstraints().add(lsOcolumn1);
            RowConstraints lsOrow1 = new RowConstraints();
            lsOrow1.setVgrow(Priority.SOMETIMES);
            RowConstraints lsOrow2 = new RowConstraints();
            lsOrow2.setVgrow(Priority.SOMETIMES);
            leftserverOptions.getRowConstraints().addAll(lsOrow1, lsOrow2);

            Label addToFavorite = new Label();
            addToFavorite.setText("dodaj do ulubionych");
            GridPane.setMargin(addToFavorite, new Insets(0.0, 0.0, 0.0, 20.0));
            addToFavorite.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.HEART_ALT));
            addToFavorite.getGraphic().getStyleClass().add("fillWhite");
            addToFavorite.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

            Label isInstalled = new Label();
            isInstalled.setText("zainstalowano");
            GridPane.setRowIndex(isInstalled, 1);
            GridPane.setMargin(isInstalled, new Insets(0.0, 0.0, 0.0, 20.0));
            isInstalled.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHECK));
            isInstalled.getGraphic().getStyleClass().add("fillWhite");
            isInstalled.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

            JFXButton playButton = new JFXButton();
            playButton.setPrefWidth(100.0);
            playButton.setPrefHeight(30.0);
            playButton.setText("GRAJ");
            GridPane.setColumnIndex(playButton, 2);
            GridPane.setHalignment(playButton, HPos.CENTER);
            GridPane.setMargin(playButton, new Insets(0.0, 20.0, 0.0, 0.0));
            playButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "playButton");
            playButton.setOnAction(Main::playClicked);

            GridPane rightserverOptions = new GridPane();
            GridPane.setColumnIndex(rightserverOptions, 1);
            ColumnConstraints rsOcolumn1 = new ColumnConstraints();
            rsOcolumn1.setHgrow(Priority.SOMETIMES);
            rsOcolumn1.setPrefWidth(100.0);
            ColumnConstraints rsOcolumn2 = new ColumnConstraints();
            rsOcolumn2.setHgrow(Priority.SOMETIMES);
            rsOcolumn2.setPrefWidth(100.0);
            rightserverOptions.getColumnConstraints().addAll(rsOcolumn1, rsOcolumn2);
            RowConstraints rsOrow1 = new RowConstraints();
            rsOrow1.setVgrow(Priority.SOMETIMES);
            rightserverOptions.getRowConstraints().add(rsOrow1);

            Text ramLabel = new Text();
            ramLabel.setStrokeType(StrokeType.OUTSIDE);
            ramLabel.setStrokeWidth(0.0);
            ramLabel.setText("ILOŚĆ RAM");
            GridPane.setHalignment(ramLabel, HPos.RIGHT);
            GridPane.setMargin(ramLabel, new Insets(0.0, 3.0, 0.0, 0.0));
            ramLabel.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillWhite");

            JFXTextField ramField = new JFXTextField();
            ramField.setAlignment(Pos.CENTER);
            ramField.setMaxWidth(JFXTextField.USE_PREF_SIZE);
            ramField.setPrefWidth(50.0);
            ramField.setPromptText("RAM");
            ramField.setFocusColor(Color.WHITE);
            ramField.setUnFocusColor(Color.WHITE);
            GridPane.setColumnIndex(ramField, 1);
            GridPane.setHalignment(ramField, HPos.LEFT);
            GridPane.setMargin(ramField, new Insets(0.0, 0.0, 0.0, 3.0));
            ramField.getStyleClass().addAll("fontSemiBold", "fontSize8_5", "textFillWhite");
            // CENTER //

            // LEFT //
            serverListleftSite = new ScrollPane();
            serverListleftSite.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            serverListleftSite.setMinSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
            serverListleftSite.setMaxSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
            serverListleftSite.setPrefSize(314.0, 445.0);
            serverListleftSite.setPadding(new Insets(0.0));
            serverListleftSite.getStyleClass().add("serverListScrollPane");

            VBox serverListleftSiteVBox = new VBox();
            serverListleftSiteVBox.getStyleClass().add("serverListContainer");
            serverListleftSiteVBox.setPadding(new Insets(0.0));

            favoriteServerList = new JFXListView<>();
            favoriteServerList.setFixedCellSize(60.0);
            favoriteServerList.getStyleClass().add("serverList");
            favoriteServerList.setPadding(new Insets(0.0));
            favoriteServerList.managedProperty().bind(favoriteServerList.visibleProperty());
            favoriteServerList.setVisible(false);

            StackPane favoriteServerListIndicator = new StackPane();
            favoriteServerListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            favoriteServerListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            favoriteServerListIndicator.setPrefSize(305.0, 30.0);
            favoriteServerListIndicator.setPadding(new Insets(0.0));
            favoriteServerListIndicator.getStyleClass().add("serverListIndicator");
            favoriteServerListIndicator.managedProperty().bind(favoriteServerList.managedProperty());
            favoriteServerListIndicator.visibleProperty().bind(favoriteServerList.visibleProperty());

            Label fSLLabel = new Label();
            fSLLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
            fSLLabel.setText("ULUBIONE");
            StackPane.setAlignment(fSLLabel, Pos.CENTER_LEFT);

            StackPane otherServerListIndicator = new StackPane();
            otherServerListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            otherServerListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            otherServerListIndicator.setPrefSize(305.0, 30.0);
            otherServerListIndicator.setPadding(new Insets(0.0));
            otherServerListIndicator.getStyleClass().add("serverListIndicator");
            otherServerListIndicator.managedProperty().bind(favoriteServerList.managedProperty());
            otherServerListIndicator.visibleProperty().bind(favoriteServerList.visibleProperty());

            Label oSLLabel = new Label();
            oSLLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
            oSLLabel.setText("POZOSTAŁE");
            StackPane.setAlignment(oSLLabel, Pos.CENTER_LEFT);

            otherServerList = new JFXListView<>();
            otherServerList.setFixedCellSize(60.0);
            otherServerList.getStyleClass().add("serverList");
            otherServerList.setPadding(new Insets(0.0));
            // LEFT //

            //Binding
            this.getChildren().add(container);
            container.setTop(topStackPane);
            container.setCenter(centerContainer);
            container.setLeft(serverListleftSite);
            topStackPane.getChildren().add(serverListText);
            centerContainer.getChildren().addAll(serverImage, serverInfo, serverOptions);
            serverListleftSite.setContent(serverListleftSiteVBox);
            serverInfo.getChildren().addAll(serverName, serverDescription);
            serverOptions.getChildren().addAll(leftserverOptions, playButton, rightserverOptions);
            serverListleftSiteVBox.getChildren().addAll(favoriteServerListIndicator, favoriteServerList, otherServerListIndicator, otherServerList);
            leftserverOptions.getChildren().addAll(addToFavorite, isInstalled);
            rightserverOptions.getChildren().addAll(ramLabel, ramField);
            favoriteServerListIndicator.getChildren().add(fSLLabel);
            otherServerListIndicator.getChildren().add(oSLLabel);
        }
    }

    class serverItem extends GridPane {
        serverItem(String name, String version, String slots) {
            this.setMouseTransparent(true);
            this.setMaxWidth(GridPane.USE_PREF_SIZE);
            this.prefWidthProperty().bind(serverListleftSite.widthProperty().subtract(43));

            ColumnConstraints sIcolumn1 = new ColumnConstraints();
            sIcolumn1.setHgrow(Priority.NEVER);
            sIcolumn1.setPercentWidth(70.0);
            ColumnConstraints sIcolumn2 = new ColumnConstraints();
            sIcolumn1.setHgrow(Priority.NEVER);
            sIcolumn2.setPercentWidth(30.0);

            this.getColumnConstraints().addAll(sIcolumn1, sIcolumn2);

            RowConstraints sIrow1 = new RowConstraints();
            sIrow1.setVgrow(Priority.SOMETIMES);
            RowConstraints sIrow2 = new RowConstraints();
            sIrow2.setVgrow(Priority.SOMETIMES);

            this.getRowConstraints().addAll(sIrow1, sIrow2);

            Label serverName = new Label();
            serverName.setWrapText(true);
            GridPane.setValignment(serverName, VPos.BOTTOM);
            serverName.setText(name.toUpperCase());
            GridPane.setMargin(serverName, new Insets(0.0, 0.0, 0.0, 24.0));
            serverName.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillTextWhite");

            Label serverVersion = new Label();
            GridPane.setValignment(serverVersion, VPos.TOP);
            GridPane.setHalignment(serverVersion, HPos.LEFT);
            GridPane.setRowIndex(serverVersion, 1);
            serverVersion.setText("WERSJA MC: " + version.toUpperCase());
            GridPane.setMargin(serverVersion, new Insets(0.0, 0.0, 0.0, 24.0));
            serverVersion.getStyleClass().addAll("fontRegular", "fontSize8", "fillTextWhite");

            Label serverSlots = new Label();
            GridPane.setValignment(serverSlots, VPos.BOTTOM);
            GridPane.setHalignment(serverSlots, HPos.CENTER);
            GridPane.setColumnIndex(serverSlots, 1);
            serverSlots.setText(slots.toUpperCase());
            serverSlots.getStyleClass().addAll("fontRegular", "fontSize10", "fillTextWhite");

            this.getChildren().addAll(serverName, serverVersion, serverSlots);
        }
    }

    class InstallerOverlay extends JFXDialog {
        InstallerOverlay(String info1, String info2, String info3) {
            StackPane content = new StackPane();
            content.setPrefSize(531.0, 104.0);
            content.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            content.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            content.setAlignment(Pos.CENTER_LEFT);
            content.getStyleClass().add("installerContent");

            JFXProgressBar progressBar = new JFXProgressBar();
            progressBar.setPrefSize(384.0, 9.0);
            progressBar.setProgress(0.571f);
            StackPane.setMargin(progressBar, new Insets(0, 0, 0, 100.0));

            ImageView logo = new ImageView();
            logo.setFitWidth(64.0);
            logo.setFitHeight(70.0);
            logo.setPickOnBounds(true);
            logo.setPreserveRatio(true);
            URL imageUrl = getClass().getClassLoader().getResource("logo-cropped.png");
            if (imageUrl != null) {
                Image image = new Image(imageUrl.toString());
                logo.setImage(image);
            } else {
                logger.error("Couldn't set installer overlay logo!");
            }
            StackPane.setMargin(logo, new Insets(0, 0, 0, 48));

            Label status = new Label();
            status.setText(info1);
            status.getStyleClass().add("fontSemiBold");
            StackPane.setAlignment(status, Pos.TOP_LEFT);
            StackPane.setMargin(status, new Insets(32.0, 0, 0, 121.0));

            Label percentage = new Label();
            percentage.setTextAlignment(TextAlignment.RIGHT);
            percentage.setText(info2);
            percentage.getStyleClass().add("fontSemiBold");
            StackPane.setAlignment(percentage, Pos.TOP_RIGHT);
            StackPane.setMargin(percentage, new Insets(32.0, 47.0, 0, 0));

            Label description = new Label();
            description.setText(info3);
            description.getStyleClass().add("fontLight");
            StackPane.setAlignment(description, Pos.BOTTOM_LEFT);
            StackPane.setMargin(description, new Insets(0.0, 0.0, 32.0, 120.0));

            content.getChildren().addAll(progressBar, logo, status, percentage, description);

            this.setOverlayClose(false);
            this.setAlignment(Pos.CENTER);
            this.setContent(content);
        }
    }
}
