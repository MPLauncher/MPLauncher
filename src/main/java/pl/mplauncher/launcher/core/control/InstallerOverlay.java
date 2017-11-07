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
package pl.mplauncher.launcher.core.control;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXProgressBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class InstallerOverlay extends JFXDialog {

    private static final Logger logger = LogManager.getLogger(InstallerOverlay.class);

    private StackPane contentHolder;
    private JFXProgressBar progressBar;
    private Label status;
    private Label progress;
    private Label description;

    private Float currentPercentage;

    /**
     * Creates empty installer overlay.
     */
    public InstallerOverlay() {
        this(null, null, null);
    }

    /**
     * Creates installer overlay and assigns it to the container.
     * @param dialogContainer stackpane container where this overlay will be.
     */
    public InstallerOverlay(StackPane dialogContainer) {
        this(null, null, null);
        this.setDialogContainer(dialogContainer);
    }

    /**
     * Creates installer overlay.
     * @param status message.
     * @param percentage value.
     * @param description message.
     */
    public InstallerOverlay(String status, Float percentage, String description) {
        initialize();
        if (status != null) { setStatus(status); }
        if (percentage != null) { setPercentage(percentage); }
        if (description != null) { setDescription(description); }
    }

    /**
     * Initializes the overlay.
     */
    private void initialize() {
        this.setOverlayClose(false);
        this.setAlignment(Pos.CENTER);

        contentHolder = new StackPane();
        contentHolder.getStyleClass().add("installerContent");
        contentHolder.setPrefSize(531.0, 104.0);
        contentHolder.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        contentHolder.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        contentHolder.setAlignment(Pos.CENTER_LEFT);

        progressBar = new JFXProgressBar();
        progressBar.setPrefSize(384.0, 9.0);
        StackPane.setMargin(progressBar, new Insets(0, 0, 0, 100.0));


        ImageView logo = new ImageView();
        logo.setFitWidth(64.0);
        logo.setFitHeight(70.0);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("images/logo-cropped.png");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            logo.setImage(image);
        } else {
            logger.error("Couldn't set installer overlay logo!");
        }
        StackPane.setMargin(logo, new Insets(0, 0, 0, 48));

        status = new Label();
        status.getStyleClass().add("fontSemiBold");
        StackPane.setAlignment(status, Pos.TOP_LEFT);
        StackPane.setMargin(status, new Insets(32.0, 0, 0, 121.0));

        progress = new Label();
        progress.setTextAlignment(TextAlignment.RIGHT);
        progress.getStyleClass().add("fontSemiBold");
        StackPane.setAlignment(progress, Pos.TOP_RIGHT);
        StackPane.setMargin(progress, new Insets(32.0, 47.0, 0, 0));

        description = new Label();
        description.getStyleClass().add("fontLight");
        StackPane.setAlignment(description, Pos.BOTTOM_LEFT);
        StackPane.setMargin(description, new Insets(0.0, 0.0, 32.0, 120.0));

        contentHolder.getChildren().addAll(progressBar, logo, status, progress, description);

        this.setContent(contentHolder);
    }

    /**
     * Returns content holder.
     * @return content holder.
     */
    public StackPane getContentHolder() {
        return this.contentHolder;
    }

    /**
     * Returns status message.
     * @return status message
     */
    public String getStatus() {
        return this.status.getText();
    }

    /**
     * Sets the status message.
     * @param status message
     */
    public void setStatus(String status) {
        if (status != null) {
            this.status.setText(status.toUpperCase());
        } else {
            logger.warn("Tried to set status to null!");
        }
    }

    /**
     * Returns current percentage.
     * @return current percentage in float value between 0.0f and 1.0f.
     */
    public Float getPercentage() {
        return currentPercentage;
    }

    /**
     * Sets the percentage.
     * @param percentage value from 0.0f to 1.0f
     */
    public void setPercentage(Float percentage) {
        if (percentage != null) {
            if (percentage > 1.0f) { currentPercentage = 1.0f; }
            else if (percentage < 0.0f) { currentPercentage = 0.0f; }
            else { currentPercentage = percentage; }

            progress.setText((currentPercentage * 100) + "%");
            progressBar.setProgress(currentPercentage);
        } else {
            logger.warn("Tried to set percentage to null!");
        }
    }

    /**
     * Returns description message.
     * @return description message.
     */
    public String getDescription() {
        return this.description.getText();
    }

    /**
     * Sets the description message.
     * @param description message.
     */
    public void setDescription(String description) {
        if (description != null) {
            this.description.setText(description.toUpperCase());
        } else {
            logger.warn("Tried to set description to null!");
        }
    }
}
