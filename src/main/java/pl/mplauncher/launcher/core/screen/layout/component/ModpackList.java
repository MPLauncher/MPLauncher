package pl.mplauncher.launcher.core.screen.layout.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import pl.mplauncher.launcher.core.api.i18n.MessageBundle;
import pl.mplauncher.launcher.core.enums.ModpackType;
import pl.mplauncher.launcher.core.manager.Managers;
import pl.mplauncher.launcher.core.screen.MainScreen;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

public class ModpackList extends StackPane {

    public Text serverName;
    public Label modpackDescription;
    public static ModpackType selectedModpackType;

    public ModpackList(MainLayout layout) {
        GridPane.setHalignment(this, HPos.CENTER);
        GridPane.setValignment(this, VPos.CENTER);

        BorderPane container = new BorderPane();
        container.getStyleClass().add("serverMenuBP");
        StackPane.setMargin(container, new Insets(25.0, 60.0, 34.0, 65.0));

        // TOP //
        StackPane topStackPane = new StackPane();
        topStackPane.setPrefHeight(53.0);
        topStackPane.getStyleClass().add("serverMenuTop");

        Label serverListText = new Label();
        serverListText.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-title"));
        StackPane.setAlignment(serverListText, Pos.CENTER_LEFT);
        StackPane.setMargin(serverListText, new Insets(0.0, 0.0, 0.0, 20.0));
        serverListText.getStyleClass().addAll("fontSemiBold", "fontSize12", "textFillWhite");
        // TOP //

        // CENTER //
        GridPane centerContainer = new GridPane();
        ColumnConstraints cCcolumn1 = new ColumnConstraints();
        cCcolumn1.setHgrow(Priority.SOMETIMES);
        centerContainer.getColumnConstraints().add(cCcolumn1);
        RowConstraints cCrow1 = new RowConstraints();
        cCrow1.setPercentHeight(45.0);
        cCrow1.setVgrow(Priority.SOMETIMES);
        RowConstraints cCrow2 = new RowConstraints();
        cCrow2.setPercentHeight(40.0);
        cCrow2.setVgrow(Priority.SOMETIMES);
        RowConstraints cCrow3 = new RowConstraints();
        cCrow3.setPercentHeight(15.0);
        cCrow3.setVgrow(Priority.SOMETIMES);
        centerContainer.getRowConstraints().addAll(cCrow1, cCrow2, cCrow3);

        StackPane serverImage = new StackPane();
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("images/mc.jpg");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            serverImage.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, true))));
        } else {
            layout.screen.logger.error("Couldn't set server image!");
        }

        serverName = new Text();
        serverName.setStrokeType(StrokeType.OUTSIDE);
        serverName.setStrokeWidth(0.0);
        serverName.setText("Nie nazwana paczka"); //Testing purpose
        GridPane.setMargin(serverName, new Insets(0, 0.0, 0.0, 20.0));
        serverName.getStyleClass().addAll("fontSemiBold", "fontSize14", "fillWhite");

        modpackDescription = new Label();
        modpackDescription.setWrapText(true);
        GridPane.setRowIndex(modpackDescription, 1);
        GridPane.setValignment(modpackDescription, VPos.TOP);
        GridPane.setMargin(modpackDescription, new Insets(0.0, 20.0, 0.0, 20.0));
        modpackDescription.setText("Powiedzmy, ze tu jest jakies description ni"); //Testing purpose
        modpackDescription.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

        GridPane serverInfo = new GridPane();
        GridPane.setRowIndex(serverInfo, 1);
        ColumnConstraints sIcolumn1 = new ColumnConstraints();
        sIcolumn1.setHgrow(Priority.SOMETIMES);
        serverInfo.getColumnConstraints().add(sIcolumn1);
        RowConstraints sIrow1 = new RowConstraints();
        sIrow1.setPercentHeight(20.0);
        sIrow1.setVgrow(Priority.SOMETIMES);
        RowConstraints sIrow2 = new RowConstraints();
        sIrow2.setPercentHeight(80.0);
        sIrow2.setVgrow(Priority.SOMETIMES);
        serverInfo.getRowConstraints().addAll(sIrow1, sIrow2);

        serverInfo.setOnMouseReleased(event -> {
            layout.screen.logger.info(serverName.getText());
            layout.screen.logger.info(selectedModpackType.name());
            Optional<ModpackItem> item = Managers.getModpackManager().findPackByType(layout, selectedModpackType, serverName.getText());
            layout.screen.logger.info(item.get().getName());
            item.ifPresent(modpackItem -> serverName.setText(modpackItem.getName()));
        });

        GridPane serverOptions = new GridPane();
        GridPane.setRowIndex(serverOptions, 2);
        ColumnConstraints sOcolumn1 = new ColumnConstraints();
        sOcolumn1.setHgrow(Priority.SOMETIMES);
        sOcolumn1.setPercentWidth(60.0);
        ColumnConstraints sOcolumn2 = new ColumnConstraints();
        sOcolumn2.setHgrow(Priority.SOMETIMES);
        sOcolumn2.setPercentWidth(20.0);
        ColumnConstraints sOcolumn3 = new ColumnConstraints();
        sOcolumn3.setHgrow(Priority.SOMETIMES);
        sOcolumn3.setPercentWidth(20.0);
        serverOptions.getColumnConstraints().addAll(sOcolumn1, sOcolumn2, sOcolumn3);
        RowConstraints sOrow1 = new RowConstraints();
        sOrow1.setPercentHeight(100.0);
        sOrow1.setVgrow(Priority.SOMETIMES);
        serverOptions.getRowConstraints().add(sOrow1);
        GridPane.setMargin(serverOptions, new Insets(0.0, 0.0, 20.0, 0.0));

        GridPane leftserverOptions = new GridPane();
        ColumnConstraints lsOcolumn1 = new ColumnConstraints();
        lsOcolumn1.setPercentWidth(100.0);
        lsOcolumn1.setHgrow(Priority.SOMETIMES);
        leftserverOptions.getColumnConstraints().add(lsOcolumn1);
        RowConstraints lsOrow1 = new RowConstraints();
        lsOrow1.setVgrow(Priority.SOMETIMES);
        RowConstraints lsOrow2 = new RowConstraints();
        lsOrow2.setVgrow(Priority.SOMETIMES);
        leftserverOptions.getRowConstraints().addAll(lsOrow1, lsOrow2);

        Label isInstalled = new Label();
        isInstalled.setText(MessageBundle.getCurrentLanguage().getMessage("serverList-alreadyInstalled"));
        GridPane.setRowIndex(isInstalled, 1);
        GridPane.setMargin(isInstalled, new Insets(0.0, 0.0, 0.0, 20.0));
        isInstalled.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHECK));
        isInstalled.getGraphic().getStyleClass().add("fillWhite");
        isInstalled.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

        JFXButton playButton = new JFXButton();
        playButton.setPrefWidth(100.0);
        playButton.setPrefHeight(30.0);
        playButton.setText(MessageBundle.getCurrentLanguage().getMessage("general-play"));
        GridPane.setColumnIndex(playButton, 2);
        GridPane.setHalignment(playButton, HPos.CENTER);
        GridPane.setMargin(playButton, new Insets(0.0, 20.0, 0.0, 0.0));
        playButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "playButton");
        playButton.setOnAction(actionEvent -> ((MainScreen) layout.screen).playClicked());

        GridPane rightserverOptions = new GridPane();
        GridPane.setColumnIndex(rightserverOptions, 1);
        ColumnConstraints rsOcolumn1 = new ColumnConstraints();
        rsOcolumn1.setHgrow(Priority.SOMETIMES);
        rsOcolumn1.setPrefWidth(100.0);
        ColumnConstraints rsOcolumn2 = new ColumnConstraints();
        rsOcolumn2.setHgrow(Priority.SOMETIMES);
        rsOcolumn2.setPrefWidth(100.0);
        rightserverOptions.getColumnConstraints().addAll(rsOcolumn1, rsOcolumn2);
        RowConstraints rsOrow1 = new RowConstraints();
        rsOrow1.setVgrow(Priority.SOMETIMES);
        rightserverOptions.getRowConstraints().add(rsOrow1);

        Text ramLabel = new Text();
        ramLabel.setStrokeType(StrokeType.OUTSIDE);
        ramLabel.setStrokeWidth(0.0);
        ramLabel.setText(MessageBundle.getCurrentLanguage().getMessage("general-ramAmount"));
        GridPane.setHalignment(ramLabel, HPos.RIGHT);
        GridPane.setMargin(ramLabel, new Insets(0.0, 3.0, 0.0, 0.0));
        ramLabel.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillWhite");

        JFXTextField ramField = new JFXTextField();
        ramField.setAlignment(Pos.CENTER);
        ramField.setMaxWidth(JFXTextField.USE_PREF_SIZE);
        ramField.setPrefWidth(50.0);
        ramField.setPromptText(MessageBundle.getCurrentLanguage().getMessage("general-ram"));
        ramField.setFocusColor(Color.WHITE);
        ramField.setUnFocusColor(Color.WHITE);
        GridPane.setColumnIndex(ramField, 1);
        GridPane.setHalignment(ramField, HPos.LEFT);
        GridPane.setMargin(ramField, new Insets(0.0, 0.0, 0.0, 3.0));
        ramField.getStyleClass().addAll("fontSemiBold", "fontSize8_5", "textFillWhite");
        // CENTER //

        // LEFT //
        layout.serverListleftSite = new ScrollPane();
        layout.serverListleftSite.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        layout.serverListleftSite.setMinSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        layout.serverListleftSite.setMaxSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        layout.serverListleftSite.setPrefSize(314.0, 445.0);
        layout.serverListleftSite.setPadding(new Insets(0.0));
        layout.serverListleftSite.getStyleClass().add("serverListScrollPane");

        VBox serverListleftSiteVBox = new VBox();
        serverListleftSiteVBox.getStyleClass().add("serverListContainer");
        serverListleftSiteVBox.setPadding(new Insets(0.0));

        layout.vanillaModpackList = new JFXListView<>();
        layout.vanillaModpackList.setFixedCellSize(60.0);
        layout.vanillaModpackList.getStyleClass().add("serverList");
        layout.vanillaModpackList.setPadding(new Insets(0.0));
        layout.vanillaModpackList.managedProperty().bind(layout.vanillaModpackList.visibleProperty());
        layout.vanillaModpackList.setVisible(false);

        layout.kenpackModpackList = new JFXListView<>();
        layout.kenpackModpackList.setFixedCellSize(60.0);
        layout.kenpackModpackList.getStyleClass().add("serverList");
        layout.kenpackModpackList.setPadding(new Insets(0.0));
        layout.kenpackModpackList.managedProperty().bind(layout.kenpackModpackList.visibleProperty());
        layout.kenpackModpackList.setVisible(false);

        layout.ownModpackList = new JFXListView<>();
        layout.ownModpackList.setFixedCellSize(60.0);
        layout.ownModpackList.getStyleClass().add("serverList");
        layout.ownModpackList.setPadding(new Insets(0.0));
        layout.ownModpackList.managedProperty().bind(layout.ownModpackList.visibleProperty());
        layout.ownModpackList.setVisible(false);

        layout.ftbModpackList = new JFXListView<>();
        layout.ftbModpackList.setFixedCellSize(60.0);
        layout.ftbModpackList.getStyleClass().add("serverList");
        layout.ftbModpackList.setPadding(new Insets(0.0));
        layout.ftbModpackList.managedProperty().bind(layout.ftbModpackList.visibleProperty());
        layout.ftbModpackList.setVisible(false);

        layout.otherModpackList = new JFXListView<>();
        layout.otherModpackList.setFixedCellSize(60.0);
        layout.otherModpackList.getStyleClass().add("serverList");
        layout.otherModpackList.setPadding(new Insets(0.0));
        layout.otherModpackList.managedProperty().bind(layout.otherModpackList.visibleProperty());
        layout.otherModpackList.setVisible(false);
        
        StackPane vanillaModpackListIndicator = new StackPane();
        vanillaModpackListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        vanillaModpackListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        vanillaModpackListIndicator.setPrefSize(305.0, 30.0);
        vanillaModpackListIndicator.setPadding(new Insets(0.0));
        vanillaModpackListIndicator.getStyleClass().add("serverListIndicator");
        vanillaModpackListIndicator.managedProperty().bind(layout.vanillaModpackList.managedProperty());
        vanillaModpackListIndicator.visibleProperty().bind(layout.vanillaModpackList.visibleProperty());

        StackPane ownModpackListIndicator = new StackPane();
        ownModpackListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        ownModpackListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        ownModpackListIndicator.setPrefSize(305.0, 30.0);
        ownModpackListIndicator.setPadding(new Insets(0.0));
        ownModpackListIndicator.getStyleClass().add("serverListIndicator");
        ownModpackListIndicator.managedProperty().bind(layout.ownModpackList.managedProperty());
        ownModpackListIndicator.visibleProperty().bind(layout.ownModpackList.visibleProperty());
        
        StackPane kenpackModpackListIndicator = new StackPane();
        kenpackModpackListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        kenpackModpackListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        kenpackModpackListIndicator.setPrefSize(305.0, 30.0);
        kenpackModpackListIndicator.setPadding(new Insets(0.0));
        kenpackModpackListIndicator.getStyleClass().add("serverListIndicator");
        kenpackModpackListIndicator.managedProperty().bind(layout.kenpackModpackList.managedProperty());
        kenpackModpackListIndicator.visibleProperty().bind(layout.kenpackModpackList.visibleProperty());

        StackPane ftbModpackListIndicator = new StackPane();
        ftbModpackListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        ftbModpackListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        ftbModpackListIndicator.setPrefSize(305.0, 30.0);
        ftbModpackListIndicator.setPadding(new Insets(0.0));
        ftbModpackListIndicator.getStyleClass().add("serverListIndicator");
        ftbModpackListIndicator.managedProperty().bind(layout.ftbModpackList.managedProperty());
        ftbModpackListIndicator.visibleProperty().bind(layout.ftbModpackList.visibleProperty());

        StackPane otherModpackListIndicator = new StackPane();
        otherModpackListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        otherModpackListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        otherModpackListIndicator.setPrefSize(305.0, 30.0);
        otherModpackListIndicator.setPadding(new Insets(0.0));
        otherModpackListIndicator.getStyleClass().add("serverListIndicator");
        otherModpackListIndicator.managedProperty().bind(layout.otherModpackList.managedProperty());
        otherModpackListIndicator.visibleProperty().bind(layout.otherModpackList.visibleProperty());

        /* LABELE */
        Label vanillaModpackLabel = new Label();
        vanillaModpackLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        vanillaModpackLabel.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-vanilla"));
        StackPane.setAlignment(vanillaModpackLabel, Pos.CENTER_LEFT);

        Label ownModpackLabel = new Label();
        ownModpackLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        ownModpackLabel.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-own"));
        StackPane.setAlignment(ownModpackLabel, Pos.CENTER_LEFT);

        Label ftbModpackLabel = new Label();
        ftbModpackLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        ftbModpackLabel.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-ftb"));
        StackPane.setAlignment(ftbModpackLabel, Pos.CENTER_LEFT);

        Label kenpackModpackLabel = new Label();
        kenpackModpackLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        kenpackModpackLabel.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-kenpack"));
        StackPane.setAlignment(kenpackModpackLabel, Pos.CENTER_LEFT);

        Label otherModpackLabel = new Label();
        otherModpackLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        otherModpackLabel.setText(MessageBundle.getCurrentLanguage().getMessage("modpacklist-other"));
        StackPane.setAlignment(otherModpackLabel, Pos.CENTER_LEFT);
        /* LABELE */

        // LEFT //

        //Binding
        this.getChildren().add(container);
        container.setTop(topStackPane);
        container.setCenter(centerContainer);
        container.setLeft(layout.serverListleftSite);
        topStackPane.getChildren().add(serverListText);
        centerContainer.getChildren().addAll(serverImage, serverInfo, serverOptions);
        layout.serverListleftSite.setContent(serverListleftSiteVBox);
        serverInfo.getChildren().addAll(serverName, modpackDescription);
        serverOptions.getChildren().addAll(leftserverOptions, playButton, rightserverOptions);
        serverListleftSiteVBox.getChildren().addAll(vanillaModpackListIndicator, layout.vanillaModpackList, ownModpackListIndicator, layout.ownModpackList,
            kenpackModpackListIndicator, layout.kenpackModpackList, otherModpackListIndicator, layout.otherModpackList);
        leftserverOptions.getChildren().addAll(isInstalled);
        rightserverOptions.getChildren().addAll(ramLabel, ramField);
        vanillaModpackListIndicator.getChildren().add(vanillaModpackLabel);
        ownModpackListIndicator.getChildren().add(ownModpackLabel);
        kenpackModpackListIndicator.getChildren().add(kenpackModpackLabel);
        ftbModpackListIndicator.getChildren().add(ftbModpackLabel);
        otherModpackListIndicator.getChildren().add(otherModpackLabel);
    }

}
