package pl.mplauncher.launcher.screen.component;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.mplauncher.launcher.screen.layout.MainLayout;

public class MenuItem extends GridPane {

    public MenuItem(MainLayout parent, FontAwesomeIcon glyph, String label) {
        this.setMouseTransparent(true);

        ColumnConstraints first = new ColumnConstraints();
        first.setHgrow(Priority.NEVER);
        first.percentWidthProperty().bind(parent.menuListIcon.minWidthProperty());
        first.setHalignment(HPos.CENTER);

        ColumnConstraints second = new ColumnConstraints();
        second.setHgrow(Priority.NEVER);
        second.percentWidthProperty().bind(parent.menuListText.minWidthProperty());

        this.getColumnConstraints().addAll(first, second);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(35);
        rowConstraints.setVgrow(Priority.SOMETIMES);

        this.getRowConstraints().add(rowConstraints);
        this.setCursor(Cursor.HAND);

        FontAwesomeIconView icon = new FontAwesomeIconView(glyph, "26px");
        Label text = new Label();
        text.opacityProperty().bind(parent.menuListText.opacityProperty());
        text.setText(label);
        GridPane.setColumnIndex(text, 1);

        this.getChildren().addAll(icon, text);
    }

}
