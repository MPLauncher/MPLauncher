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
package pl.mplauncher.launcher.bootstrap;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mplauncher.launcher.MPLauncher;

import java.time.LocalDateTime;

public class MPLauncherBootstrap extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MPLauncher launcher = new MPLauncher();

        /**
         * ToDo
         * - Initialize logger
         * - Initialize data
         * - Initialize config
         * - Initialize login screen
         */

        // Important things on the beginning of the log
        System.out.println("App started on: " + LocalDateTime.now());
        System.out.println("App version: " + MPLauncher.class.getPackage().getImplementationVersion());
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS Arch: " + System.getProperty("os.arch"));
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("------------- STARTED LOGGING THE APP -------------");

    }
}
