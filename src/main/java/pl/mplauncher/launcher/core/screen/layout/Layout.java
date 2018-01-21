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

package pl.mplauncher.launcher.core.screen.layout;

import javafx.scene.Scene;
import pl.mplauncher.launcher.core.screen.Screen;

public abstract class Layout {

    protected Scene scene;
    public Screen screen;

    public double xOffset;
    public double yOffset;

    public abstract void initialize();

    Layout(Screen screen) {
        this.screen = screen;
    }

    public Scene getScene() {
        return scene;
    }

}
