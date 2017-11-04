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
package pl.mplauncher.launcher.screen.component;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.screen.layout.MainLayout;

public class ServerItem extends GridPane {

    public ServerItem(MainLayout parent, String name, String version, String slots) {
        this.setMouseTransparent(true);
        this.setMaxWidth(GridPane.USE_PREF_SIZE);
        this.prefWidthProperty().bind(parent.serverListleftSite.widthProperty().subtract(43));

        ColumnConstraints sIcolumn1 = new ColumnConstraints();
        sIcolumn1.setHgrow(Priority.NEVER);
        sIcolumn1.setPercentWidth(70.0);
        ColumnConstraints sIcolumn2 = new ColumnConstraints();
        sIcolumn1.setHgrow(Priority.NEVER);
        sIcolumn2.setPercentWidth(30.0);

        this.getColumnConstraints().addAll(sIcolumn1, sIcolumn2);

        RowConstraints sIrow1 = new RowConstraints();
        sIrow1.setVgrow(Priority.SOMETIMES);
        RowConstraints sIrow2 = new RowConstraints();
        sIrow2.setVgrow(Priority.SOMETIMES);

        this.getRowConstraints().addAll(sIrow1, sIrow2);

        Label serverName = new Label();
        serverName.setWrapText(true);
        GridPane.setValignment(serverName, VPos.BOTTOM);
        serverName.setText(name.toUpperCase());
        GridPane.setMargin(serverName, new Insets(0.0, 0.0, 0.0, 24.0));
        serverName.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillTextWhite");

        Label serverVersion = new Label();
        GridPane.setValignment(serverVersion, VPos.TOP);
        GridPane.setHalignment(serverVersion, HPos.LEFT);
        GridPane.setRowIndex(serverVersion, 1);
        serverVersion.setText(MessageBundle.getCurrentLanguage().getMessage("general-mcVersion") + " " + version.toUpperCase());
        GridPane.setMargin(serverVersion, new Insets(0.0, 0.0, 0.0, 24.0));
        serverVersion.getStyleClass().addAll("fontRegular", "fontSize8", "fillTextWhite");

        Label serverSlots = new Label();
        GridPane.setValignment(serverSlots, VPos.BOTTOM);
        GridPane.setHalignment(serverSlots, HPos.CENTER);
        GridPane.setColumnIndex(serverSlots, 1);
        serverSlots.setText(slots.toUpperCase());
        serverSlots.getStyleClass().addAll("fontRegular", "fontSize10", "fillTextWhite");

        this.getChildren().addAll(serverName, serverVersion, serverSlots);
    }

}
