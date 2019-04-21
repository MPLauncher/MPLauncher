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

package pl.mplauncher.launcher.core.api.mp.component;

import javafx.scene.image.Image;

public class PlayerSkin {

    private String username;

    private boolean showHair = true;
    private boolean headOnly = false;

    private int ratio = 0;
    private boolean antyAliasing = true;

    private int verticalRotation = 0;
    private int horizontalRotation = 0;

    public PlayerSkin(String username) {
        this.username = username;
    }

    public PlayerSkin showHair() {
        showHair = true;
        return this;
    }

    public PlayerSkin head() {
        headOnly = true;
        return this;
    }

    public PlayerSkin ratio(int ratio) {
        this.ratio = ratio;
        return this;
    }

    public PlayerSkin antyAliasing() {
        antyAliasing = true;
        return this;
    }

    public PlayerSkin rotation(int vr, int hr) {
        verticalRotation = vr;
        horizontalRotation = hr;
        return this;
    }

    public Image getImage() {
        return new Image(String.format("https://skiny.mplauncher.pl/api/3d.php?user=%s&vr=%d&hr=%d&displayHair=%b&headOnly=%b&format=png&ratio=%d&aa=%b&layers=false",
                username, verticalRotation, horizontalRotation, showHair, headOnly, ratio, antyAliasing));
    }

}
