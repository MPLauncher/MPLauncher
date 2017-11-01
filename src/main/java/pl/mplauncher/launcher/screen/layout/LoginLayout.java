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
package pl.mplauncher.launcher.screen.layout;

import com.jfoenix.controls.*;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.helper.JFXHelpers;
import pl.mplauncher.launcher.screen.Screen;
import pl.mplauncher.launcher.screen.component.UserAccountListItem;

import java.net.URL;

public class LoginLayout extends Layout{

    public JFXSnackbar snackBar;
    public StackPane stackPane;
    public Hyperlink closeButton;
    //LoginScreen StackPane
    public StackPane loginPane;
    public JFXButton premiumButton;
    public Line premiumButtonLine;
    public JFXButton nonpremiumButton;
    public Line nonpremiumButtonLine;
    public JFXTextField loginField;
    public JFXPasswordField passwordField;
    public JFXToggleButton rememberButton;
    //Account pane
    private StackPane accountPane;
    public JFXListView<UserAccountListItem> accountList;
    //After stackpanes
    public JFXSpinner loginSpinner;
    public JFXButton loginButton;
    public Hyperlink termsHyperlink;
    public Pane namePane;

    public LoginLayout(Screen screen) {
        super(screen);
    }

    public void initialize() {
        MessageBundle currentLanguage = MessageBundle.getCurrentLanguage();
        VBox loginForm = new VBox();
        this.snackBar = new JFXSnackbar();
        this.stackPane = new StackPane();
        StackPane pane = new StackPane();
        this.closeButton = new Hyperlink();
        //
        this.loginPane = new StackPane();
        this.premiumButton = new JFXButton();
        this.premiumButtonLine = new Line();
        this.nonpremiumButton = new JFXButton();
        this.nonpremiumButtonLine = new Line();
        this.loginField = new JFXTextField();
        this.passwordField = new JFXPasswordField();
        this.rememberButton = new JFXToggleButton();
        //
        this.accountPane = new StackPane();
        VBox forAccount = new VBox();
        this.accountList = new JFXListView<>();
        Hyperlink otherAccount = new Hyperlink();
        //
        this.loginSpinner = new JFXSpinner();
        this.loginButton = new JFXButton();
        this.termsHyperlink = new Hyperlink();
        this.namePane = new Pane();
        TextFlow mplauncher = new TextFlow();
        Text mp = new Text();
        Text launcher = new Text();

        URL style = Thread.currentThread().getContextClassLoader().getResource("styles/login.css");
        if (style != null) {
            loginForm.getStylesheets().add(style.toExternalForm());
        } else {
            screen.logger.error("Couldn't load CSS style for the LoginScreen form!");
        }
        loginForm.getStyleClass().add("loginForm");

        snackBar.setPrefWidth(200.0);

        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPrefSize(200.0, 150.0);

        pane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setPrefSize(294.0, 417.0);
        pane.getStyleClass().add("loginContent");
        pane.setPadding(new Insets(16.0, 0, 0, 0));
        pane.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(pane, new Insets(16.0, 0.0, 0.0, 0.0));

        closeButton.setEllipsisString("");
        closeButton.setText("X");
        closeButton.setTextOverrun(OverrunStyle.CLIP);
        closeButton.getStyleClass().add("closeHyperlink");
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(closeButton, new Insets(-6.0, 15.0, 0.0, 0.0));

        loginPane.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(loginPane, new Insets(60.0, 0.0, 0.0, 0.0));

        premiumButton.setText(currentLanguage.getMessage("login-premium"));
        premiumButton.getStyleClass().add("accountType");
        StackPane.setAlignment(premiumButton, Pos.TOP_LEFT);
        StackPane.setMargin(premiumButton, new Insets(5.0, 0.0, 0.0, 18.0));

        premiumButtonLine.setEndX(74.74);
        premiumButtonLine.getStyleClass().add("lineType");
        StackPane.setAlignment(premiumButtonLine, Pos.TOP_LEFT);
        StackPane.setMargin(premiumButtonLine, new Insets(22.0, 0.0, 0.0, 17.0));

        nonpremiumButton.getStyleClass().add("accountType");
        nonpremiumButton.setText(currentLanguage.getMessage("login-nonPremium"));
        StackPane.setAlignment(nonpremiumButton, Pos.TOP_RIGHT);
        StackPane.setMargin(nonpremiumButton, new Insets(5.0, 16.0, 0.0, 0.0));

        nonpremiumButtonLine.setEndX(116.0);
        nonpremiumButtonLine.getStyleClass().add("lineType");
        StackPane.setAlignment(nonpremiumButtonLine, Pos.TOP_RIGHT);
        StackPane.setMargin(nonpremiumButtonLine, new Insets(22.0, 16.0, 0.0, 0.0));

        loginField.setFocusColor(Paint.valueOf("WHITE"));
        loginField.setPrefHeight(23.0);
        loginField.setPromptText(currentLanguage.getMessage("login-formPremiumUsername"));
        loginField.setLabelFloat(true);
        loginField.getStyleClass().add("input");
        StackPane.setAlignment(loginField, Pos.TOP_LEFT);
        StackPane.setMargin(loginField, new Insets(61.0, 15.0, 0.0, 20.0));

        passwordField.setFocusColor(Paint.valueOf("WHITE"));
        passwordField.setPrefHeight(23.0);
        passwordField.setPromptText(currentLanguage.getMessage("login-formPremiumPassword"));
        passwordField.setLabelFloat(true);
        passwordField.getStyleClass().add("input");
        StackPane.setAlignment(passwordField, Pos.TOP_LEFT);
        StackPane.setMargin(passwordField, new Insets(116.0, 15.0, 0.0, 20.0));

        rememberButton.setPrefHeight(36.0);
        rememberButton.setText(currentLanguage.getMessage("login-formRememberMe"));
        rememberButton.getStyleClass().add("toggleButton");
        StackPane.setAlignment(rememberButton, Pos.TOP_LEFT);
        StackPane.setMargin(rememberButton, new Insets(145.0, 0.0, 0.0, 15.0));

        accountPane.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(accountPane, new Insets(60.0, 0.0, 0.0, 0.0));
        accountPane.setOpacity(0.0);
        accountPane.visibleProperty().bind(loginPane.visibleProperty().not());
        accountPane.managedProperty().bind(loginPane.managedProperty().not());

        accountList.setFixedCellSize(42.0);
        accountList.getItems().addListener((ListChangeListener<UserAccountListItem>) c -> {
            double desiredHeight = (accountList.getItems().size() * accountList.getFixedCellSize()) + 5;
            if (desiredHeight > 190.0) {
                accountList.setPrefHeight(190.0);
            } else {
                accountList.setPrefHeight(desiredHeight);
            }
        });
        accountList.setMaxHeight(JFXListView.USE_PREF_SIZE);
        accountList.getStyleClass().add("accountList");
        StackPane.setMargin(accountList, new Insets(5.0, 5.0, 0.0, 5.0));

        otherAccount.setText("INNE KONTO");
        otherAccount.getStyleClass().add("smallHyperlink");
        otherAccount.setTextAlignment(TextAlignment.LEFT);
        VBox.setMargin(otherAccount, new Insets(0.0, 0.0, 0.0, 13.0));
        otherAccount.setOnAction((actionEvent) ->
                JFXHelpers.doublePropertyAnimation(Duration.millis(500), accountPane.opacityProperty(), 0.0, (actionEvent1) -> {
                    loginPane.setVisible(true);
                    loginPane.setManaged(true);
                    JFXHelpers.doublePropertyAnimation(Duration.millis(500), loginPane.opacityProperty(), 1.0);
                })
        );

        loginSpinner.setRadius(13);
        StackPane.setMargin(loginSpinner, new Insets(298.0, 0.0, 0.0, 0.0));

        loginButton.setPrefSize(131.0, 32.0);
        loginButton.setText(currentLanguage.getMessage("login-formLogInButton"));
        loginButton.getStyleClass().add("loginButton");
        StackPane.setMargin(loginButton, new Insets(298.0, 0.0, 0.0, 0.0));

        termsHyperlink.setTextAlignment(TextAlignment.CENTER);
        termsHyperlink.setText(currentLanguage.getMessage("login-termsOfUse"));
        termsHyperlink.getStyleClass().addAll("smallHyperlink", "textFillLightGray");
        StackPane.setMargin(termsHyperlink, new Insets(342.0, 0.0, 0.0, 0.0));

        namePane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        namePane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        namePane.setPrefSize(304.0, 43.0);
        namePane.getStyleClass().add("loginName");

        mplauncher.setLayoutX(39.0);
        mplauncher.setLayoutY(12.0);
        mplauncher.setLineSpacing(0.6);
        mplauncher.setPrefHeight(9.0);
        mplauncher.setPrefWidth(92.0);
        mplauncher.getStyleClass().add("mplauncher");
        mplauncher.setTextAlignment(TextAlignment.CENTER);

        mp.setText("MP");
        mp.getStyleClass().addAll("textWhite", "textBold");

        launcher.setText("LAUNCHER");
        launcher.getStyleClass().add("textWhite");

        //Children
        loginForm.getChildren().addAll(snackBar, stackPane);
        stackPane.getChildren().addAll(pane, namePane);
        pane.getChildren().addAll(closeButton, loginPane, accountPane, loginSpinner, loginButton, termsHyperlink);
        loginPane.getChildren().addAll(premiumButton, premiumButtonLine, nonpremiumButton, nonpremiumButtonLine,
                loginField, passwordField, rememberButton);
        accountPane.getChildren().add(forAccount);
        namePane.getChildren().add(mplauncher);
        forAccount.getChildren().addAll(accountList, otherAccount);
        mplauncher.getChildren().addAll(mp, launcher);

        //Parent
        this.scene = new Scene(loginForm, 304, 416);
        this.scene.setFill(Color.TRANSPARENT);
    }

    public void switchToAccountList() {
        JFXHelpers.doublePropertyAnimation(Duration.millis(500), loginPane.opacityProperty(), 0.0, (event) -> {
            loginPane.setVisible(false);
            loginPane.setManaged(false);
            JFXHelpers.doublePropertyAnimation(Duration.millis(500), accountPane.opacityProperty(), 1.0);
        });
    }

    public void disableActions(boolean disable) {
        premiumButton.setDisable(disable);
        nonpremiumButton.setDisable(disable);

        closeButton.setDisable(disable);
        loginField.setDisable(disable);
        passwordField.setDisable(disable);
        rememberButton.setDisable(disable);
    }

    public void setLoggingIn(boolean loggingIn) {
        if (loggingIn) {
            JFXHelpers.fadeTransition(Duration.millis(250), loginButton, 1.0, 0.0, actionEvent -> loginButton.setVisible(false));
            JFXHelpers.fadeTransition(Duration.millis(250), loginSpinner, 0.0, 1.0);
        } else {
            JFXHelpers.fadeTransition(Duration.millis(250), loginSpinner, 1.0, 0.0);
            JFXHelpers.fadeTransition(Duration.millis(250), loginButton, 0.0, 1.0, actionEvent -> loginButton.setVisible(true));
        }
    }

}
