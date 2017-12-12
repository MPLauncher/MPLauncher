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
package pl.mplauncher.launcher.core.helper;

import javafx.event.EventTarget;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.core.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.core.screen.Screen;

import java.net.URL;

public class GUI {

    private static final Logger logger = LogManager.getLogger(GUI.class);

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

    public static void switchScreen(Class to) {
        Screen screen;

        try {
            screen = (Screen) to.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return;
        }

        screen.initialize();

        String title = "MPLauncher";
        if (screen.getDefaultTitle() != null) {
            title += " - " + screen.getDefaultTitle();
        }

        MPLauncherBootstrap.getStartStage().setTitle(title);
        MPLauncherBootstrap.getStartStage().setScene(screen.layout.getScene());

        screen.layout.getScene().addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            EventTarget target = event.getTarget();
            logger.debug("- - - - - - - - - - - - - - - - - -");
            logger.debug("Target: " + (target != null ? target.getClass().getSimpleName() : event.getClass().getSimpleName()));
            logger.debug("Event Type: " + event.getEventType());
            logger.debug("Component: " + event.getTarget());
            logger.debug("Details: " + event);
            logger.debug("- - - - - - - - - - - - - - - - - -");
        });

        MPLauncherBootstrap.getStartStage().centerOnScreen();
        MPLauncherBootstrap.getStartStage().show();
        MPLauncherBootstrap.getStartStage().toFront();
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(),
                1.0);
    }

}
