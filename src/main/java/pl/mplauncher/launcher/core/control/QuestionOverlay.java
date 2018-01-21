/*
 * Copyright 2017-2018 MPLauncher Team
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.helper.JFXHelpers;

public class QuestionOverlay extends Stage {

    public enum DialogType {
        Ok,
        YesNo,
        OkCancel
    }

    public enum DialogResult {
        Yes,
        No,
        Ok,
        Cancel
    }

    private boolean accepted;
    private DialogType dialogType;

    public QuestionOverlay(DialogType dialogType, String dialogTitle, String dialogContent) {
        this.dialogType = dialogType;

        JFXDialogLayout content = new JFXDialogLayout();
        Scene scene = new Scene(content);
        scene.setFill(Color.TRANSPARENT);

        this.getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo-cropped.png")));
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);

        Text title = new Text(dialogTitle);
        title.setFont(new Font("Montserrat SemiBold", 13));

        content.setHeading(title);

        GridPane root = new GridPane();
        root.setPrefWidth(437.0);
        root.setVgap(20.0);
        root.setHgap(14.0);

        Label content2 = new Label(dialogContent);
        content2.setFont(new Font("Montserrat Regular", 10));

        HBox okParent = new HBox();
        okParent.setSpacing(10.0);
        okParent.setAlignment(Pos.CENTER_RIGHT);

        JFXButton first = new JFXButton();
        first.setPrefWidth(70.0);
        first.setPrefHeight(30.0);
        first.setButtonType(JFXButton.ButtonType.RAISED);
        first.setOnAction(action -> { accepted = true; JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 0.0, event -> this.close()); });

        JFXButton second = new JFXButton();
        second.setPrefWidth(70.0);
        second.setPrefHeight(30.0);
        second.setOnAction(action -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 0.0, event -> this.close()));

        switch (dialogType) {
            case Ok: {
                first.setText(MessageBundle.getCurrentLanguage().getMessage("general-ok"));
                okParent.getChildren().addAll(first);
                break;
            }
            case OkCancel: {
                first.setText(MessageBundle.getCurrentLanguage().getMessage("general-ok"));
                second.setText(MessageBundle.getCurrentLanguage().getMessage("general-cancel"));
                okParent.getChildren().addAll(first, second);
                break;
            }
            case YesNo: {
                first.setText(MessageBundle.getCurrentLanguage().getMessage("general-yes"));
                second.setText(MessageBundle.getCurrentLanguage().getMessage("general-no"));
                okParent.getChildren().addAll(first, second);
                break;
            }
        }

        content.setBody(content2);
        content.setActions(okParent);

        this.setAlwaysOnTop(true);
        this.centerOnScreen();
        this.setOpacity(0.0);
        this.setOnShown(event -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 1.0));
        this.showAndWait();
    }

    public DialogResult getResult() {
        switch (dialogType) {
            case Ok: {
                return DialogResult.Ok;
            }
            case OkCancel: {
                return accepted ? DialogResult.Ok : DialogResult.Cancel;
            }
            case YesNo: {
                return accepted ? DialogResult.Yes : DialogResult.No;
            }
        }
        return null;
    }
}
