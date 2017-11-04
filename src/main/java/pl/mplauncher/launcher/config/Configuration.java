package pl.mplauncher.launcher.config;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;

import java.io.*;
import java.lang.reflect.Field;

abstract class Configuration<T> {

    abstract File getDefaultLocation();
    private File location;

    public void setConfigLocation(File location) {
        this.location = location;
    }

    public void load() {
        load(false);
    }

    public void load(boolean autocreate) {
        File loadLocation;

        if (location == null) {
            loadLocation = getDefaultLocation();
        } else {
            loadLocation = location;
        }

        if (!loadLocation.exists() && autocreate) {
            save();
        }

        try {
            JsonReader reader = new JsonReader(new FileReader(loadLocation));
            applyFields(ConfigurationFactory.gson.fromJson(reader, getClass()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            FileUtils.deleteQuietly(loadLocation);
        }
    }

    public void save() {
        if (location == null) {
            save(getDefaultLocation());
        } else {
            save(location);
        }
    }

    public void save(File location) {
        try {
            if (!location.getParentFile().exists()) {
                Validate.isTrue(location.getParentFile().mkdirs(), "Couldn't mkdirs() on " + location.getAbsolutePath());
            }

            FileWriter writer = new FileWriter(location);
            writer.write(ConfigurationFactory.gson.toJson(this));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyFields(T from) {
        Class<T> fromClass = (Class<T>) from.getClass();

        for (Field field : fromClass.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                field.set(this, field.get(from));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
