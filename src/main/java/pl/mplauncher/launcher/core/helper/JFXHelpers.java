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

package pl.mplauncher.launcher.core.helper;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import java.awt.*;
import java.net.URI;

public class JFXHelpers {

    /**
     * Makes double property animation
     * @param duration time in ms
     * @param target given doubleproperty
     * @param endValue final value
     */
    public static void doublePropertyAnimation(Duration duration, DoubleProperty target,
                                               Double endValue) {
        doublePropertyAnimation(duration, target, endValue, null);
    }

    /**
     * Makes double property animation
     * @param duration time in ms
     * @param target given doubleproperty
     * @param endValue final value
     * @param onFinished action after the end of animation
     */
    public static void doublePropertyAnimation(Duration duration, DoubleProperty target, Double endValue,
                                               EventHandler<ActionEvent> onFinished) {
        Timeline animations = new Timeline();
        animations.getKeyFrames().add(new KeyFrame(duration, new KeyValue(target, endValue)));

        if (onFinished != null) {
            animations.setOnFinished(onFinished);
        }

        animations.play();
    }

    /**
     * Makes fade transition of given node.
     * @param duration time in ms
     * @param node given node
     * @param from double value of where to start
     * @param to double value of where to stop
     */
    public static void fadeTransition(Duration duration, Node node, double from, double to) {
        fadeTransition(duration, node, from, to, null);
    }

    /**
     * Makes fade transition of given node.
     * @param duration time in ms
     * @param node given node
     * @param from double value of where to start
     * @param to double value of where to stop
     * @param onFinished action event handler
     */
    public static void fadeTransition(Duration duration, Node node, double from, double to,
                                      EventHandler<ActionEvent> onFinished) {
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);

        if (onFinished != null) {
            fadeTransition.setOnFinished(onFinished);
        }

        fadeTransition.play();
    }

    /**
     * Opens browser for user with given Uri
     * @param uri address of webpage
     */
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
