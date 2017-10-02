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

import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.api.i18n.MessageBundle;

import java.net.URL;

class LoginDesigner {

    private static final Logger logger = LogManager.getLogger(LoginDesigner.class);

    //Elements
    private Scene loginScene;
    JFXSnackbar snackBar;
    StackPane stackPane;
    Hyperlink closeButton;
    JFXButton premiumButton;
    Line premiumButtonLine;
    JFXButton nonpremiumButton;
    Line nonpremiumButtonLine;
    JFXTextField loginField;
    JFXPasswordField passwordField;
    JFXToggleButton rememberButton;
    JFXSpinner loginSpinner;
    JFXButton loginButton;
    Hyperlink termsHyperlink;
    Pane namePane;

    //Initializer
    void initializeComponent() {
        MessageBundle currentLanguage = MessageBundle.getCurrentLanguage();
        VBox loginForm = new VBox();
        this.snackBar = new JFXSnackbar();
        this.stackPane = new StackPane();
        StackPane pane = new StackPane();
        this.closeButton = new Hyperlink();
        this.premiumButton = new JFXButton();
        this.premiumButtonLine = new Line();
        this.nonpremiumButton = new JFXButton();
        this.nonpremiumButtonLine = new Line();
        this.loginField = new JFXTextField();
        this.passwordField = new JFXPasswordField();
        this.rememberButton = new JFXToggleButton();
        this.loginSpinner = new JFXSpinner();
        this.loginButton = new JFXButton();
        this.termsHyperlink = new Hyperlink();
        this.namePane = new Pane();
        TextFlow mplauncher = new TextFlow();
        Text mp = new Text();
        Text launcher = new Text();

        URL style = Thread.currentThread().getContextClassLoader().getResource("style_login.css");
        if (style != null) {
            loginForm.getStylesheets().add(style.toExternalForm());
        } else {
            logger.error("Couldn't load CSS style for the Login form!");
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

        premiumButton.setText(currentLanguage.getMessage("login-premium"));
        premiumButton.getStyleClass().add("accountType");
        StackPane.setAlignment(premiumButton, Pos.TOP_LEFT);
        StackPane.setMargin(premiumButton, new Insets(65.0, 0.0, 0.0, 18.0));

        premiumButtonLine.setEndX(74.74);
        premiumButtonLine.getStyleClass().add("lineType");
        StackPane.setAlignment(premiumButtonLine, Pos.TOP_LEFT);
        StackPane.setMargin(premiumButtonLine, new Insets(82.0, 0.0, 0.0, 17.0));

        nonpremiumButton.getStyleClass().add("accountType");
        nonpremiumButton.setText(currentLanguage.getMessage("login-nonPremium"));
        StackPane.setAlignment(nonpremiumButton, Pos.TOP_RIGHT);
        StackPane.setMargin(nonpremiumButton, new Insets(65.0, 16.0, 0.0, 0.0));

        nonpremiumButtonLine.setEndX(116.0);
        nonpremiumButtonLine.getStyleClass().add("lineType");
        StackPane.setAlignment(nonpremiumButtonLine, Pos.TOP_RIGHT);
        StackPane.setMargin(nonpremiumButtonLine, new Insets(82.0, 16.0, 0.0, 0.0));

        loginField.setFocusColor(Paint.valueOf("WHITE"));
        loginField.setPrefHeight(23.0);
        loginField.setPromptText(currentLanguage.getMessage("login-formPremiumUsername"));
        loginField.setLabelFloat(true);
        loginField.getStyleClass().add("input");
        StackPane.setAlignment(loginField, Pos.TOP_LEFT);
        StackPane.setMargin(loginField, new Insets(121.0, 15.0, 0.0, 20.0));

        passwordField.setFocusColor(Paint.valueOf("WHITE"));
        passwordField.setPrefHeight(23.0);
        passwordField.setPromptText(currentLanguage.getMessage("login-formPremiumPassword"));
        passwordField.setLabelFloat(true);
        passwordField.getStyleClass().add("input");
        StackPane.setAlignment(passwordField, Pos.TOP_LEFT);
        StackPane.setMargin(passwordField, new Insets(176.0, 15.0, 0.0, 20.0));

        rememberButton.setPrefHeight(36.0);
        rememberButton.setText(currentLanguage.getMessage("login-formRememberMe"));
        rememberButton.getStyleClass().add("toggleButton");
        StackPane.setAlignment(rememberButton, Pos.TOP_LEFT);
        StackPane.setMargin(rememberButton, new Insets(205.0, 0.0, 0.0, 15.0));

        loginSpinner.setPrefWidth(32.0);
        StackPane.setMargin(loginSpinner, new Insets(298.0, 0.0, 0.0, 0.0));

        loginButton.setPrefSize(131.0, 32.0);
        loginButton.setText(currentLanguage.getMessage("login-formLogInButton"));
        loginButton.getStyleClass().add("loginButton");
        StackPane.setMargin(loginButton, new Insets(298.0, 0.0, 0.0, 0.0));

        termsHyperlink.setTextAlignment(TextAlignment.CENTER);
        termsHyperlink.setText(currentLanguage.getMessage("login-termsOfUse"));
        termsHyperlink.getStyleClass().add("smallHyperlink");
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
        pane.getChildren().addAll(closeButton, premiumButton, premiumButtonLine, nonpremiumButton, nonpremiumButtonLine,
                loginField, passwordField, rememberButton, loginSpinner, loginButton, termsHyperlink);
        namePane.getChildren().add(mplauncher);
        mplauncher.getChildren().addAll(mp, launcher);

        //Parent
        this.loginScene = new Scene(loginForm, 304, 416);
        this.loginScene.setFill(Color.TRANSPARENT);
    }

    public Scene getLoginScene() {
        return loginScene;
    }

}
