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
package pl.mplauncher.launcher.helper;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.screen.LoginScreen;
import pl.mplauncher.launcher.screen.MainScreen;
import pl.mplauncher.launcher.screen.Screen;

import java.io.File;
import java.net.URL;

public class GUI {

    private static final Logger logger = LogManager.getLogger(GUI.class);

    public enum ScreenType {
        LOGIN,
        MAIN
    }

    public static void initialize() {
        MPLauncherBootstrap.getStartStage().getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo.png")));

        final String[] requiredFonts = {
                "Montserrat-Thin.ttf",
                "Montserrat-Light.ttf",
                "Montserrat-Regular.ttf",
                "Montserrat-SemiBold.ttf",
                "Montserrat-Bold.ttf"
        };

        for (String fontFilename : requiredFonts) {
            URL fontURL = Thread.currentThread().getContextClassLoader().getResource("fonts/" + fontFilename);
            if (fontURL != null) {
                Font.loadFont(fontURL.toExternalForm(), 10);
            } else {
                logger.error("Couldn't load " + fontFilename + " font!");
            }
        }

        MPLauncherBootstrap.getStartStage().initStyle(StageStyle.TRANSPARENT);
    }

    public static void switchScreen(ScreenType to) {
        MPLauncherBootstrap.getStartStage().setOpacity(0.0);

        Screen screen = null;

        switch (to) {
            case LOGIN: {
                screen = new LoginScreen();
                break;
            }
            case MAIN: {
                screen = new MainScreen();
                break;
            }
        }

        screen.initialize();

        MPLauncherBootstrap.getStartStage().setTitle("MPLauncher - " + StringUtils.capitalize(to.name().toLowerCase()));
        MPLauncherBootstrap.getStartStage().setScene(screen.layout.getScene());

        MPLauncherBootstrap.getStartStage().centerOnScreen();
        MPLauncherBootstrap.getStartStage().show();
        MPLauncherBootstrap.getStartStage().toFront();
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(),
                1.0);
    }
}
