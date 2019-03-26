/*
 * Copyright 2017-2019 MPLauncher Team
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

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import pl.mplauncher.launcher.core.screen.MainScreen;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

import java.net.URL;

public class RightOfNews extends StackPane {

    public RightOfNews(MainLayout parent) {
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("fancyText");
        StackPane.setAlignment(this, Pos.TOP_RIGHT);
        GridPane.setColumnIndex(this, 1);

        parent.rightCenterFirstText.setStrokeType(StrokeType.OUTSIDE);
        parent.rightCenterFirstText.setStrokeWidth(0.0);
        parent.rightCenterFirstText.getStyleClass().add("fancyText_1");
        parent.rightCenterFirstText.setTextAlignment(TextAlignment.CENTER);

        parent.discordLogo.setFitWidth(163.0);
        parent.discordLogo.setFitHeight(55.0);
        parent.discordLogo.setPickOnBounds(true);
        parent.discordLogo.setPreserveRatio(true);
        parent.discordLogo.setTranslateY(215.0);
        parent.discordLogo.setCursor(Cursor.HAND);
        parent.discordLogo.setOnMouseClicked(event -> ((MainScreen) parent.screen).discordLogoClicked());
        URL imgUrl = Thread.currentThread().getContextClassLoader().getResource("images/DiscordLogo.png");
        if (imgUrl != null) {
            parent.discordLogo.setImage(new Image(imgUrl.toString()));
        } else {
            parent.screen.logger.error("Couldn't load discord logo for the MainScreen form!");
        }


        TextFlow mplauncherText = new TextFlow();
        mplauncherText.setMaxHeight(TextFlow.USE_PREF_SIZE);
        mplauncherText.setPrefHeight(50.0);
        mplauncherText.setTextAlignment(TextAlignment.CENTER);
        mplauncherText.getStyleClass().add("fancyText_3_Container");

        Text mpText = new Text();
        mpText.setStrokeType(StrokeType.OUTSIDE);
        mpText.setStrokeWidth(0.0);
        mpText.setTextAlignment(TextAlignment.CENTER);
        mpText.getStyleClass().addAll("fancyText_3", "textBold");
        mpText.setText("MP");

        Text launcherText = new Text();
        launcherText.setStrokeType(StrokeType.OUTSIDE);
        launcherText.setStrokeWidth(0.0);
        launcherText.setTextAlignment(TextAlignment.CENTER);
        launcherText.getStyleClass().add("fancyText_3");
        launcherText.setText("LAUNCHER.PL");

        this.getChildren().addAll(parent.rightCenterFirstText, parent.discordLogo, mplauncherText);
        mplauncherText.getChildren().addAll(mpText, launcherText);
    }
}
