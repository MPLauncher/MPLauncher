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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class StartNews extends StackPane {

    public StartNews(String title, Image image, String article, String author, String time) {
        this.setAlignment(Pos.TOP_RIGHT);
        this.getStyleClass().add("newsContent");
        StackPane.setAlignment(this, Pos.CENTER);
        StackPane.setMargin(this, new Insets(25.0, 60.0, 34.0, 65.0));

        StackPane titleSP = new StackPane();
        titleSP.setAlignment(Pos.CENTER_LEFT);
        titleSP.setMaxHeight(StackPane.USE_PREF_SIZE);
        titleSP.setPrefSize(200.0, 53.0);
        titleSP.setPadding(new Insets(0.0, 0.0, 0.0, 16.0));

        Text newsTitle = new Text();
        newsTitle.setStrokeType(StrokeType.OUTSIDE);
        newsTitle.setStrokeWidth(0.0);
        newsTitle.getStyleClass().add("newsTitle");
        newsTitle.setText(title);

        StackPane imageSP = new StackPane();
        imageSP.setMaxHeight(StackPane.USE_PREF_SIZE);
        imageSP.setPrefHeight(212.0);
        imageSP.getStyleClass().add("newsImage");
        imageSP.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        StackPane.setMargin(imageSP, new Insets(53.0, 0.0, 0.0, 0.0));

        StackPane articleSP = new StackPane();
        articleSP.setAlignment(Pos.TOP_LEFT);
        articleSP.setMaxHeight(StackPane.USE_PREF_SIZE);
        articleSP.setPrefHeight(188.0);
        articleSP.setPadding(new Insets(23.0, 16.0, 16.0, 16.0));
        StackPane.setMargin(articleSP, new Insets(266.0, 0.0, 0.0, 0.0));

        Label newsArticle = new Label();
        newsArticle.setWrapText(true);
        newsArticle.getStyleClass().add("newsArticle");
        newsArticle.setText(article);

        StackPane footerSP = new StackPane();
        footerSP.setAlignment(Pos.TOP_RIGHT);
        footerSP.setMaxHeight(StackPane.USE_PREF_SIZE);
        footerSP.setPrefHeight(40.0);
        footerSP.setPadding(new Insets(0.0, 21.0, 0.0, 0.0));
        StackPane.setMargin(footerSP, new Insets(451.0, 0.0, 0.0, 0.0));

        Text newsAuthor = new Text();
        newsAuthor.setStrokeType(StrokeType.OUTSIDE);
        newsAuthor.setStrokeWidth(0.0);
        newsAuthor.getStyleClass().add("newsAuthor");
        newsAuthor.setText(author);

        Text newsTime = new Text();
        newsTime.setStrokeType(StrokeType.OUTSIDE);
        newsTime.setStrokeWidth(0.0);
        newsTime.getStyleClass().add("newsTime");
        StackPane.setAlignment(newsTime, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(newsTime, new Insets(0.0, 0.0, 16.0, 0.0));
        newsTime.setText(time);

        this.getChildren().addAll(titleSP, imageSP, articleSP, footerSP);
        titleSP.getChildren().add(newsTitle);
        articleSP.getChildren().add(newsArticle);
        footerSP.getChildren().addAll(newsAuthor, newsTime);
    }

}
