package pl.mplauncher.launcher.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SettingsOverlay extends JFXDialog {

    private static final Logger logger = LogManager.getLogger(SettingsOverlay.class);

    private JFXDialogLayout contentHandler;

    //private StackPane contentHandler;
    private Label windowTitle;
    private JFXButton saveButton;
    private JFXRippler closeButton;

    public SettingsOverlay() {
        this(null);
    }

    public SettingsOverlay(StackPane dialogContainer) {
        initialize();
        if (dialogContainer != null) {
            this.setDialogContainer(dialogContainer);
        }
    }

    private void initialize() {
        this.setOverlayClose(false);
        if (!this.getChildren().isEmpty()) {
            this.getChildren().get(0).getStyleClass().add("settingsContent");
        }

        contentHandler = new JFXDialogLayout();
        contentHandler.setPrefSize(704.0, 373.0);
        contentHandler.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        contentHandler.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        contentHandler.getStyleClass().add("settingsContent");

        // HEADING //
        StackPane header = new StackPane();

        windowTitle = new Label();
        windowTitle.getStyleClass().addAll("fontSemiBold", "fontSize12", "textFillWhite");
        StackPane.setAlignment(windowTitle, Pos.CENTER_LEFT);

        FontAwesomeIconView fa = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
        fa.getStyleClass().add("closeIcon");
        fa.setMouseTransparent(true);

        closeButton = new JFXRippler();
        closeButton.setMaxSize(JFXRippler.USE_PREF_SIZE, JFXRippler.USE_PREF_SIZE);
        closeButton.setMinSize(JFXRippler.USE_PREF_SIZE, JFXRippler.USE_PREF_SIZE);
        closeButton.setOnMouseClicked(event -> this.close());
        closeButton.setControl(fa);
        StackPane.setAlignment(closeButton, Pos.CENTER_RIGHT);

        header.getChildren().addAll(windowTitle, closeButton);

        contentHandler.setHeading(header);

        // BODY //

        // ACTIONS //
        saveButton = new JFXButton();
        saveButton.setText("ZAPISZ");
        saveButton.setPrefSize(100.0, 30.0);
        saveButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "specialButton");

        contentHandler.setActions(saveButton);

        //StackPane.setAlignment(saveButton, Pos.BOTTOM_RIGHT);
        //StackPane.setMargin(saveButton, new Insets(0.0, 17.0, 26.0, 0.0));

        //contentHandler.getChildren().addAll(windowTitle, closeButton, saveButton);
        this.setContent(contentHandler);
    }

    public StackPane getContentHandler() {
        return contentHandler;
    }

    public String getWindowTitle() {
        return windowTitle.getText();
    }

    public void setWindowTitle(String title) {
        if (title != null) {
            windowTitle.setText(title.toUpperCase());
        } else {
            logger.warn("Tried to set window title to null!");
        }
    }

    public boolean isSaveButtonEnabled() {
        return !saveButton.isDisabled();
    }

    public void setSaveButtonEnabled(boolean enabled) {
        saveButton.setDisable(!enabled);
    }
}
