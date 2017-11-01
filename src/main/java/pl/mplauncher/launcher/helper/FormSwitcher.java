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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.form.Login;
import pl.mplauncher.launcher.form.Main;

import java.net.URL;

public class FormSwitcher {

    private static final Logger logger = LogManager.getLogger(FormSwitcher.class);

    public enum Form {
        LOGIN,
        MAIN
    }

    public static void initializeGUI() {
        MPLauncherBootstrap.getStartStage().getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("logo.png")));

        URL montserratThin = Thread.currentThread().getContextClassLoader().getResource("Montserrat-Thin.ttf");
        if (montserratThin != null) {
            Font.loadFont(montserratThin.toExternalForm(), 10);
        } else {
            logger.error("Couldn't load Montserrat Thin font!");
        }

        URL montserratLight = Thread.currentThread().getContextClassLoader().getResource("Montserrat-Light.ttf");
        if (montserratLight != null) {
            Font.loadFont(montserratLight.toExternalForm(), 10);
        } else {
            logger.error("Couldn't load Montserrat Light font!");
        }

        URL montserratRegular = Thread.currentThread().getContextClassLoader().getResource("Montserrat-Regular.ttf");
        if (montserratRegular != null) {
            Font.loadFont(montserratRegular.toExternalForm(), 10);
        } else {
            logger.error("Couldn't load Montserrat Regular font!");
        }

        URL montserratSemiBold = Thread.currentThread().getContextClassLoader().getResource("Montserrat-SemiBold.ttf");
        if (montserratSemiBold != null) {
            Font.loadFont(montserratSemiBold.toExternalForm(), 10);
        } else {
            logger.error("Couldn't load Montserrat SemiBold font!");
        }

        URL montserratBold = Thread.currentThread().getContextClassLoader().getResource("Montserrat-Bold.ttf");
        if (montserratBold != null) {
            Font.loadFont(montserratBold.toExternalForm(), 10);
        } else {
            logger.error("Couldn't load Montserrat Bold font!");
        }

        MPLauncherBootstrap.getStartStage().initStyle(StageStyle.TRANSPARENT);
    }

    public static void switchTo(Form to) {
        MPLauncherBootstrap.getStartStage().setOpacity(0.0);

        switch (to) {
            case LOGIN: {
                Login login = new Login();
                login.initialize();

                MPLauncherBootstrap.getStartStage().setTitle("MPLauncher - Login");
                MPLauncherBootstrap.getStartStage().setScene(login.getLoginScene());
                break;
            }
            case MAIN: {
                Main main = new Main();
                main.initialize();

                MPLauncherBootstrap.getStartStage().setTitle("MPLauncher - Main");
                MPLauncherBootstrap.getStartStage().setScene(main.getMainScene());
                break;
            }
        }

        MPLauncherBootstrap.getStartStage().centerOnScreen();
        MPLauncherBootstrap.getStartStage().show();
        MPLauncherBootstrap.getStartStage().toFront();
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(),
                1.0);
    }
}
