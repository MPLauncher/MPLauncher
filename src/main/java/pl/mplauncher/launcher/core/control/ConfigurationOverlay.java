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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.core.config.ConfigUtils;
import pl.mplauncher.launcher.core.helper.JFXHelpers;

import java.io.File;

public class ConfigurationOverlay extends Stage {

    public enum InstallationType {
        Classic,
        OwnLocation,
        Portable
    }

    private JFXRadioButton classic;

    private JFXRadioButton ownLocation;
    private JFXTextField location;

    private JFXRadioButton portable;
    private JFXComboBox<String> driveList;

    public ConfigurationOverlay() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        Scene scene = new Scene(dialogLayout);
        scene.setFill(Color.TRANSPARENT);

        this.getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo.png")));
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);

        Text title = new Text("Konfiguracja Launchera");
        title.setFont(new Font("Montserrat SemiBold", 13));
        dialogLayout.setHeading(title);

        VBox vBox = new VBox();
        vBox.setMinHeight(160.0);
        dialogLayout.setBody(vBox);

        Text info = new Text("W jaki sposób chciałbyś używać ten launcher?");
        info.setFont(new Font("Montserrat Regular", 10));

        classic = new JFXRadioButton();
        classic.setText("Klasycznie (instalacja w " + ConfigUtils.getDefaultDataLocation().getPath() + ")");
        classic.setPadding(new Insets(5.0, 0.0, 5.0, 0.0));
        classic.setSelected(true);
        vBox.getChildren().add(classic);

        ownLocation = new JFXRadioButton();
        ownLocation.setText("Własna lokalizacja (instalacja np. na innym dysku)");
        ownLocation.setPadding(new Insets(5.0, 0.0, 5.0, 0.0));
        vBox.getChildren().add(ownLocation);

        HBox ownLocationContent = new HBox();
        ownLocationContent.setPadding(new Insets(0.0, 0.0, 10.0, 40.0));
        ownLocationContent.setVisible(false);
        vBox.getChildren().add(ownLocationContent);

        location = new JFXTextField();
        location.setText(ConfigUtils.getDefaultDataLocation().getParentFile().getAbsolutePath());
        JFXButton selectLocation = new JFXButton();
        selectLocation.setText("Wybierz lokalizacje");
        selectLocation.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Wybierz lokalizacje launchera");
            directoryChooser.setInitialDirectory(new File(location.getText()));
            File selectedLocation = directoryChooser.showDialog(this);
            if (selectedLocation != null) {
                location.setText(selectedLocation.getAbsolutePath());
            }
        });
        ownLocationContent.getChildren().addAll(location, selectLocation);

        portable = new JFXRadioButton();
        portable.setText("Portable (instalacja na Pendrive, działa na każdym PC)");
        portable.setPadding(new Insets(5.0, 0.0, 5.0, 0.0));

        //Disabled because in DEV mode without Jar file it'll not work.
        if (MPLauncher.class.getPackage().getImplementationVersion() == null) {
            portable.setDisable(true);
        }

        vBox.getChildren().add(portable);

        HBox portableContent = new HBox();
        portableContent.setPadding(new Insets(0.0, 0.0, 10.0, 40.0));
        portableContent.setVisible(false);
        vBox.getChildren().add(portableContent);

        driveList = new JFXComboBox<>();
        driveList.setPromptText("Wybierz");
        driveList.getItems().addAll("D:/", "E:/", "G:/");
        portableContent.getChildren().add(driveList);

        HBox okButtons = new HBox();
        okButtons.setAlignment(Pos.CENTER_RIGHT);
        dialogLayout.setActions(okButtons);

        Text importantInfo = new Text("Ustawienia te są permanentne (oprócz portable)");
        importantInfo.setFont(new Font("Montserrat Light", 10));
        importantInfo.setTextAlignment(TextAlignment.LEFT);
        HBox.setMargin(importantInfo, new Insets(0.0, 10.0, 0.0, 0.0));

        JFXButton ok = new JFXButton();
        ok.setPrefWidth(70.0);
        ok.setPrefHeight(30.0);
        ok.setButtonType(JFXButton.ButtonType.RAISED);
        ok.setOnAction(action -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 0.0, event -> this.close()));
        ok.setText("Zapisz");
        okButtons.getChildren().addAll(importantInfo, ok);

        classic.setOnAction(event -> { if (classic.isSelected()) { ownLocation.setSelected(false); portable.setSelected(false);} else if (!ownLocation.isSelected() && !portable.isSelected()) { classic.setSelected(true); } });
        ownLocation.setOnAction(event -> { if (ownLocation.isSelected()) { portable.setSelected(false); classic.setSelected(false);} else if (!classic.isSelected() && !portable.isSelected()) { classic.setSelected(true); } });
        portable.setOnAction(event -> { if (portable.isSelected()) { classic.setSelected(false); ownLocation.setSelected(false);} else if (!classic.isSelected() && !ownLocation.isSelected()) { classic.setSelected(true); } });

        ownLocationContent.visibleProperty().bind(ownLocation.selectedProperty());
        ownLocationContent.managedProperty().bind(ownLocation.selectedProperty());
        portableContent.visibleProperty().bind(portable.selectedProperty());
        portableContent.visibleProperty().bind(portable.selectedProperty());

        this.setAlwaysOnTop(true);
        this.centerOnScreen();
        this.setOpacity(0.0);
        this.setOnShown(event -> JFXHelpers.doublePropertyAnimation(Duration.millis(500), this.opacityProperty(), 1.0));
        this.showAndWait();
    }

    public InstallationType getResult() {
        if (classic.isSelected()) {
            return InstallationType.Classic;
        }

        if (ownLocation.isSelected()) {
            return InstallationType.OwnLocation;
        }

        if (portable.isSelected()) {
            return InstallationType.Portable;
        }

        return null;
    }

    public String getLocation() {
        if (ownLocation.isSelected()) {
            return location.getText();
        } else if (portable.isSelected()) {
            return driveList.getSelectionModel().getSelectedItem();
        } else {
            return ConfigUtils.getDefaultDataLocation().getAbsolutePath();
        }
    }
}
