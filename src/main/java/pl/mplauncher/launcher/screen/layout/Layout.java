package pl.mplauncher.launcher.screen.layout;

import javafx.scene.Scene;
import pl.mplauncher.launcher.screen.Screen;

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
