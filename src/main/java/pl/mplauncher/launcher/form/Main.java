package pl.mplauncher.launcher.form;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main extends MainDesigner {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static double xOffset;
    private static double yOffset;

    private void initialize() {
        //Form
        initializeComponent();

        //Events
        closeRippler.setOnMouseClicked(event -> closeClicked());
        discordLogo.setOnMouseClicked(event -> discordLogoClicked());
        menuButton.setOnMouseClicked(event -> menuButtonClicked());
    }

    public Main() {
        initialize();

        JFXHelpers.fadeTransition(Duration.millis(250), menuButtonIconLEFT, 0.0, 1.0);

        menuList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Node nodeIn : newValue.getChildren()) {
                if (nodeIn instanceof Label) {
                    System.out.println("New Selection -> ID: " + menuList.getSelectionModel().getSelectedIndex() + " == " + ((Label) nodeIn).getText());

                    if (menuList.getSelectionModel().getSelectedIndex() == 3) {
                        setServerList();

                        // Set servers!
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                        addServerToList("NAJLEPSZY SERWER ŚWIATA", "1.11.2", 11, 100);
                    }
                }
            }
        });

        // Allow to drag entire app via namePane
        mainTop.setOnMousePressed(event -> {
            xOffset = MPLauncherBootstrap.getStartStage().getX() - event.getScreenX();
            yOffset = MPLauncherBootstrap.getStartStage().getY() - event.getScreenY();
        });
        mainTop.setOnMouseDragged(event -> {
            MPLauncherBootstrap.getStartStage().setX(event.getScreenX() + xOffset);
            MPLauncherBootstrap.getStartStage().setY(event.getScreenY() + yOffset);
        });

        // -- SET ALL -- //

        //Set USERNAME
        URL imgUrl = getClass().getClassLoader().getResource("gaben.jpg");
        if (imgUrl != null) {
            Image img = new Image(imgUrl.toString());
            setUserAvatar(img);
        } else {
            logger.error("Couldn't set user avatar!");
        }

        setUserName("Łowca wiaderek :kappa:");
        setUserOnline(true);

        //Set menu
        addMenuOption(FontAwesomeIcon.USER_CIRCLE_ALT, "PROFIL" + System.lineSeparator() + "UŻYTKOWNIKA");
        addMenuOption(FontAwesomeIcon.COG, "USTAWIENIA" + System.lineSeparator() + "UŻYTKOWNIKA");
        addMenuOption(FontAwesomeIcon.NEWSPAPER_ALT, "NOWOŚCI");
        addMenuOption(FontAwesomeIcon.PLAY_CIRCLE_ALT, "WYBÓR SERWERA");

        //Set close
        setCloseOption("WYŁĄCZ");

        //Set NEWS
        URL imageUrl = getClass().getClassLoader().getResource("mc.jpg");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            setNews("NOWY WYGLĄD?", image, "Witajcie gracze i graczki!" + System.lineSeparator() + System.lineSeparator() +
                    "Jako, iż nasza ekipa robi wszystko ze starannością i dbałością dla was, postanowiłem rozpocząć tworzenie nowego stylu launchera!" + System.lineSeparator() +
                    "Styl przybrał nazwę „Callipso” i prawdopodobnie do 15-30 dni uda mi się stworzyć jego layout." + System.lineSeparator() +
                    "Na obecną chwilę mogę napisać, iż szykuje się pare dodatków w nowym wyglądzie, całkowita zmiana stylu oraz pełno eastereggów." + System.lineSeparator() + System.lineSeparator() +
                    "Czytaj więcej.", "IceMeltt", "~miesiąc temu");
        } else {
            logger.error("Couldn't set news image!");
        }

        //Set right
        setRightSite("Znajdziesz nas na:");

        //Set version
        setLauncherVersion("ver 1.0.1-dev. 14");
    }

    private void closeClicked() {
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> Platform.exit());
    }

    private void discordLogoClicked() {
        try {
            JFXHelpers.openWebpage(new URI("https://discord.gg/C5pkDan"));
        } catch (URISyntaxException e) {
            logger.error("Failed to open discord invite!", e);
        }
    }

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