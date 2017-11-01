package pl.mplauncher.launcher.screen.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.screen.MainScreen;
import pl.mplauncher.launcher.screen.layout.MainLayout;

import java.net.URL;

public class ServerList extends StackPane {

    public ServerList(MainLayout parent) {
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
        serverListText.setText(MessageBundle.getCurrentLanguage().getMessage("serverList-title"));
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
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("mc.jpg");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            serverImage.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, true))));
        } else {
            parent.screen.logger.error("Couldn't set server image!");
        }

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

        Text serverName = new Text();
        serverName.setStrokeType(StrokeType.OUTSIDE);
        serverName.setStrokeWidth(0.0);
        serverName.setText("NAJLEPSZY SERWER ŚWIATA"); //Testing purpose
        GridPane.setMargin(serverName, new Insets(0, 0.0, 0.0, 20.0));
        serverName.getStyleClass().addAll("fontSemiBold", "fontSize14", "fillWhite");

        Label serverDescription = new Label();
        serverDescription.setWrapText(true);
        GridPane.setRowIndex(serverDescription, 1);
        GridPane.setValignment(serverDescription, VPos.TOP);
        GridPane.setMargin(serverDescription, new Insets(0.0, 20.0, 0.0, 20.0));
        serverDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse et mauris id arcu sodales lobortis nec sit amet enim. Integer venenatis nunc at purus varius pulvinar. Sed vehicula convallis aliquet. Vestibulum congue vestibulum magna, sit amet tristique neque imperdiet eu.\n" +
                "\n" +
                " Sed vehicula convallis aliquet. Vestibulum congue vestibulum magna, sit amet tristique neque imperdiet eu."); //Testing purpose
        serverDescription.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

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

        Label addToFavorite = new Label();
        addToFavorite.setText(MessageBundle.getCurrentLanguage().getMessage("serverList-addToFav"));
        GridPane.setMargin(addToFavorite, new Insets(0.0, 0.0, 0.0, 20.0));
        addToFavorite.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.HEART_ALT));
        addToFavorite.getGraphic().getStyleClass().add("fillWhite");
        addToFavorite.getStyleClass().addAll("fontRegular", "fontSize10", "textFillWhite");

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
        playButton.setOnAction(actionEvent -> ((MainScreen) parent.screen).playClicked());

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
        parent.serverListleftSite = new ScrollPane();
        parent.serverListleftSite.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        parent.serverListleftSite.setMinSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        parent.serverListleftSite.setMaxSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        parent.serverListleftSite.setPrefSize(314.0, 445.0);
        parent.serverListleftSite.setPadding(new Insets(0.0));
        parent.serverListleftSite.getStyleClass().add("serverListScrollPane");

        VBox serverListleftSiteVBox = new VBox();
        serverListleftSiteVBox.getStyleClass().add("serverListContainer");
        serverListleftSiteVBox.setPadding(new Insets(0.0));

        parent.favoriteServerList = new JFXListView<>();
        parent.favoriteServerList.setFixedCellSize(60.0);
        parent.favoriteServerList.getStyleClass().add("serverList");
        parent.favoriteServerList.setPadding(new Insets(0.0));
        parent.favoriteServerList.managedProperty().bind(parent.favoriteServerList.visibleProperty());
        parent.favoriteServerList.setVisible(false);

        StackPane favoriteServerListIndicator = new StackPane();
        favoriteServerListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        favoriteServerListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        favoriteServerListIndicator.setPrefSize(305.0, 30.0);
        favoriteServerListIndicator.setPadding(new Insets(0.0));
        favoriteServerListIndicator.getStyleClass().add("serverListIndicator");
        favoriteServerListIndicator.managedProperty().bind(parent.favoriteServerList.managedProperty());
        favoriteServerListIndicator.visibleProperty().bind(parent.favoriteServerList.visibleProperty());

        Label fSLLabel = new Label();
        fSLLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        fSLLabel.setText(MessageBundle.getCurrentLanguage().getMessage("serverList-fav"));
        StackPane.setAlignment(fSLLabel, Pos.CENTER_LEFT);

        StackPane otherServerListIndicator = new StackPane();
        otherServerListIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        otherServerListIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        otherServerListIndicator.setPrefSize(305.0, 30.0);
        otherServerListIndicator.setPadding(new Insets(0.0));
        otherServerListIndicator.getStyleClass().add("serverListIndicator");
        otherServerListIndicator.managedProperty().bind(parent.favoriteServerList.managedProperty());
        otherServerListIndicator.visibleProperty().bind(parent.favoriteServerList.visibleProperty());

        Label oSLLabel = new Label();
        oSLLabel.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));
        oSLLabel.setText(MessageBundle.getCurrentLanguage().getMessage("serverList-other"));
        StackPane.setAlignment(oSLLabel, Pos.CENTER_LEFT);

        parent.otherServerList = new JFXListView<>();
        parent.otherServerList.setFixedCellSize(60.0);
        parent.otherServerList.getStyleClass().add("serverList");
        parent.otherServerList.setPadding(new Insets(0.0));
        // LEFT //

        //Binding
        this.getChildren().add(container);
        container.setTop(topStackPane);
        container.setCenter(centerContainer);
        container.setLeft(parent.serverListleftSite);
        topStackPane.getChildren().add(serverListText);
        centerContainer.getChildren().addAll(serverImage, serverInfo, serverOptions);
        parent.serverListleftSite.setContent(serverListleftSiteVBox);
        serverInfo.getChildren().addAll(serverName, serverDescription);
        serverOptions.getChildren().addAll(leftserverOptions, playButton, rightserverOptions);
        serverListleftSiteVBox.getChildren().addAll(favoriteServerListIndicator, parent.favoriteServerList, otherServerListIndicator, parent.otherServerList);
        leftserverOptions.getChildren().addAll(addToFavorite, isInstalled);
        rightserverOptions.getChildren().addAll(ramLabel, ramField);
        favoriteServerListIndicator.getChildren().add(fSLLabel);
        otherServerListIndicator.getChildren().add(oSLLabel);
    }
}
