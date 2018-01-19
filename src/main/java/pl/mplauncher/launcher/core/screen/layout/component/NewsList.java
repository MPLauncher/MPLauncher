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

package pl.mplauncher.launcher.core.screen.layout.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
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
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;

public class NewsList extends StackPane {

    public enum Category {
        Important,
        Latest,
        Technical
    }

    private HashMap<Category, JFXListView<NewsItem>> categorizedNews;

    public void addNews(Category newsCategory, String newsTitle, Date newsDate) {
        if (!categorizedNews.get(newsCategory).isVisible()) {
            categorizedNews.get(newsCategory).setVisible(true);
        }
        categorizedNews.get(newsCategory).setPrefHeight(((categorizedNews.get(newsCategory).getItems().size() + 1) * categorizedNews.get(newsCategory).getFixedCellSize()) + 2);
        categorizedNews.get(newsCategory).getItems().add(new NewsItem(newsTitle, newsDate));
    }

    public NewsList(MainLayout parent) {

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
        serverListText.setText("ARCHIWUM NEWSÓW");
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
        cCrow3.setValignment(VPos.BOTTOM);
        centerContainer.getRowConstraints().addAll(cCrow1, cCrow2, cCrow3);

        StackPane serverImage = new StackPane();
        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("images/mc.jpg");
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            serverImage.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, true))));
        } else {
            parent.screen.logger.error("Couldn't set NEWS image!");
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

        JFXButton playButton = new JFXButton();
        playButton.setPrefSize(188.0, 30.0);
        playButton.setText("POKAŻ NEWSA NA STRONIE");
        GridPane.setRowIndex(playButton, 2);
        GridPane.setHalignment(playButton, HPos.RIGHT);
        GridPane.setMargin(playButton, new Insets(0.0, 20.0, 20.0, 0.0));
        playButton.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite", "playButton");

        // CENTER //

        // LEFT //
        ScrollPane leftSiteScrollPane = new ScrollPane();
        leftSiteScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftSiteScrollPane.setMinSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        leftSiteScrollPane.setMaxSize(ScrollPane.USE_PREF_SIZE, ScrollPane.USE_PREF_SIZE);
        leftSiteScrollPane.setPrefSize(244.0, 445.0);
        leftSiteScrollPane.getStyleClass().add("serverListScrollPane");

        VBox newsListleftSiteVBox = new VBox();
        newsListleftSiteVBox.getStyleClass().add("serverListContainer");
        newsListleftSiteVBox.setPadding(new Insets(0.0));

        // Initialize categorized news
        categorizedNews = new HashMap<>();
        for(Category category : Category.values()) {
            JFXListView<NewsItem> newsList = new JFXListView<>();

            newsList.setFixedCellSize(60.0);
            newsList.getStyleClass().add("serverList");
            newsList.managedProperty().bind(newsList.visibleProperty());
            newsList.setPrefWidth(244.0);
            newsList.setVisible(false);

            StackPane categoryIndicator = new StackPane();
            categoryIndicator.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            categoryIndicator.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            categoryIndicator.setPrefSize(244.0, 30.0);
            categoryIndicator.setPadding(new Insets(0.0));
            categoryIndicator.getStyleClass().add("serverListIndicator");

            Label categoryName = new Label();
            categoryName.setText(category.name().toUpperCase());
            StackPane.setMargin(categoryName, new Insets(0.0, 0.0, 0.0, 18.0));
            StackPane.setAlignment(categoryName, Pos.CENTER_LEFT);

            JFXRippler jfxRippler = new JFXRippler();
            jfxRippler.setAlignment(Pos.CENTER_RIGHT);
            StackPane.setMargin(jfxRippler, new Insets(0.0, 25.0, 0.0, 0.0));
            StackPane.setAlignment(jfxRippler, Pos.CENTER_RIGHT);
            FontAwesomeIconView disclosure = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_DOWN, "10px");
            disclosure.getStyleClass().add("textWhite");
            jfxRippler.setControl(disclosure);
            jfxRippler.setOnMouseClicked((mouseEvent) -> {
                if (disclosure.getGlyphName().equalsIgnoreCase("CHEVRON_DOWN")) {
                    newsList.setVisible(false);
                    disclosure.setIcon(FontAwesomeIcon.CHEVRON_RIGHT);
                } else {
                    newsList.setVisible(true);
                    disclosure.setIcon(FontAwesomeIcon.CHEVRON_DOWN);
                }
            });

            categoryIndicator.getChildren().addAll(categoryName, jfxRippler);
            newsListleftSiteVBox.getChildren().addAll(categoryIndicator, newsList);
            categorizedNews.put(category, newsList);
        }

        // Deselect other news in group if news in other group is selected
        for (JFXListView<NewsItem> listView : categorizedNews.values()) {
            for (JFXListView<NewsItem> otherListView : categorizedNews.values()) {
                if (listView != otherListView) {
                    listView.getSelectionModel().selectedItemProperty().addListener(((o, oV, nV) -> {
                        if (nV != null && otherListView != null
                                && !otherListView.getSelectionModel().isEmpty()) {
                            otherListView.getSelectionModel().clearSelection();
                        }
                    }));
                }
            }
        }

        // LEFT //

        //Binding
        this.getChildren().add(container);
        container.setTop(topStackPane);
        container.setCenter(centerContainer);
        container.setLeft(leftSiteScrollPane);
        topStackPane.getChildren().add(serverListText);
        centerContainer.getChildren().addAll(serverImage, serverInfo, playButton);
        leftSiteScrollPane.setContent(newsListleftSiteVBox);
        serverInfo.getChildren().addAll(serverName, serverDescription);
    }
}
