package pl.mplauncher.launcher.screen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.screen.layout.Layout;

import java.lang.reflect.ParameterizedType;

public abstract class Screen<T extends Layout> {

    @SuppressWarnings("unchecked")
    public Logger logger = LogManager.getLogger(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

    public T layout;

    public abstract void initialize();
    abstract T createLayout();

    Screen() {
        layout = createLayout();
    }

}
