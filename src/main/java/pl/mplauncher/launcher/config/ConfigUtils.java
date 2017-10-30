package pl.mplauncher.launcher.config;

import org.apache.commons.lang3.Validate;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;

import java.io.File;

public class ConfigUtils {

    public enum DataDirectory {
        LOGS,
        CONFIG
    }

    public static File getLocationForData(DataDirectory type) {
        String directoryName = type.name().toLowerCase();
        File toReturn = new File(ConfigurationFactory.getAppSetup().getDataLocation() + File.separator + directoryName);

        if (!toReturn.exists()) {
            Validate.isTrue(toReturn.mkdirs(), "Couldn't mkdirs() on " + toReturn.getAbsolutePath());
        }

        return toReturn;
    }

    public static boolean isGlobalConfigExists() {
        return getNearJarConfigLocation().exists() || getNearPcConfigLocation().exists();
    }

    public static File getNearJarConfigLocation() {
        File jarPath = new File(MPLauncherBootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return new File(jarPath.getParent() + File.separator + ".mplauncher2.0" + File.separator + "MPLauncher.config");
    }

    public static File getNearPcConfigLocation() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("win")) { //Windows
            return new File(System.getenv("APPDATA") + File.separator + ".MPLauncher.config");
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) { //Linux
            return new File(System.getProperty("user.home") + File.separator + ".config" + File.separator + ".MPLauncher.config");
        } else { //Mac? Solaris?
            return new File(System.getProperty("user.home") + File.separator + ".MPLauncher.config");
        }
    }

    public static File getClassicDataLocation() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("win")) { //Windows
            return new File(System.getenv("APPDATA") + File.separator + ".mplauncher2.0" + File.separator);
        } else { //Linux / Mac / Solaris
            return new File(System.getProperty("user.home") + File.separator + ".mplauncher2.0" + File.separator);
        }
    }

    public static File getPortableDataLocation() {
        return new File(new File(MPLauncherBootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator + ".mplauncher2.0" + File.separator);
    }

}
