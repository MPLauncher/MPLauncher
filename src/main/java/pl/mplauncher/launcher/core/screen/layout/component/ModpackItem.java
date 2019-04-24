package pl.mplauncher.launcher.core.screen.layout.component;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

public class ModpackItem extends GridPane {

    private String name;

    public ModpackItem(MainLayout parent, String name, String version, String description) {
        this.name = name;
        this.setMouseTransparent(true);
        this.setMaxWidth(GridPane.USE_PREF_SIZE);
        this.prefWidthProperty().bind(parent.serverListleftSite.widthProperty().subtract(43));

        ColumnConstraints sIcolumn1 = new ColumnConstraints();
        sIcolumn1.setHgrow(Priority.NEVER);
        sIcolumn1.setPercentWidth(70.0);
        ColumnConstraints sIcolumn2 = new ColumnConstraints();
        sIcolumn2.setHgrow(Priority.NEVER);
        sIcolumn2.setPercentWidth(30.0);
        sIcolumn2.setHalignment(HPos.CENTER);

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
        serverName.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillTextWhite");

        Label serverVersion = new Label();
        GridPane.setValignment(serverVersion, VPos.TOP);
        GridPane.setHalignment(serverVersion, HPos.LEFT);
        GridPane.setRowIndex(serverVersion, 1);
        serverVersion.setText(
            MessageBundle.getCurrentLanguage().getMessage("general-modpackVersion") + " " + version.toUpperCase());
        serverVersion.getStyleClass().addAll("fontRegular", "fontSize8", "fillTextWhite");

        Label modpackDescription = new Label();
        GridPane.setValignment(modpackDescription, VPos.BOTTOM);
        GridPane.setHalignment(modpackDescription, HPos.CENTER);
        GridPane.setColumnIndex(modpackDescription, 1);
        modpackDescription.setText(description.toUpperCase());
        modpackDescription.getStyleClass().addAll("fontRegular", "fontSize10", "fillTextWhite");

        this.getChildren().addAll(serverName, serverVersion, modpackDescription);
    }

    public String getName() {
        return this.name;
    }
}
