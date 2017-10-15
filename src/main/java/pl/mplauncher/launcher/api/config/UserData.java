package pl.mplauncher.launcher.api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.api.config.templates.UserDataTemplate;

import java.io.File;

public class UserData {
    private static Logger logger = LogManager.getLogger(UserData.class);
    private static UserDataTemplate instance;
    private static File location;

    private static void initialize() {
        location = new File(ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.CONFIG) + File.separator + "UserData.yml");
        instance = ConfigUtils.loadConfig(location, UserDataTemplate.class);
        logger.info("UserDataTemplate has been loaded into memory!");
    }

    public static UserDataTemplate getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    public static void saveConfig() {
        if (instance == null) {
            logger.warn("Tried to save UserData config without initializing it!");
        } else {
            ConfigUtils.saveConfig(location, UserDataTemplate.class, instance);
        }
    }
}
