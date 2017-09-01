package pl.mplauncher.launcher.form;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

class MainDesigner {

    private static final Logger logger = LogManager.getLogger(MainDesigner.class);

    //Elements
    private Scene mainScene;
    Label menuListIcon;
    Label menuListText;
    StackPane mainMenu;
    Circle userAvatar;
    Circle userOnline;
    Label userName;
    JFXListView<menuItem> menuList;
    JFXRippler closeRippler;
    private StackPane firstSPinCenterGP;
    private Text rightCenterFirstText;
    ImageView discordLogo;
    StackPane mainTop;
    private Text launcherVersion;
    private StackPane menuButtonSP;
    JFXRippler menuButton;
    FontAwesomeIconView menuButtonIconLEFT;
    FontAwesomeIconView menuButtonIconRIGHT;

    //Initializer
    void initializeComponent() {
        VBox mainForm = new VBox();
        StackPane mainStackPane = new StackPane();
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
        GridPane centerGridPane = new GridPane();
        firstSPinCenterGP = new StackPane();
        StackPane secondSPinCenterGP = new StackPane();
        rightCenterFirstText = new Text();
        discordLogo = new ImageView();
        TextFlow mplauncherText = new TextFlow();
        Text mpText = new Text();
        Text launcherText = new Text();
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
        closeRippler.getStyleClass().add("closeApp");
        StackPane.setMargin(closeRippler, new Insets(667.0, 0.0, 0.0, 0.0));

        BorderPane.setAlignment(borderPaneCenter, Pos.CENTER);

        BorderPane.setAlignment(centerGridPane, Pos.CENTER);
        ColumnConstraints firstCC = new ColumnConstraints();
        firstCC.setHgrow(Priority.SOMETIMES);
        firstCC.setMinWidth(10.0);
        firstCC.setPercentWidth(75.0);
        ColumnConstraints secondCC = new ColumnConstraints();
        secondCC.setHgrow(Priority.SOMETIMES);
        secondCC.setMinWidth(10.0);
        secondCC.setPercentWidth(25.0);
        secondCC.setPrefWidth(100.0);
        centerGridPane.getColumnConstraints().addAll(firstCC, secondCC);
        RowConstraints firstRC = new RowConstraints();
        firstRC.setMinHeight(10.0);
        firstRC.setVgrow(Priority.SOMETIMES);
        centerGridPane.getRowConstraints().add(firstRC);

        GridPane.setHalignment(firstSPinCenterGP, HPos.CENTER);
        GridPane.setValignment(firstSPinCenterGP, VPos.CENTER);

        secondSPinCenterGP.setAlignment(Pos.TOP_CENTER);
        secondSPinCenterGP.getStyleClass().add("fancyText");
        StackPane.setAlignment(secondSPinCenterGP, Pos.TOP_RIGHT);
        GridPane.setColumnIndex(secondSPinCenterGP, 1);

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

        mplauncherText.setMaxHeight(TextFlow.USE_PREF_SIZE);
        mplauncherText.setPrefHeight(50.0);
        mplauncherText.setTextAlignment(TextAlignment.CENTER);
        mplauncherText.getStyleClass().add("fancyText_3_Container");

        mpText.setStrokeType(StrokeType.OUTSIDE);
        mpText.setStrokeWidth(0.0);
        mpText.setTextAlignment(TextAlignment.CENTER);
        mpText.getStyleClass().addAll("fancyText_3", "textBold");
        mpText.setText("MP");

        launcherText.setStrokeType(StrokeType.OUTSIDE);
        launcherText.setStrokeWidth(0.0);
        launcherText.setTextAlignment(TextAlignment.CENTER);
        launcherText.getStyleClass().add("fancyText_3");
        launcherText.setText("LAUNCHER.PL");

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
        centerGridPane.getChildren().addAll(firstSPinCenterGP, secondSPinCenterGP);
        firstSPTopGP.getChildren().addAll(topBackground, mainTop);
        secondSPinCenterGP.getChildren().addAll(rightCenterFirstText, discordLogo, mplauncherText);
        mainTop.getChildren().addAll(launcherVersion, topContent);
        mplauncherText.getChildren().addAll(mpText, launcherText);
        topContent.getChildren().addAll(firstTextmainTopTF, secondTextmainTopTF);
        menuButton.getChildren().addAll(menuButtonIconLEFT, menuButtonIconRIGHT);

        //Binding
        mainMenu.widthProperty().addListener(listener -> menuButtonSP.setTranslateX(mainMenu.getWidth()));

        //Parent
        this.mainScene = new Scene(mainForm, 1178, 722);
        this.mainScene.setFill(Color.TRANSPARENT);
    }

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
            this.setCursor(Cursor.HAND);

            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.POWER_OFF, "26px");
            icon.getStyleClass().add("selectListIcon");
            Label text = new Label();
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

    void setNews(String newsTitle, Image newsImage, String newsArticle, String newsAuthor, String newsTime) {
        this.firstSPinCenterGP.getChildren().clear();
        this.firstSPinCenterGP.getChildren().add(new startNews(newsTitle, newsImage, newsArticle, newsAuthor, newsTime));
    }

    void addMenuOption(FontAwesomeIcon glyph, String label) {
        this.menuList.getItems().add(new menuItem(glyph, label));
    }

    void setCloseOption(String label) {
        this.closeRippler.getChildren().clear();
        this.closeRippler.getChildren().add(new closeItem(label));
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
        if (premium) { this.userOnline.getStyleClass().setAll("userOnline_GREEN"); }
        else { this.userOnline.getStyleClass().setAll("userOnline_RED");  }
    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}
