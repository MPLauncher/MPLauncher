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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;

import java.net.URL;

// TODO: maybe not-static?
public class FormSwitcher {

    private static Scene loginScene;
    private static Scene mainScene;

    public enum Form {
        LOGIN,
        MAIN
    }

    public static boolean initializeForms() throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        // Initialize Login.fxml window
        // TODO: Move fxml to in-code generation - not fxml to be more clear and dynamic.
        URL loginForm = cl.getResource("Login.fxml");
        if (loginForm != null) {
            Parent root = FXMLLoader.load(loginForm);
            loginScene = new Scene(root, 304, 416);
            loginScene.setFill(Color.TRANSPARENT);
        } else {
            throw new Exception("Couldn't find login form!");
        }

        // Initialize Main.fxml window
        // TODO: Move fxml to in-code generation - not fxml to be more clear and dynamic
        URL mainForm = cl.getResource("Main.fxml");
        if (mainForm != null) {
            Parent root = FXMLLoader.load(mainForm);
            mainScene = new Scene(root, 1178, 722);
            mainScene.setFill(Color.TRANSPARENT);
        } else {
            throw new Exception("Couldn't find main form!");
        }

        return true;
    }

    public static void switchTo(Form to) {
        switch (to) {
            case LOGIN: {
                MPLauncherBootstrap.start_stage.hide();
                MPLauncherBootstrap.start_stage.setTitle("MPLauncher - Login");
                MPLauncherBootstrap.start_stage.setScene(loginScene);
                MPLauncherBootstrap.start_stage.centerOnScreen();
                MPLauncherBootstrap.start_stage.show();
                break;
            }
            case MAIN: {
                MPLauncherBootstrap.start_stage.hide();
                MPLauncherBootstrap.start_stage.setTitle("MPLauncher - Main");
                MPLauncherBootstrap.start_stage.setScene(mainScene);
                MPLauncherBootstrap.start_stage.centerOnScreen();
                MPLauncherBootstrap.start_stage.show();
                break;
            }
        }
    }

}
