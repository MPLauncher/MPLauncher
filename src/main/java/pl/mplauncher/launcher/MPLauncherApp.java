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
package pl.mplauncher.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import pl.mplauncher.launcher.logging.Log4j2OutputStream;

import java.io.PrintStream;

public final class MPLauncherApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Logger bootstrapLogger = (Logger) LogManager.getLogger("Bootstrap");
        Logger launcherLogger = (Logger) LogManager.getLogger("Launcher");

        // logs should be printed only on one stream...
        System.setErr(new PrintStream(new Log4j2OutputStream(bootstrapLogger, Level.INFO)));
        System.setErr(new PrintStream(new Log4j2OutputStream(bootstrapLogger, Level.ERROR)));

        bootstrapLogger.log(Level.INFO, "MPLauncher v2.0.0-dev2.");
        bootstrapLogger.log(Level.INFO, "Our websites: https://mplauncher.pl / " +
                                                "https://github.com/MPLauncher/");
        bootstrapLogger.log(Level.INFO, "Copyright 2017 MPLauncher Team. Licensed under the Apache License, " +
                                                "Version 2.0."); // why warn?

        long took = System.currentTimeMillis();
        MPLauncher launcher = new MPLauncher(stage, launcherLogger);

        try {
            launcher.initialize();
            launcher.start();
        } catch (Exception ex) {
            bootstrapLogger.log(Level.ERROR, "Could not init launcher!", ex);

            System.exit(1);
            return;
        }

        bootstrapLogger.log(Level.INFO, "Started in " + (double) (System.currentTimeMillis() - took) / 1000L
                                                + " second(s).");
    }

}
