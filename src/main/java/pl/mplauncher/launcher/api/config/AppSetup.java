package pl.mplauncher.launcher.api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mplauncher.launcher.api.config.templates.AppSetupTemplate;
import pl.mplauncher.launcher.control.ConfigurationOverlay;

import java.io.File;

public class AppSetup {
    private static Logger logger;
    private static AppSetupTemplate instance;
    private static File location;

    public static void initialize() {
        if (ConfigUtils.isGlobalConfigExists()) {
            if (ConfigUtils.getNearJarConfigLocation().exists()) {
                location = ConfigUtils.getNearJarConfigLocation();
                instance = ConfigUtils.loadConfig(ConfigUtils.getNearJarConfigLocation(), AppSetupTemplate.class);
            } else {
                location = ConfigUtils.getNearPcConfigLocation();
                instance = ConfigUtils.loadConfig(ConfigUtils.getNearPcConfigLocation(), AppSetupTemplate.class);
            }

            if (instance.getInstallationType() == ConfigurationOverlay.InstallationType.Portable) {
                instance.setDataLocation(ConfigUtils.getPortableDataLocation()); //Pendrive letter/location may change!
                saveConfig(); //Save it for now
            }

            if (logger == null) {
                // Setting proper log directory.
                System.setProperty("logBasePath", ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.LOGS).getAbsolutePath());
                logger = LogManager.getLogger(AppSetup.class);
            }
            logger.info("AppSetupTemplate has been loaded into memory!");
        } else {
            if (logger == null) {
                // *********************************************** //
                // By default it's path where is the *.JAR file.   //
                // It's have to be called before log4j2 is loaded! //
                // *********************************************** //
                System.setProperty("logBasePath", "logs");
                logger = LogManager.getLogger(AppSetup.class);
            }
            logger.warn("Launcher isn't configured yet!");
        }
    }

    public static void configure(File configLocation) {
        location = configLocation;
        instance = ConfigUtils.loadConfig(location, AppSetupTemplate.class);
    }

    public static AppSetupTemplate getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    public static void saveConfig() {
        ConfigUtils.saveConfig(location, AppSetupTemplate.class, instance);
    }
}
