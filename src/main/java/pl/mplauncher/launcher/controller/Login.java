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
package pl.mplauncher.launcher.controller;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.helper.FormSwitcher;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.net.URI;
import java.net.URISyntaxException;

public class Login {

    @FXML
    private StackPane stackPane;

    @FXML
    private Pane namePane;

    @FXML
    private Hyperlink closeButton;

    @FXML
    private JFXButton premiumButton;

    @FXML
    private Line premiumButtonLine;

    @FXML
    private JFXButton nonpremiumButton;

    @FXML
    private Line nonpremiumButtonLine;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXToggleButton rememberButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXSpinner loginSpinner;

    @FXML
    private JFXSnackbar snackbar;

    private static double xOffset;
    private static double yOffset;

    @FXML
    public void initialize() {
        snackbar.registerSnackbarContainer(stackPane);

        loginSpinner.setOpacity(0.0);

        loginField.setDisableAnimation(true);
        passwordField.setDisableAnimation(true);

        // Premium activated by default!
        premiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
        nonpremiumButtonLine.setOpacity(0.0);

        // Allow to drag entire app via namePane
        namePane.setOnMousePressed(event -> {
            xOffset = MPLauncherBootstrap.start_stage.getX() - event.getScreenX();
            yOffset = MPLauncherBootstrap.start_stage.getY() - event.getScreenY();
        });
        namePane.setOnMouseDragged(event -> {
            MPLauncherBootstrap.start_stage.setX(event.getScreenX() + xOffset);
            MPLauncherBootstrap.start_stage.setY(event.getScreenY() + yOffset);
        });
    }

    @FXML
    private void closeClicked() {
        Platform.exit();
    }

    @FXML
    private void premiumSelected() {
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

    @FXML
    private void nonpremiumSelected() {
        if (!nonpremiumButton.getStyleClass().contains("accountTypeSelected")) {
            nonpremiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
            premiumButton.getStyleClass().setAll("accountType");
            loginField.setPromptText("NICK");

            JFXHelpers.fadeTransition(Duration.millis(250), premiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), nonpremiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), passwordField, 1.0, 0.0, actionEvent -> passwordField.setVisible(false));
        }
    }

    @FXML
    private void loginClicked() {
        System.out.println("*** LOGIN CLICKED ***");

        if (loginField.getText().length() == 0) {
            if (passwordField.isVisible()) {
                snackbar.show("Uzupełnij nazwę użytkownika/email.", 3000);
            } else {
                snackbar.show("Uzupełnij swój nick.", 3000);
            }
        } else if (passwordField.isVisible() && passwordField.getText().length() == 0) {
            snackbar.show("Uzupełnij hasło.", 3000);
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
            if (passwordField.isVisible()) { System.out.println("Password: " + passwordField.getText()); }
            System.out.println("Remember: " + rememberButton.isSelected());

            if (loginField.getText().equals("Test") && passwordField.getText().equals("ForMe")) {
                FormSwitcher.switchTo(FormSwitcher.Form.MAIN);
            }
        }
    }

    @FXML
    private void termsClicked() {
        try {
            JFXHelpers.openWebpage(new URI("https://mplauncher.pl/?p=tos"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
