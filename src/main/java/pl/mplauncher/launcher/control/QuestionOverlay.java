package pl.mplauncher.launcher.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.helper.JFXHelpers;

public class QuestionOverlay extends StackPane {

    private boolean Answered;
    private boolean Accepted;

    public enum DialogType {
        YesNo,
        OkCancel,
    }

    //TODO:This is so damn stupid code that it have to be written properly ASAP! It must work like an alert for getting the result.

    public QuestionOverlay(DialogType dialogType, String dialogTitle, String dialogContent) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(this, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);

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
        first.setOnAction(action -> { Answered = true; Accepted = true; JFXHelpers.fadeTransition(Duration.millis(500), this, 1.0, 0.0, event -> dialog.close()); });

        JFXButton second = new JFXButton();
        second.setPrefWidth(70.0);
        second.setPrefHeight(30.0);
        first.setOnAction(action -> { Answered = true; Accepted = false; JFXHelpers.fadeTransition(Duration.millis(500), this, 1.0, 0.0, event -> dialog.close()); });

        if (dialogType == DialogType.OkCancel) {
            first.setText(MessageBundle.getCurrentLanguage().getMessage("general-ok"));
            second.setText(MessageBundle.getCurrentLanguage().getMessage("general-cancel"));
        }
        else if (dialogType == DialogType.YesNo) {
            first.setText(MessageBundle.getCurrentLanguage().getMessage("general-yes"));
            second.setText(MessageBundle.getCurrentLanguage().getMessage("general-no"));
        }

        okParent.getChildren().addAll(first, second);

        content.setBody(content2);
        content.setActions(okParent);

        dialog.show();
    }

    public boolean isResult() {
        return Answered;
    }

    public boolean getResult() {
        return Accepted;
    }

}
