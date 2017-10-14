package pl.mplauncher.launcher.api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.api.config.templates.UsersTemplate;

import java.io.File;

public class Users {
    private static Logger logger = LogManager.getLogger(Users.class);
    private static UsersTemplate instance;
    private static File location;

    private static void initialize() {
        location = new File(ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.CONFIG) + File.separator + "Users.yml");
        instance = ConfigUtils.loadConfig(location, UsersTemplate.class);
        logger.info("Users has been loaded into memory!");
    }

    public static UsersTemplate getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    public static void saveConfig() {
        ConfigUtils.saveConfig(location, UsersTemplate.class, instance);
    }
}
