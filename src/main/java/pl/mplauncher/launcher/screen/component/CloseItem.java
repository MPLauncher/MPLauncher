package pl.mplauncher.launcher.screen.component;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.mplauncher.launcher.screen.layout.MainLayout;

public class CloseItem extends GridPane {

    public CloseItem(MainLayout parent, String label) {
        this.setMouseTransparent(true);
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
