package pl.mplauncher.launcher.helpers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import java.awt.*;
import java.net.URI;

/**
 * Created by losti on 29.08.2017.
 * Copyright (c) 2017
 */
public class JFXHelpers {

    /**
     * Makes fade transition of given node.
     * @param duration time in ms
     * @param node given node
     * @param from double value of where to start
     * @param to double value of where to stop
     */
    public static void FadeTransition(Duration duration, Node node, double from, double to) {
        FadeTransition(duration, node, from, to, null);
    }
    public static void FadeTransition(Duration duration, Node node, double from, double to, EventHandler<ActionEvent> onFinished) {
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        if (onFinished != null) { fadeTransition.setOnFinished(onFinished); }
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
