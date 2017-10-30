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

import com.google.common.io.Files;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.config.ConfigUtils;
import pl.mplauncher.launcher.config.AppConfiguration;
import pl.mplauncher.launcher.config.ConfigurationFactory;
import pl.mplauncher.launcher.control.ConfigurationOverlay;
import pl.mplauncher.launcher.control.QuestionOverlay;
import pl.mplauncher.launcher.helper.FormSwitcher;
import pl.mplauncher.launcher.api.i18n.MessageBundleIO;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;

public class MPLauncherBootstrap extends Application {

    private static Stage startStage;
    private static Logger logger;

    public static void main(String[] args) {
        try {
            MessageBundleIO.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Thread.setDefaultUncaughtExceptionHandler(MPLauncherBootstrap::showError);
        startStage = stage;

        // Initializing the app setup and logger.
        AppConfiguration app = ConfigurationFactory.getAppSetup();
//        AppSetup.initialize();
        logger = LogManager.getLogger(MPLauncherBootstrap.class);

        // Important things on the beginning of the log
        logger.info("------------- LOGGER INITIALIZED -------------");
        logger.info("App started on: " + LocalDateTime.now());
        logger.info("------------- ------------------ -------------");
        logger.info("App version: " + ((MPLauncher.class.getPackage().getImplementationVersion() == null) ? "DEV" : MPLauncher.class.getPackage().getImplementationVersion()));
        logger.info("Java version: " + System.getProperty("java.version"));
        logger.info("OS Arch: " + System.getProperty("os.arch"));
        logger.info("OS Name: " + System.getProperty("os.name"));
        logger.info("OS Version: " + System.getProperty("os.version"));
        logger.info("Working directory: " + System.getProperty("user.dir"));
        logger.info("------------- STARTED LOGGING THE APP -------------");

        // ********* DATA CONFIGURE ********* //

        if (!ConfigUtils.isGlobalConfigExists()) {
            ConfigurationOverlay configurationOverlay = new ConfigurationOverlay();
            logger.info("User has configured this installation!");
            logger.info("Selected configuration: " + configurationOverlay.getResult());
            logger.info("Location: " + configurationOverlay.getLocation());

            switch (configurationOverlay.getResult()) {
                case Classic: {
                    app.setConfigLocation(ConfigUtils.getNearPcConfigLocation());
                    app.setDataLocation(ConfigUtils.getClassicDataLocation());
                    break;
                }

                case OwnLocation: {
                    app.setConfigLocation(ConfigUtils.getNearPcConfigLocation());
                    app.setDataLocation(new File(configurationOverlay.getLocation() + File.separator + ".mplauncher2.0" + File.separator));
                    break;
                }

                case Portable: {
                    File jarPath = new File(MPLauncherBootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                    File dstPath = new File(configurationOverlay.getLocation() + File.separator + jarPath.getName());
                    try {
                        Files.copy(jarPath, dstPath);
                        File dataLocation = new File(dstPath.getParent() + File.separator + ".mplauncher2.0" + File.separator);

                        if (!dataLocation.exists()) {
                            Validate.isTrue(dataLocation.mkdirs(), "Couldn't mkdirs() on Portable installation.");
                        }

                        app.setConfigLocation(new File(dataLocation + File.separator + "MPLauncher.config"));
                        app.setInstallationType(configurationOverlay.getResult());
                        app.setDataLocation(dataLocation);

                        app.save();

                        new QuestionOverlay(QuestionOverlay.DialogType.Ok, "Instalacja zakończona!",
                                "Launcher możesz uruchamiać za pomocą pliku .jar znajdującego się w wybranej lokalizacji.");

                        Desktop.getDesktop().open(dstPath.getParentFile());
                        System.exit(0);
                    } catch (IOException e) {
                        logger.fatal("Couldn't configure portable installation", e);
                    }
                    break;
                }
            }

            app.setInstallationType(configurationOverlay.getResult());
            app.save();

            if (!app.getDataLocation().exists()) {
                Validate.isTrue(app.getDataLocation().mkdirs(), String.format("Couldn't mkdirs() on %s installation.", configurationOverlay.getResult().name()));
                ConfigurationFactory.getUsers().save();
            } else {
                ConfigurationFactory.getUsers().load();
            }

        } else {
            app = ConfigurationFactory.getAppSetup(true);
            ConfigurationFactory.getUsers(true);

            logger.info("Installation type: " + app.getInstallationType());
            logger.info("Application data location: " + app.getDataLocation());
        }

        if (app.isFirstRun()) {
            //TODO:Download needed files (e.g. lang files)

            app.setFirstRun(false);
        }

        // Future use: MPLauncher launcher = new MPLauncher();

        /*
          ToDo
          - Initialize data
          - Initialize config
         */

        //We are now ready to run our launcher!
        FormSwitcher.initializeGUI();
        FormSwitcher.switchTo(FormSwitcher.Form.LOGIN);
        /*
            Login: Test
            Passw: ForMe
            For launching main form!
         */
    }

    private static void showError(Thread t, Throwable e) {
        logger.error("I've got an exception!", e);

        if (Platform.isFxApplicationThread()) {
            // Window with error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unhandled Exception");
            alert.setHeaderText("I've got an unhandled error while making you happy :c");
            alert.setContentText(
                    "The exception was: " + e.getLocalizedMessage() + System.lineSeparator() +
                    "On Thread: " + t.getId() + " (" + t.getName() + ").");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            String exceptionStackTrace = sw.toString();

            Label label = new Label("Exception stacktrace:");

            TextArea textArea = new TextArea(exceptionStackTrace);
            textArea.setEditable(false);
            textArea.setWrapText(false);

            textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);
            alert.show();
        }
    }

    public static Stage getStartStage() {
        return startStage;
    }
}
