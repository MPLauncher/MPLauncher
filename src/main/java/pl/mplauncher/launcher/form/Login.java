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
package pl.mplauncher.launcher.form;

import javafx.application.Platform;
import javafx.util.Duration;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.helper.FormSwitcher;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.net.URI;
import java.net.URISyntaxException;

public class Login extends LoginDesigner {

    private static double xOffset;
    private static double yOffset;

    public void initialize() {
        snackBar.registerSnackbarContainer(stackPane);
        loginSpinner.setOpacity(0.0);

        loginField.setDisableAnimation(true);
        passwordField.setDisableAnimation(true);

        // PREMIUM activated by default!
        premiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
        nonpremiumButtonLine.setOpacity(0.0);

        // Allow to drag entire app via namePane
        namePane.setOnMousePressed(event -> {
            xOffset = MPLauncherBootstrap.getStartStage().getX() - event.getScreenX();
            yOffset = MPLauncherBootstrap.getStartStage().getY() - event.getScreenY();
        });
        namePane.setOnMouseDragged(event -> {
            MPLauncherBootstrap.getStartStage().setX(event.getScreenX() + xOffset);
            MPLauncherBootstrap.getStartStage().setY(event.getScreenY() + yOffset);
        });

        //Form
        initializeComponent();

        //Events
        closeButton.setOnAction(event -> onCloseAction());
        premiumButton.setOnAction(event -> onPremiumSelected());
        nonpremiumButton.setOnAction(event -> onNonPremiumSelected());
        loginButton.setOnAction(event -> onLoginAction());
        termsHyperlink.setOnAction(event -> onTermsAction());
    }

    private void onCloseAction() {
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> Platform.exit());
    }

    private void onPremiumSelected() {
        if (!premiumButton.getStyleClass().contains("accountTypeSelected")) {
            premiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
            nonpremiumButton.getStyleClass().setAll("accountType");
            loginField.setPromptText("EMAIL / NICK");

            passwordField.setVisible(true);
            JFXHelpers.fadeTransition(Duration.millis(250), nonpremiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), premiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), passwordField, 0.0, 1.0);
        }
    }

    private void onNonPremiumSelected() {
        if (!nonpremiumButton.getStyleClass().contains("accountTypeSelected")) {
            nonpremiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
            premiumButton.getStyleClass().setAll("accountType");
            loginField.setPromptText("NICK");

            JFXHelpers.fadeTransition(Duration.millis(250), premiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), nonpremiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), passwordField, 1.0, 0.0, actionEvent -> passwordField.setVisible(false));
        }
    }

    private void onLoginAction() {
        System.out.println("*** LOGIN CLICKED ***");

        if (loginField.getText().length() == 0) {
            if (passwordField.isVisible()) {
                snackBar.show("Uzupełnij nazwę użytkownika/email.", 3000);
            } else {
                snackBar.show("Uzupełnij swój nick.", 3000);
            }
        } else if (passwordField.isVisible() && passwordField.getText().length() == 0) {
            snackBar.show("Uzupełnij hasło.", 3000);
        } else {
            premiumButton.setDisable(true);
            nonpremiumButton.setDisable(true);

            closeButton.setDisable(true);
            loginField.setDisable(true);
            passwordField.setDisable(true);
            rememberButton.setDisable(true);

            JFXHelpers.fadeTransition(Duration.millis(250), loginButton, 1.0, 0.0, actionEvent -> loginButton.setVisible(false));
            JFXHelpers.fadeTransition(Duration.millis(250), loginSpinner, 0.0, 1.0);

            System.out.println("Type: " + ((passwordField.isVisible()) ? "PREMIUM" : "NON-PREMIUM"));
            System.out.println("Login: " + loginField.getText());
            if (passwordField.isVisible()) {
                System.out.println("Password: " + passwordField.getText());
            }
            System.out.println("Remember: " + rememberButton.isSelected());

            if (loginField.getText().equals("Test") && passwordField.getText().equals("ForMe")) {
                JFXHelpers.doublePropertyAnimation(Duration.millis(1000), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> FormSwitcher.switchTo(FormSwitcher.Form.MAIN));
            }
        }
    }

    private void onTermsAction() {
        try {
            JFXHelpers.openWebpage(new URI("https://mplauncher.pl/?p=tos"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
