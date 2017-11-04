package pl.mplauncher.launcher.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigurationFactory {

    static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static AppConfiguration appConfiguration = null;
    private static UsersConfiguration usersConfiguration = null;

    public static AppConfiguration getAppSetup() {
        return getAppSetup(false);
    }

    public static AppConfiguration getAppSetup(boolean fromFile) {
        if (appConfiguration == null) {
            appConfiguration = new AppConfiguration();
        }

        if (fromFile) {
            appConfiguration.load();
        }

        return appConfiguration;
    }

    public static UsersConfiguration getUsers() {
        return getUsers(false);
    }

    public static UsersConfiguration getUsers(boolean fromFile) {
        if (usersConfiguration == null) {
            usersConfiguration = new UsersConfiguration();
        }

        if (fromFile) {
            usersConfiguration.load();
        }

        return usersConfiguration;
    }

}
