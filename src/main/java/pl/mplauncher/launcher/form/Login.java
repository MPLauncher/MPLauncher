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
import pl.mplauncher.launcher.api.config.ConfigUtils;
import pl.mplauncher.launcher.api.config.templates.Users;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.helper.FormSwitcher;
import pl.mplauncher.launcher.helper.JFXHelpers;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class Login extends LoginDesigner {

    //TODO: Make API to config so we can read this data anywhere in the launcher.
    private Users usersInstance;

    private static double xOffset;
    private static double yOffset;

    public void initialize() {
        //Form
        initializeComponent();

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

        //Events
        closeButton.setOnAction(event -> onCloseAction());
        premiumButton.setOnAction(event -> onPremiumSelected());
        nonpremiumButton.setOnAction(event -> onNonPremiumSelected());
        loginField.setOnAction(event -> onLoginAction());
        passwordField.setOnAction(event -> onLoginAction());
        loginButton.setOnAction(event -> onLoginAction());
        termsHyperlink.setOnAction(event -> onTermsAction());

        // Load login data //
        usersInstance = ConfigUtils.loadConfig(new File(ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.CONFIG) + File.separator + "users.yml"), Users.class);

        if (usersInstance.users.isEmpty()) {
            // Show login fields
        } else {
            // Show profiles to select
        }
    }

    private void onCloseAction() {
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> Platform.exit());
    }

    private void onPremiumSelected() {
        if (!premiumButton.getStyleClass().contains("accountTypeSelected")) {
            premiumButton.getStyleClass().setAll("accountType", "accountTypeSelected");
            nonpremiumButton.getStyleClass().setAll("accountType");
            loginField.setPromptText(MessageBundle.getCurrentLanguage().getMessage("login-formPremiumUsername"));

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
            loginField.setPromptText(MessageBundle.getCurrentLanguage().getMessage("login-formNonPremiumUsername"));

            JFXHelpers.fadeTransition(Duration.millis(250), premiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), nonpremiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), passwordField, 1.0, 0.0, actionEvent -> passwordField.setVisible(false));
        }
    }

    private void onLoginAction() {
        System.out.println("*** LOGIN CLICKED ***");

        if (loginField.getText().length() == 0) {
            if (passwordField.isVisible()) {
                snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessagePremiumNoNickname"), 3000);
            } else {
                snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessageNonPremiumNoNickname"), 3000);
            }
        } else if (passwordField.isVisible() && passwordField.getText().length() == 0) {
            snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessagePremiumNoPassword"), 3000);
        } else {
            disableActions(true);
            setLoggingIn(true);

            System.out.println("Type: " + ((passwordField.isVisible()) ? "PREMIUM" : "NON-PREMIUM"));
            System.out.println("Login: " + loginField.getText());
            if (passwordField.isVisible()) {
                System.out.println("Password: " + passwordField.getText());
            }
            System.out.println("Remember: " + rememberButton.isSelected());

            // Easter EGGS!
            switch (loginField.getText().toLowerCase()) {
                case "ilovemplauncher": {
                    snackBar.show("I love You too!" + System.lineSeparator() + "(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧", 3000);
                    disableActions(false);
                    setLoggingIn(false);
                    break;
                }
                case "ihatemplauncher": {
                    loginField.clear();
                    snackBar.show("I'm giving up!" + System.lineSeparator() + "o(╥﹏╥)o", 3000);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            onCloseAction();
                            timer.cancel();
                        }
                    }, 3500);
                    break;
                }
            }

            if (passwordField.isVisible()) {
                //Premium
                if (loginField.getText().equals("Test") && passwordField.getText().equals("ForMe")) {
                    launchMain();
                } else {
                    snackBar.show("Invalid credentials!", 3000);
                    disableActions(false);
                    setLoggingIn(false);
                }
            } else {
                //NonPremium
                Users.User user = new Users.User(loginField.getText(), rememberButton.isSelected());

                if (usersInstance.users.stream().map(Users.User::getUuid).anyMatch(user.getUuid()::equals)) {
                    snackBar.show("This account was added before.", 3000);
                    disableActions(false);
                    setLoggingIn(false);
                } else {
                    usersInstance.users.add(user);
                    launchMain();
                }
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

    private void launchMain() {
        ConfigUtils.saveConfig(new File(ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.CONFIG) + File.separator + "users.yml"), Users.class, usersInstance);
        JFXHelpers.doublePropertyAnimation(Duration.millis(1000), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> FormSwitcher.switchTo(FormSwitcher.Form.MAIN));
    }
}
