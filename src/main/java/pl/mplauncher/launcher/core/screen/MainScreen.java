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
package pl.mplauncher.launcher.core.screen;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.api.mp.MPAPI;
import pl.mplauncher.launcher.core.config.UserProfile;
import pl.mplauncher.launcher.core.control.InstallerOverlay;
import pl.mplauncher.launcher.core.control.QuestionOverlay;
import pl.mplauncher.launcher.core.control.SettingsOverlay;
import pl.mplauncher.launcher.core.helper.ApplicationFactory;
import pl.mplauncher.launcher.core.helper.JFXHelpers;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class MainScreen extends Screen<MainLayout> {

    private static StackPane mainStackPane; // TODO

    public void initialize() {
        layout.initialize();

        layout.menuList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Node nodeIn : newValue.getChildren()) {
                if (nodeIn instanceof Label) {
                    System.out.println("New Selection -> ID: " + layout.menuList.getSelectionModel().getSelectedIndex()
                            + " == " + ((Label) nodeIn).getText());

                    switch (layout.menuList.getSelectionModel().getSelectedIndex()) {
                        case 1: {
                            SettingsOverlay settingsOverlay = new SettingsOverlay(mainStackPane);
                            settingsOverlay.setWindowTitle(MessageBundle.getCurrentLanguage().getMessage("main-settingsOverlayTitle"));
                            settingsOverlay.show();
                            break;
                        }
                        case 3: {
                            JFXHelpers.doublePropertyAnimation(Duration.millis(250), layout.centerGridPane.opacityProperty(),
                                    0.0, event -> {
                                        layout.setServerList();

                                        // When Favorite server is selected, deselect other server
                                        layout.favoriteServerList.getSelectionModel().selectedItemProperty().addListener(((o, oV, nV) -> {
                                            if (nV != null && layout.otherServerList != null
                                                    && !layout.otherServerList.getSelectionModel().isEmpty()) {
                                                layout.otherServerList.getSelectionModel().clearSelection();
                                            }
                                        }));
                                        layout.otherServerList.getSelectionModel().selectedItemProperty().addListener(((o, oV, nV) -> {
                                            if (nV != null && layout.favoriteServerList != null
                                                    && !layout.favoriteServerList.getSelectionModel().isEmpty()) {
                                                layout.favoriteServerList.getSelectionModel().clearSelection();
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
                                                layout.addServerToFavoriteList(sb.toString(), "1.11.2",
                                                        random.nextInt(100), random.nextInt(100) + 100);
                                            } else {
                                                layout.addServerToOtherList(sb.toString(), "1.11.2",
                                                        random.nextInt(100), random.nextInt(100) + 100);
                                            }
                                        }
                                    });
                            break;
                        }
                        default: {
                            logger.warn("Not implemented yet!");

                            //Example of how QuestionOverlay works.
                            QuestionOverlay questionOverlay = new QuestionOverlay(QuestionOverlay.DialogType.Ok, "Not implemented", "This is not implemented yet!");
                            logger.info("Clicked: " + questionOverlay.getResult());
                            break;
                        }
                    }
                }
            }
        });

        // -- SET ALL -- //

        UserProfile profile = ApplicationFactory.getUsersManager().getCurrentProfile();

        Image skinImg = MPAPI.skins()
                .get(profile.getUsername())
                    .head()
                    .showHair()
                    .ratio(20)
                .getImage();

        layout.setUserAvatar(skinImg);

        layout.setUserName(profile.getUsername());
        layout.setUserOnline(true);

        //Set menu
        layout.addMenuOption(FontAwesomeIcon.USER_CIRCLE_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuUserProfile").replace("#LINESEPARATOR#", System.lineSeparator()));
        layout.addMenuOption(FontAwesomeIcon.COG, MessageBundle.getCurrentLanguage().getMessage("main-menuUserSettings").replace("#LINESEPARATOR#", System.lineSeparator()));
        layout.addMenuOption(FontAwesomeIcon.NEWSPAPER_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuNews"));
        layout.addMenuOption(FontAwesomeIcon.PLAY_CIRCLE_ALT, MessageBundle.getCurrentLanguage().getMessage("main-menuServerPicker"));

        //Set close
        layout.setCloseOption(MessageBundle.getCurrentLanguage().getMessage("main-exit"));

        layout.setNews(MPAPI.news().latest());

        //Set right
        layout.setRightSite(MessageBundle.getCurrentLanguage().getMessage("main-findUsAt"));

        //Set version
        layout.setLauncherVersion(((MPLauncher.class.getPackage().getImplementationVersion() == null) ? "DEV" : MPLauncher.class.getPackage().getImplementationVersion()));

        //MainScreen stackpane
        mainStackPane = layout.getMainStackPane();
    }

    @Override
    MainLayout createLayout() {
        return new MainLayout(this);
    }

    public void closeClicked() {
        Platform.exit();
    }

    public void discordLogoClicked() {
        try {
            JFXHelpers.openWebpage(new URI("https://discord.gg/C5pkDan"));
        } catch (URISyntaxException e) {
            logger.error("Failed to open discord invite!", e);
        }
    }

    public void playClicked() {
        InstallerOverlay installerOverlay = new InstallerOverlay(mainStackPane);
        installerOverlay.setStatus("Instalowanie: LIBRARIES");
        installerOverlay.setPercentage(0.571f);
        installerOverlay.setDescription("Zainstalowano 100 spośród 10000 plików.");

        installerOverlay.show();
    }

}
