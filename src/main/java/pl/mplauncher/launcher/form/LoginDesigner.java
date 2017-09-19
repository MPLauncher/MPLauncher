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
        Pane pane = new Pane();
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

        URL style = getClass().getClassLoader().getResource("style_login.css");
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
        StackPane.setMargin(pane, new Insets(16.0, 0.0, 0.0, 0.0));

        closeButton.setAlignment(Pos.TOP_LEFT);
        closeButton.setEllipsisString("");
        closeButton.setLayoutX(276.0);
        closeButton.setLayoutY(2.0);
        closeButton.getStyleClass().add("closeHyperlink");
        closeButton.setText("X");
        closeButton.setTextOverrun(OverrunStyle.CLIP);

        premiumButton.setLayoutX(18.0);
        premiumButton.setLayoutY(50.0);
        premiumButton.getStyleClass().add("accountType");
        premiumButton.setText(currentLanguage.getMessage("login-premium"));

        premiumButtonLine.setEndX(74.74);
        premiumButtonLine.setLayoutX(18.0);
        premiumButtonLine.setLayoutY(72.0);
        premiumButtonLine.getStyleClass().add("lineType");

        nonpremiumButton.setLayoutX(168.0);
        nonpremiumButton.setLayoutY(50.0);
        nonpremiumButton.getStyleClass().add("accountType");
        nonpremiumButton.setText(currentLanguage.getMessage("login-nonPremium"));

        nonpremiumButtonLine.setEndX(116.0);
        nonpremiumButtonLine.setLayoutX(168.0);
        nonpremiumButtonLine.setLayoutY(72.0);
        nonpremiumButtonLine.getStyleClass().add("lineType");

        loginField.setFocusColor(Paint.valueOf("WHITE"));
        loginField.setLayoutX(19.0);
        loginField.setLayoutY(131.0);
        loginField.setPrefHeight(23.0);
        loginField.setPrefWidth(266.0);
        loginField.setPromptText(currentLanguage.getMessage("login-formPremiumUsername"));
        loginField.setLabelFloat(true);
        loginField.getStyleClass().add("input");

        passwordField.setFocusColor(Paint.valueOf("WHITE"));
        passwordField.setLayoutX(19.0);
        passwordField.setLayoutY(185.0);
        passwordField.setPrefHeight(23.0);
        passwordField.setPrefWidth(266.0);
        passwordField.setPromptText(currentLanguage.getMessage("login-formPremiumPassword"));
        passwordField.setLabelFloat(true);
        passwordField.getStyleClass().add("input");

        rememberButton.setLayoutX(2.0);
        rememberButton.setLayoutY(215.0);
        rememberButton.setPrefHeight(36.0);
        rememberButton.setPrefWidth(194.0);
        rememberButton.getStyleClass().add("toggleButton");
        rememberButton.setText(currentLanguage.getMessage("login-formRememberMe"));

        loginSpinner.setLayoutX(139.0);
        loginSpinner.setLayoutY(298.0);
        loginSpinner.setPrefWidth(32.0);

        loginButton.setLayoutX(92.0);
        loginButton.setLayoutY(298.0);
        loginButton.setPrefHeight(32.0);
        loginButton.setPrefWidth(131.0);
        loginButton.getStyleClass().add("loginButton");
        loginButton.setText(currentLanguage.getMessage("login-formLogInButton"));

        termsHyperlink.setLayoutX(104.0);
        termsHyperlink.setLayoutY(340.0);
        termsHyperlink.getStyleClass().add("smallHyperlink");
        termsHyperlink.setText(currentLanguage.getMessage("login-termsOfUse"));

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
