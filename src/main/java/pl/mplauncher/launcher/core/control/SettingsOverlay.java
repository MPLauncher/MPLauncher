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

        SettingOption option_1 = new SettingOption("DYNAMICZNA IKONA/LOGO", new JFXCheckBox());
        GridPane.setColumnIndex(option_1, 0);
        GridPane.setRowIndex(option_1, 0);
        GridPane.setMargin(option_1, new Insets(0.0, 10.0, 0.0, 0.0));

        ((JFXCheckBox) option_1.getChild()).setSelected(userConfig.isEnableDynamicIcon());


        SettingOption option_2 = new SettingOption("WYŁĄCZ WYGLĄD EVENTOWY", new JFXCheckBox());
        GridPane.setColumnIndex(option_2, 0);
        GridPane.setRowIndex(option_2, 1);
        GridPane.setMargin(option_2, new Insets(0.0, 10.0, 0.0, 0.0));

        ((JFXCheckBox) option_2.getChild()).setSelected(userConfig.isDisableEventTheme());


        SettingOption option_3 = new SettingOption("URUCHAMIAJ Z SYSTEMEM", new JFXCheckBox());
        GridPane.setColumnIndex(option_3, 0);
        GridPane.setRowIndex(option_3, 2);
        GridPane.setMargin(option_3, new Insets(0.0, 10.0, 0.0, 0.0));

        ((JFXCheckBox) option_3.getChild()).setSelected(userConfig.isAutostart());


        SettingOption option_4 = new SettingOption("DEBUGUJ LAUNCHER", new JFXCheckBox());
        GridPane.setColumnIndex(option_4, 0);
        GridPane.setRowIndex(option_4, 3);
        GridPane.setMargin(option_4, new Insets(0.0, 10.0, 0.0, 0.0));

        ((JFXCheckBox) option_4.getChild()).setSelected(userConfig.isDebugApplication());


        JFXComboBox<String> languages = new JFXComboBox<>();
        languages.getItems().addAll("POLSKI", "ANGIELSKI", "BAKA");
        SettingOption option_language = new SettingOption("JĘZYK", languages);
        GridPane.setColumnIndex(option_language, 0);
        GridPane.setRowIndex(option_language, 4);
        GridPane.setMargin(option_language, new Insets(0.0, 10.0, 0.0, 0.0));

        languages.setValue(userConfig.getLanguage());


        JFXComboBox<String> themes = new JFXComboBox<>();
        themes.getItems().addAll("NAJLEPSZY", "KAWAII");
        SettingOption option_theme = new SettingOption("MOTYW", themes);
        GridPane.setColumnIndex(option_theme, 0);
        GridPane.setRowIndex(option_theme, 5);
        GridPane.setMargin(option_theme, new Insets(0.0, 10.0, 0.0, 0.0));

        themes.setValue(userConfig.getTheme());


        SettingOption option_5 = new SettingOption("NIE PRZECHWYTUJ LOGÓW KLIENTA", new JFXCheckBox());
        GridPane.setColumnIndex(option_5, 1);
        GridPane.setRowIndex(option_5, 0);
        GridPane.setMargin(option_5, new Insets(0.0, 0.0, 0.0, 10.0));

        ((JFXCheckBox) option_5.getChild()).setSelected(userConfig.isDisableLogCatch());


        SettingOption option_6 = new SettingOption("KONSOLA MINECRAFTA", new JFXCheckBox());
        GridPane.setColumnIndex(option_6, 1);
        GridPane.setRowIndex(option_6, 1);
        GridPane.setMargin(option_6, new Insets(0.0, 0.0, 0.0, 10.0));

        ((JFXCheckBox) option_6.getChild()).setSelected(userConfig.isShowMinecraftConsole());


        SettingOption option_7 = new SettingOption("MINIMALIZUJ DO TRAYA", new JFXCheckBox());
        GridPane.setColumnIndex(option_7, 1);
        GridPane.setRowIndex(option_7, 2);
        GridPane.setMargin(option_7, new Insets(0.0, 0.0, 0.0, 10.0));

        ((JFXCheckBox) option_7.getChild()).setSelected(userConfig.isMinimizeToTray());

        bodyGridPane.getChildren().addAll(option_1, option_2, option_3, option_4, option_language, option_theme,
                option_5, option_6, option_7);
        body.getChildren().add(bodyGridPane);

        contentHandler.setBody(body);

        // ACTIONS //
        saveButton = new JFXButton();
        saveButton.setText(MessageBundle.getCurrentLanguage().getMessage("general-save"));
        saveButton.setPrefSize(100.0, 30.0);
        saveButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "specialButton");

        contentHandler.setActions(saveButton);

        saveButton.setOnAction(actionEvent -> {
            userConfig.setLanguage(languages.getSelectionModel().getSelectedItem());
            userConfig.setTheme(themes.getSelectionModel().getSelectedItem());
            userConfig.setEnableDynamicIcon(((JFXCheckBox) option_1.getChild()).isSelected());
            userConfig.setDisableEventTheme(((JFXCheckBox) option_2.getChild()).isSelected());
            userConfig.setAutostart(((JFXCheckBox) option_3.getChild()).isSelected());
            userConfig.setDebugApplication(((JFXCheckBox) option_4.getChild()).isSelected());
            userConfig.setDisableLogCatch(((JFXCheckBox) option_5.getChild()).isSelected());
            userConfig.setShowMinecraftConsole(((JFXCheckBox) option_6.getChild()).isSelected());
            userConfig.setMinimizeToTray(((JFXCheckBox) option_7.getChild()).isSelected());

            userConfig.save();
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
