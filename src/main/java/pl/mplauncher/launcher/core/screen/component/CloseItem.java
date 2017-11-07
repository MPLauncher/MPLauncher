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
package pl.mplauncher.launcher.core.screen.component;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

public class CloseItem extends GridPane {

    public CloseItem(MainLayout parent, String label) {
        this.setPadding(new Insets(0.0, 13.0, 13.0, 13.0));

        ColumnConstraints first = new ColumnConstraints();
        first.setHgrow(Priority.NEVER);
        first.percentWidthProperty().bind(parent.menuListIcon.minWidthProperty());
        first.setHalignment(HPos.CENTER);

        ColumnConstraints second = new ColumnConstraints();
        second.setHgrow(Priority.NEVER);
        second.percentWidthProperty().bind(parent.menuListText.minWidthProperty());

        this.getColumnConstraints().addAll(first, second);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(55.0);
        rowConstraints.setVgrow(Priority.NEVER);

        this.getRowConstraints().add(rowConstraints);

        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.POWER_OFF, "26px");
        icon.getStyleClass().add("selectListIcon");
        icon.setMouseTransparent(true);
        Label text = new Label();
        text.setMouseTransparent(true);
        text.opacityProperty().bind(parent.menuListText.opacityProperty());
        text.setText(label);
        GridPane.setColumnIndex(text, 1);

        this.getChildren().addAll(icon, text);
    }

}
