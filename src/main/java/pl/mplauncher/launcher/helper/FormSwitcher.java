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

import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.form.Login;
import pl.mplauncher.launcher.form.Main;

public class FormSwitcher {

    public enum Form {
        LOGIN,
        MAIN
    }

    public static void switchTo(Form to) {
        switch (to) {
            case LOGIN: {
                MPLauncherBootstrap.getStartStage().hide();
                MPLauncherBootstrap.getStartStage().setTitle("MPLauncher - Login");
                MPLauncherBootstrap.getStartStage().setScene(new Login().getLoginScene());
                MPLauncherBootstrap.getStartStage().centerOnScreen();
                MPLauncherBootstrap.getStartStage().show();
                break;
            }
            case MAIN: {
                MPLauncherBootstrap.getStartStage().hide();
                MPLauncherBootstrap.getStartStage().setTitle("MPLauncher - Main");
                MPLauncherBootstrap.getStartStage().setScene(new Main().getMainScene());
                MPLauncherBootstrap.getStartStage().centerOnScreen();
                MPLauncherBootstrap.getStartStage().show();
                break;
            }
        }
    }

}
