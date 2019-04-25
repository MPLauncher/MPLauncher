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

package pl.mplauncher.launcher.core.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.core.helper.JFXHelpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

public class ErrorOverlay extends Stage {

    private static final Logger logger = LogManager.getLogger(ErrorOverlay.class);

    public ErrorOverlay(Throwable error) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        Scene scene = new Scene(dialogLayout);
        scene.setFill(Color.TRANSPARENT);

        URL style = Thread.currentThread().getContextClassLoader().getResource("styles/main.css");
        if (style != null) {
            scene.getStylesheets().add(style.toExternalForm());
        } else {
            logger.error("Couldn't load CSS style for the ErrorOverlay form!");
        }

        this.getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo-cropped.png")));
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);

        dialogLayout.getStyleClass().add("errorOverlayContent");

        // Heading
        Text title = new Text();
        title.getStyleClass().addAll("fontSemiBold", "fontSize12", "textWhite");
        title.setText("Coś poszło nie tak....".toUpperCase());
        dialogLayout.setHeading(title);

        // Body
        VBox bodyContent = new VBox();
        dialogLayout.setBody(bodyContent);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);

        FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.EXCLAMATION_TRIANGLE, "95.4px");
        HBox.setMargin(fontAwesomeIconView, new Insets(0.0, 31.0, 0.0, 31.0));
        fontAwesomeIconView.getStyleClass().add("errorOverlayIcon");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(vBox, new Insets(0.0, 31.0, 0.0, 0.0));
        Text text1 = new Text();
        text1.setText("Nikt nie jest idealny... ;(");
        text1.getStyleClass().addAll("fontRegular", "fontSize10", "textWhite");

        HBox errorCodeHBox = new HBox();
        Text text2 = new Text();
        text2.setText("Typ błędu: ");
        text2.getStyleClass().addAll("fontRegular", "fontSize10", "textWhite");

        Text errorCode = new Text();
        errorCode.setText(error.getLocalizedMessage());
        errorCode.getStyleClass().addAll("fontSemiBold", "fontSize10", "textWhite");
        HBox.setHgrow(errorCode, Priority.ALWAYS);
        errorCodeHBox.getChildren().addAll(text2, errorCode);

        vBox.getChildren().addAll(text1, errorCodeHBox);

        JFXButton saveLogsButton = new JFXButton();
        saveLogsButton.setMinSize(JFXButton.USE_PREF_SIZE, JFXButton.USE_PREF_SIZE);
        saveLogsButton.setPrefSize(124.0, 30.0);
        saveLogsButton.setText("ZAPISZ LOGI");
        HBox.setMargin(saveLogsButton, new Insets(0.0, 9.0, 0.0, 9.0));
        saveLogsButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "borderedButton");

        JFXButton closeButton = new JFXButton();
        closeButton.setMinSize(JFXButton.USE_PREF_SIZE, JFXButton.USE_PREF_SIZE);
        closeButton.setPrefSize(100.0, 30.0);
        closeButton.setText("ZAMKNIJ");
        HBox.setMargin(closeButton, new Insets(0.0, 31.0, 0.0, 9.0));
        closeButton.setOnAction(action -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 0.0, event -> this.close()));
        closeButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "filledButton");

        VBox.setVgrow(hBox, Priority.ALWAYS);
        hBox.getChildren().addAll(fontAwesomeIconView, vBox, saveLogsButton, closeButton);

        JFXTextArea errorLog = new JFXTextArea();
        errorLog.setEditable(false);
        errorLog.setWrapText(false);
        errorLog.setVisible(false);
        // Set errorLog
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);
        errorLog.setText(sw.toString());
        //
        errorLog.setPrefHeight(140.0);
        errorLog.managedProperty().bind(errorLog.visibleProperty()); // Hide when not visible
        VBox.setVgrow(errorLog, Priority.ALWAYS);
        errorLog.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

        bodyContent.getChildren().addAll(hBox, errorLog);

        // Actions
        JFXRippler jfxRippler = new JFXRippler();
        FontAwesomeIconView showLogs = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_DOWN, "15px");
        jfxRippler.setControl(showLogs);
        jfxRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        jfxRippler.setOnMouseClicked((mouseEvent) -> {
            if (showLogs.getGlyphName().equalsIgnoreCase("CHEVRON_CIRCLE_DOWN")) {
                errorLog.setVisible(true);
                this.getScene().getWindow().sizeToScene();
                showLogs.setIcon(FontAwesomeIcon.CHEVRON_CIRCLE_UP);
            } else {
                errorLog.setVisible(false);
                this.getScene().getWindow().sizeToScene();
                showLogs.setIcon(FontAwesomeIcon.CHEVRON_CIRCLE_DOWN);
            }
        });
        showLogs.getStyleClass().add("errorOverlayIcon");
        dialogLayout.setActions(jfxRippler);

        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.centerOnScreen();
        this.setOpacity(0.0);
        this.setOnShown(event -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 1.0));
        this.show();
        //this.showAndWait();
    }

}
