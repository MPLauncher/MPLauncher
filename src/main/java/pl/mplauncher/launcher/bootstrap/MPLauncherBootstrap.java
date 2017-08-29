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
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.helpers.FormSwitcher;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class MPLauncherBootstrap extends Application {

    public static Stage start_stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(MPLauncherBootstrap::showError);

        start_stage = stage;
        // Future use: MPLauncher launcher = new MPLauncher();

        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));

        /*
          ToDo
          - Initialize logger
          - Initialize data
          - Initialize config
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

        stage.initStyle(StageStyle.TRANSPARENT);

        if (FormSwitcher.initializeForms()) {
            System.out.println("Forms has been successfully initialized.");
            FormSwitcher.switchTo(FormSwitcher.Form.LOGIN);
            /*
                Login: Test
                Passw: ForMe
                For launching main form!
             */
        }
    }

    private static void showError(Thread t, Throwable e) {
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

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);
            alert.show();
        } else {
            e.printStackTrace();
        }
    }
}
