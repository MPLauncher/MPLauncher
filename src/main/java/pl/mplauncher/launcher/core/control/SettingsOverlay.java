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

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.config.UserSpecificConfiguration;
import pl.mplauncher.launcher.core.helper.ApplicationFactory;
import pl.mplauncher.launcher.core.config.smart.SmartConfiguration;
import pl.mplauncher.launcher.core.config.smart.SmartOptionType;
import pl.mplauncher.launcher.core.config.smart.SmartOption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsOverlay extends JFXDialog {

    private static final Logger logger = LogManager.getLogger(SettingsOverlay.class);

    private JFXDialogLayout contentHandler;
    private Label windowTitle;
    private JFXButton saveButton;

    /**
     * Creates settings overlay.
     */
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

        JFXRippler closeButton = new JFXRippler();
        closeButton.setMaxSize(JFXRippler.USE_PREF_SIZE, JFXRippler.USE_PREF_SIZE);
        closeButton.setMinSize(JFXRippler.USE_PREF_SIZE, JFXRippler.USE_PREF_SIZE);
        closeButton.setOnMouseClicked(event -> this.close());
        closeButton.setControl(fa);
        StackPane.setAlignment(closeButton, Pos.CENTER_RIGHT);

        header.getChildren().addAll(windowTitle, closeButton);

        contentHandler.setHeading(header);

        // BODY //
        StackPane body = new StackPane();

        GridPane bodyGridPane = new GridPane();
        bodyGridPane.setAlignment(Pos.CENTER);

        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(40);
        firstColumnConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(40);
        secondColumnConstraints.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(50);
        rowConstraints.setMaxHeight(USE_PREF_SIZE);

        // 2 columns
        bodyGridPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);

        // 6 rows
        for (int x=0; x<6; x++) {
            bodyGridPane.getRowConstraints().add(rowConstraints);
        }

        UserSpecificConfiguration userConfig = ApplicationFactory.getUsersManager().getCurrentProfile().getConfiguration();
        SmartConfiguration<UserSpecificConfiguration> smartConfiguration = new SmartConfiguration<>(userConfig);
        List<SmartOption> options = smartConfiguration.getOptions();

        Map<SettingOption, SmartOption> guiMapping = new HashMap<>();
        for (int i = 0; i < options.size(); i++) {
            int column = i / 6;
            int row = i % 6;

            SmartOption option = options.get(i);
            SettingOption settingOption = null;
            String displayName = MessageBundle.getCurrentLanguage().getMessage("setting-" + option.getName());

            if (option.getType() == SmartOptionType.CHECKBOX) {
                settingOption = new SettingOption(displayName, new JFXCheckBox());
                ((JFXCheckBox) settingOption.getChild()).setSelected((Boolean) option.getValue());
            } else if (option.getType() == SmartOptionType.LIST) {
                JFXComboBox<String> selectableOptions = new JFXComboBox<>();
                selectableOptions.getItems().addAll(option.getOptions());
                selectableOptions.setValue((String) option.getValue());

                if (option.getValue() == null) {
                    selectableOptions.setValue(option.getOptions()[0]);
                }

                settingOption = new SettingOption(displayName, selectableOptions);
            }

            GridPane.setColumnIndex(settingOption, column);
            GridPane.setRowIndex(settingOption, row);
            GridPane.setMargin(settingOption, new Insets(0.0, 10.0, 0.0, 0.0));

            guiMapping.put(settingOption, option);
            bodyGridPane.getChildren().add(settingOption);
        }

        body.getChildren().add(bodyGridPane);

        contentHandler.setBody(body);

        // ACTIONS //
        saveButton = new JFXButton();
        saveButton.setText(MessageBundle.getCurrentLanguage().getMessage("general-save"));
        saveButton.setPrefSize(100.0, 30.0);
        saveButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "specialButton");

        contentHandler.setActions(saveButton);

        saveButton.setOnAction(actionEvent -> {
            for (Map.Entry<SettingOption, SmartOption> entry : guiMapping.entrySet()) {
                if (entry.getValue().getType() == SmartOptionType.CHECKBOX) {
                    entry.getValue().setValue(((JFXCheckBox)entry.getKey().getChild()).isSelected());
                } else if (entry.getValue().getType() == SmartOptionType.LIST) {
                    entry.getValue().setValue(((JFXComboBox)entry.getKey().getChild()).getSelectionModel().getSelectedItem());
                }
            }

            smartConfiguration.save();
            this.close();
        });

        this.setContent(contentHandler);
    }

    class SettingOption extends StackPane {

        private Node child;

        SettingOption(String info, Node child) {
            this.child = child;

            this.getStyleClass().add("settingOption");
            Label infoLabel = new Label();
            infoLabel.setText(info);
            infoLabel.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite");
            StackPane.setAlignment(infoLabel, Pos.CENTER_LEFT);

            StackPane.setAlignment(child, Pos.CENTER_RIGHT);
            if (child instanceof JFXComboBox) {
                StackPane.setMargin(child, new Insets(0, 7, 0, 0));
                ((JFXComboBox) child).setPrefWidth(122);
                ((JFXComboBox) child).setMaxWidth(USE_PREF_SIZE);
            }

            this.getChildren().addAll(infoLabel, child);
        }

        public Node getChild() {
            return child;
        }
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
