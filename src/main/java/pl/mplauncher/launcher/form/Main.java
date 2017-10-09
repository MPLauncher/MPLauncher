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

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.api.config.ConfigUtils;
import pl.mplauncher.launcher.api.config.templates.Users;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.control.InstallerOverlay;
import pl.mplauncher.launcher.control.SettingsOverlay;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class Main extends MainDesigner {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static StackPane mainStackPane;

    public void initialize() {
        //Form
        initializeComponent();

        menuList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Node nodeIn : newValue.getChildren()) {
                if (nodeIn instanceof Label) {
                    System.out.println("New Selection -> ID: " + menuList.getSelectionModel().getSelectedIndex()
                            + " == " + ((Label) nodeIn).getText());

                    switch (menuList.getSelectionModel().getSelectedIndex()) {
                        case 1: {
                            SettingsOverlay settingsOverlay = new SettingsOverlay(mainStackPane);
                            settingsOverlay.setWindowTitle(MessageBundle.getCurrentLanguage().getMessage("main-settingsOverlayTitle"));
                            settingsOverlay.show();
                            break;
                        }
                        case 3: {
                            JFXHelpers.doublePropertyAnimation(Duration.millis(250), centerGridPane.opacityProperty(),
                                    0.0, event -> {
                                        setServerList();

                                        // When Favorite server is selected, deselect other server
                                        favoriteServerList.getSelectionModel().selectedItemProperty().addListener(((o, oV, nV) -> {
                                            if (nV != null && otherServerList != null
                                                    && !otherServerList.getSelectionModel().isEmpty()) {
                                                otherServerList.getSelectionModel().clearSelection();
                                            }
                                        }));
                                        otherServerList.getSelectionModel().selectedItemProperty().addListener(((o, oV, nV) -> {
                                            if (nV != null && favoriteServerList != null
                                                    && !favoriteServerList.getSelectionModel().isEmpty()) {
                                                favoriteServerList.getSelectionModel().clearSelection();
                                            }
                                        }));

                                        // Set servers!
                                        for (int x = 0; x < 53; x++) {
                                            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

                                            StringBuilder sb = new StringBuilder();
                                            Random random = new Random();
                                            for (int i = 0; i < random.nextInt(30) + 10; i++) {
                                                char ch = chars[random.nextInt(chars.length)];
                                                sb.append(ch);
                                            }

                                            if (x < 3) {
                                                addServerToFavoriteList(sb.toString(), "1.11.2",
                                                        random.nextInt(100), random.nextInt(100) + 100);
                                            } else {
                                                addServerToOtherList(sb.toString(), "1.11.2",
                                                        random.nextInt(100), random.nextInt(100) + 100);
                                            }
                                        }
                                    });
                            break;
                        }
                        default: {
                            logger.warn("Not implemented yet!");
                            break;
                        }
                    }
                }
            }
        });

        // -- SET ALL -- //

        //Set USERNAME
        URL imgUrl = Thread.currentThread().getContextClassLoader().getResource("gaben.jpg");
        if (imgUrl != null) {
            Image img = new Image(imgUrl.toString());
            setUserAvatar(img);
        } else {
            logger.error("Couldn't set user avatar!");
        }

        setUserName("Łowca wiaderek :kappa:");
        setUserOnline(true);

        //Set menu
        addMenuOption(FontAwesomeIcon.USER_CIRCLE_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuUserProfile").replace("#LINESEPARATOR#", System.lineSeparator()));
        addMenuOption(FontAwesomeIcon.COG, MessageBundle.getCurrentLanguage().getMessage("main-menuUserSettings").replace("#LINESEPARATOR#", System.lineSeparator()));
        addMenuOption(FontAwesomeIcon.NEWSPAPER_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuNews"));
        addMenuOption(FontAwesomeIcon.PLAY_CIRCLE_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuServerPicker"));

        //Set close
        setCloseOption(MessageBundle.getCurrentLanguage().getMessage("main-exit"));

        //Set NEWS
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("mc.jpg");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            setNews("NOWY WYGLĄD?", image, "Witajcie gracze i graczki!"
                    + System.lineSeparator() + System.lineSeparator() +
                    "Jako, iż nasza ekipa robi wszystko ze starannością i dbałością dla was, postanowiłem " +
                    "rozpocząć tworzenie nowego stylu launchera!" + System.lineSeparator() +
                    "Styl przybrał nazwę „Callipso” i prawdopodobnie do 15-30 dni uda mi się stworzyć jego layout."
                    + System.lineSeparator() +
                    "Na obecną chwilę mogę napisać, iż szykuje się pare dodatków w nowym wyglądzie, " +
                    "całkowita zmiana stylu oraz pełno eastereggów." + System.lineSeparator() + System.lineSeparator() +
                    "Czytaj więcej.", "IceMeltt", "~miesiąc temu");
        } else {
            logger.error("Couldn't set news image!");
        }

        //Set right
        setRightSite(MessageBundle.getCurrentLanguage().getMessage("main-findUsAt"));

        //Set version
        setLauncherVersion(((MPLauncher.class.getPackage().getImplementationVersion() == null) ? "DEV" : MPLauncher.class.getPackage().getImplementationVersion()));

        //Main stackpane
        mainStackPane = getMainStackPane();
    }

    static void closeClicked() {
        Platform.exit();
    }

    static void discordLogoClicked() {
        try {
            JFXHelpers.openWebpage(new URI("https://discord.gg/C5pkDan"));
        } catch (URISyntaxException e) {
            logger.error("Failed to open discord invite!", e);
        }
    }

    static void playClicked() {
        InstallerOverlay installerOverlay = new InstallerOverlay(mainStackPane);
        installerOverlay.setStatus("Instalowanie: LIBRARIES");
        installerOverlay.setPercentage(0.571f);
        installerOverlay.setDescription("Zainstalowano 100 spośród 10000 plików.");

        installerOverlay.show();
    }

}
