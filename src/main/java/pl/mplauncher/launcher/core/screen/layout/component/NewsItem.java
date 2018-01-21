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

import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsItem extends VBox {

    public NewsItem(String title, Date date) {
        this.setMouseTransparent(true);
        this.setMaxWidth(VBox.USE_PREF_SIZE);

        Label newsTitle = new Label();
        newsTitle.setWrapText(true);
        newsTitle.setText(title.toUpperCase());
        newsTitle.getStyleClass().addAll("fontSemiBold", "fontSize10", "fillTextWhite");

        Label newsDate = new Label();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        newsDate.setText(dateFormat.format(date));
        newsDate.getStyleClass().addAll("fontRegular", "fontSize10", "fillTextWhite");

        this.getChildren().addAll(newsTitle, newsDate);
    }

}

