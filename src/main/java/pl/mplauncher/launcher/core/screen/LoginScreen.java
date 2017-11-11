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
package pl.mplauncher.launcher.core.screen;

import javafx.application.Platform;
import javafx.util.Duration;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.core.config.ConfigurationFactory;
import pl.mplauncher.launcher.core.config.UserProfile;
import pl.mplauncher.launcher.core.helper.ApplicationFactory;
import pl.mplauncher.launcher.core.helper.GUI;
import pl.mplauncher.launcher.core.helper.JFXHelpers;
import pl.mplauncher.launcher.core.helper.Placeholder;
import pl.mplauncher.launcher.core.screen.component.UserAccountListItem;
import pl.mplauncher.launcher.core.screen.layout.LoginLayout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class LoginScreen extends Screen<LoginLayout> {

    public void initialize() {
        layout.initialize();

        layout.snackBar.registerSnackbarContainer(layout.stackPane);
        layout.loginSpinner.setOpacity(0.0);

        layout.loginField.setDisableAnimation(true);
        layout.passwordField.setDisableAnimation(true);

        // PREMIUM activated by default!
        layout.premiumButton.getStyleClass().add("accountTypeSelected");
        layout.nonpremiumButtonLine.setOpacity(0.0);

        // Allow to drag entire app via namePane
        layout.namePane.setOnMousePressed(event -> {
            layout.xOffset = MPLauncherBootstrap.getStartStage().getX() - event.getScreenX();
            layout.yOffset = MPLauncherBootstrap.getStartStage().getY() - event.getScreenY();
        });
        layout.namePane.setOnMouseDragged(event -> {
            MPLauncherBootstrap.getStartStage().setX(event.getScreenX() + layout.xOffset);
            MPLauncherBootstrap.getStartStage().setY(event.getScreenY() + layout.yOffset);
        });

        //Events
        layout.closeButton.setOnAction(event -> onCloseAction());
        layout.premiumButton.setOnAction(event -> onPremiumSelected());
        layout.nonpremiumButton.setOnAction(event -> onNonPremiumSelected());
        layout.loginField.setOnAction(event -> onLoginAction());
        layout.passwordField.setOnAction(event -> onLoginAction());
        layout.loginButton.setOnAction(event -> onLoginAction());
        layout.termsHyperlink.setOnAction(event -> onTermsAction());

        // Load login data //
        if (!ConfigurationFactory.getUsers().getUsers().isEmpty()) {
            layout.switchToAccountList();
            for (UserProfile user : ConfigurationFactory.getUsers().getUsers()) {
                layout.accountList.getItems().add(new UserAccountListItem(user));
            }
        }
    }

    @Override
    LoginLayout createLayout() {
        return new LoginLayout(this);
    }

    private void onCloseAction() {
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> Platform.exit());
    }

    private void onPremiumSelected() {
        if (!layout.premiumButton.getStyleClass().contains("accountTypeSelected")) {
            layout.premiumButton.getStyleClass().add("accountTypeSelected");
            layout.nonpremiumButton.getStyleClass().remove("accountTypeSelected");
            layout.loginField.setPromptText(MessageBundle.getCurrentLanguage().getMessage("login-formPremiumUsername"));

            layout.passwordField.setVisible(true);
            JFXHelpers.fadeTransition(Duration.millis(250), layout.nonpremiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), layout.premiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), layout.passwordField, 0.0, 1.0);
        }
    }

    private void onNonPremiumSelected() {
        if (!layout.nonpremiumButton.getStyleClass().contains("accountTypeSelected")) {
            layout.nonpremiumButton.getStyleClass().add("accountTypeSelected");
            layout.premiumButton.getStyleClass().remove("accountTypeSelected");
            layout.loginField.setPromptText(MessageBundle.getCurrentLanguage().getMessage("login-formNonPremiumUsername"));

            JFXHelpers.fadeTransition(Duration.millis(250), layout.premiumButtonLine, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), layout.nonpremiumButtonLine, 0.0, 1.0);
            JFXHelpers.fadeTransition(Duration.millis(250), layout.passwordField, 1.0, 0.0, actionEvent -> layout.passwordField.setVisible(false));
        }
    }

    private void onLoginAction() {
        System.out.println("*** LOGIN CLICKED ***");

        if (layout.loginPane.isVisible()) {
            if (layout.loginField.getText().length() == 0) {
                if (layout.passwordField.isVisible()) {
                    layout.snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessagePremiumNoNickname"), 3000);
                } else {
                    layout.snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessageNonPremiumNoNickname"), 3000);
                }
            } else if (layout.passwordField.isVisible() && layout.passwordField.getText().length() == 0) {
                layout.snackBar.show(MessageBundle.getCurrentLanguage().getMessage("login-toastMessagePremiumNoPassword"), 3000);
            } else {
                layout.disableActions(true);
                layout.setLoggingIn(true);

                System.out.println("Type: " + ((layout.passwordField.isVisible()) ? "PREMIUM" : "NON-PREMIUM"));
                System.out.println("LoginScreen: " + layout.loginField.getText());
                if (layout.passwordField.isVisible()) {
                    System.out.println("Password: " + layout.passwordField.getText());
                }
                System.out.println("Remember: " + layout.rememberButton.isSelected());

                UserProfile user = null;
                if (layout.passwordField.isVisible()) {

                    if (Placeholder.handleLogin(layout.loginField.getText(), layout.passwordField.getText())) {
                        user = Placeholder.getTestUser();
                    } else {
                        layout.snackBar.show("Invalid credentials!", 3000);
                        layout.disableActions(false);
                        layout.setLoggingIn(false);
                    }

                } else {
                    //NonPremium
                    user = new UserProfile(layout.loginField.getText(), layout.rememberButton.isSelected());
                }

                if (layout.rememberButton.isSelected() && user != null) {
                    if (ConfigurationFactory.getUsers().getUsers().stream().map(UserProfile::getUUID).anyMatch(user.getUUID()::equals)) {
                        layout.snackBar.show("This account was added before.", 3000);
                        layout.disableActions(false);
                        layout.setLoggingIn(false);
                    } else {
                        ConfigurationFactory.getUsers().getUsers().add(user);
                    }
                }

                ApplicationFactory.getUsersManager().setCurrentProfile(user);
                launchMain();
            }
        } else {
            if (layout.accountList.getSelectionModel().getSelectedIndex() != -1) {
                UserProfile profile = layout.accountList.getSelectionModel().getSelectedItem().profile;
                ApplicationFactory.getUsersManager().setCurrentProfile(profile);
                launchMain();
            } else {
                layout.snackBar.show("Select an account!", 3000);
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
        ConfigurationFactory.getUsers().save();
        JFXHelpers.doublePropertyAnimation(Duration.millis(1000), MPLauncherBootstrap.getStartStage().opacityProperty(), 0.0, event -> GUI.switchScreen(GUI.ScreenType.MAIN));
    }
}
