/*
 * Copyright 2017-2019 MPLauncher Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mplauncher.launcher.core.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;
import javafx.application.Platform;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.bootstrap.MPLauncherBootstrap;
import pl.mplauncher.launcher.core.config.ConfigurationFactory;
import pl.mplauncher.launcher.core.config.UserProfile;
import pl.mplauncher.launcher.core.helper.ApplicationFactory;
import pl.mplauncher.launcher.core.helper.GUI;
import pl.mplauncher.launcher.core.helper.JFXHelpers;
import pl.mplauncher.launcher.core.helper.Placeholder;
import pl.mplauncher.launcher.core.screen.layout.component.UserAccountListItem;
import pl.mplauncher.launcher.core.screen.layout.LoginLayout;

import java.net.URI;
import java.net.URISyntaxException;

public class LoginScreen extends Screen<LoginLayout> {

    private UserProfile.Type accountTypeSelected = UserProfile.Type.NONPREMIUM;

    @Override
    public String getDefaultTitle() {
        return "Login";
    }

    public void initialize() {
        layout.initialize();

        //Events
        layout.closeButton.setOnAction(event -> onCloseAction());
        layout.premiumButton.setOnAction(event -> onPremiumSelected());
        layout.nonpremiumButton.setOnAction(event -> onNonPremiumSelected());
        layout.loginField.setOnAction(event -> onLoginAction());
        layout.passwordField.setOnAction(event -> onLoginAction());
        layout.loginButton.setOnAction(event -> onLoginAction());
        layout.termsHyperlink.setOnAction(event -> onTermsAction());

        if (!ConfigurationFactory.getUsersRepository().getAll().isEmpty()) {
            layout.switchToAccountList();
            for (UserProfile user : ConfigurationFactory.getUsersRepository().getAll()) {
                layout.accountList.getItems().add(new UserAccountListItem(user));
            }
        }
    }

    private void onPremiumSelected() {
        switchAccountType(UserProfile.Type.PREMIUM);

        layout.passwordField.setVisible(true);
        JFXHelpers.fadeTransition(Duration.millis(250), layout.passwordField, 0.0, 1.0);
    }

    private void onNonPremiumSelected() {
        switchAccountType(UserProfile.Type.NONPREMIUM);

        JFXHelpers.fadeTransition(Duration.millis(250), layout.passwordField, 1.0, 0.0, actionEvent -> layout.passwordField.setVisible(false));
    }

    private void switchAccountType(UserProfile.Type type) {
        boolean isPremium = (type == UserProfile.Type.PREMIUM);
        accountTypeSelected = type;

        String promptTextKey = (isPremium ? "login-formPremiumUsername" : "login-formNonPremiumUsername");

        JFXButton activeButton = (isPremium ? layout.premiumButton : layout.nonpremiumButton);
        JFXButton otherButton = (!isPremium ? layout.premiumButton : layout.nonpremiumButton);

        Line activeButtonLine = (isPremium ? layout.premiumButtonLine : layout.nonpremiumButtonLine);
        Line otherButtonLine = (!isPremium ? layout.premiumButtonLine : layout.nonpremiumButtonLine);

        if (!activeButton.getStyleClass().contains("accountTypeSelected")) {
            activeButton.getStyleClass().add("accountTypeSelected");
            otherButton.getStyleClass().remove("accountTypeSelected");
        }

        layout.loginField.setPromptText(MessageBundle.getCurrentLanguage().getMessage(promptTextKey));
        JFXHelpers.fadeTransition(Duration.millis(250), otherButtonLine, 1.0, 0.0);
        JFXHelpers.fadeTransition(Duration.millis(250), activeButtonLine, 0.0, 1.0);
    }

    private String checkFields(String login, String password) {
        if (login.isEmpty()) {
            if (accountTypeSelected == UserProfile.Type.PREMIUM) {
                return "PremiumNoNickname";
            } else {
                return "NonPremiumNoNickname";
            }
        }

        if (accountTypeSelected == UserProfile.Type.PREMIUM && password.isEmpty()) {
            return "PremiumNoPassword";
        }

        return null;
    }

    private void onLoginAction() {
        MessageBundle currentLang = MessageBundle.getCurrentLanguage();

        if (layout.loginPane.isVisible()) {
            String login = layout.loginField.getText();
            String password = layout.passwordField.getText();

            String checkResult = checkFields(login, password);
            if (checkResult != null) {
                layout.snackBar.enqueue(new SnackbarEvent(new JFXSnackbarLayout(currentLang.getMessage("login-toastMessage" + checkResult)), new Duration(3000), null));
                return;
            }

            layout.disableActions(true);
            layout.setLoggingIn(true);


            UserProfile user;

            // Handle login
            if (accountTypeSelected == UserProfile.Type.PREMIUM) {
                if (Placeholder.handleLogin(login, password)) {
                    user = Placeholder.getTestUser();
                } else {
                    layout.snackBar.enqueue(new SnackbarEvent(new JFXSnackbarLayout("Invalid credentials!"), new Duration(3000), null));
                    layout.disableActions(false);
                    layout.setLoggingIn(false);
                    return;
                }
            } else {
                user = new UserProfile(layout.loginField.getText(), layout.rememberButton.isSelected());
            }


            // Handle profile saving
            if (layout.rememberButton.isSelected()) {
                if (ApplicationFactory.getUsersManager().hasUser(user.getUUID())) {
                    layout.snackBar.enqueue(new SnackbarEvent(new JFXSnackbarLayout("This account was added before."), new Duration(3000), null));
                    layout.disableActions(false);
                    layout.setLoggingIn(false);
                    return;
                } else {
                    ConfigurationFactory.getUsersRepository().getAll().add(user);
                }
            }

            ApplicationFactory.getUsersManager().setCurrentProfile(user);
            launchMain();

        } else {
            // Handle profile loading
            if (layout.accountList.getSelectionModel().getSelectedIndex() != -1) {
                UserProfile profile = layout.accountList.getSelectionModel().getSelectedItem().profile;
                ApplicationFactory.getUsersManager().setCurrentProfile(profile);
                launchMain();
            } else {
                layout.snackBar.enqueue(new SnackbarEvent(new JFXSnackbarLayout("Select an account!"), new Duration(3000), null));
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

    private void onCloseAction() {
        if (!ConfigurationFactory.getUsersRepository().getAll().isEmpty() && layout.loginPane.isVisible()) {
            layout.switchToAccountList();
            return;
        }

        JFXHelpers.doublePropertyAnimation(Duration.millis(500),
                MPLauncherBootstrap.getStartStage().opacityProperty(),
                0.0,
                event -> Platform.exit());
    }

    private void launchMain() {
        ConfigurationFactory.getUsersRepository().save();
        JFXHelpers.doublePropertyAnimation(Duration.millis(1000),
                MPLauncherBootstrap.getStartStage().opacityProperty(),
                0.0,
                event -> GUI.switchScreen(MainScreen.class));
    }

    @Override
    LoginLayout createLayout() {
        return new LoginLayout(this);
    }

}
