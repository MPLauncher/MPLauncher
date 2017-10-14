package pl.mplauncher.launcher.api.config;

import org.apache.commons.lang3.Validate;
import org.diorite.cfg.system.Template;
import org.diorite.cfg.system.TemplateCreator;
import pl.mplauncher.launcher.bootstrap.MPLauncherBootstrap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class ConfigUtils {

    public enum DataDirectory {
        LOGS,
        CONFIG
    }

    public static File getLocationForData(DataDirectory type) {
        switch (type) {
            case LOGS: {
                File toReturn = new File(AppSetup.getInstance().getDataLocation() + File.separator + "logs");
                if (!toReturn.exists()) { Validate.isTrue(toReturn.mkdirs(), "Couldn't mkdirs() on " + toReturn.getAbsolutePath()); }
                return toReturn;
            }
            case CONFIG: {
                File toReturn = new File(AppSetup.getInstance().getDataLocation() + File.separator + "config");
                if (!toReturn.exists()) { Validate.isTrue(toReturn.mkdirs(), "Couldn't mkdirs() on " + toReturn.getAbsolutePath()); }
                return toReturn;
            }
            default: {
                File toReturn = new File(AppSetup.getInstance().getDataLocation() + File.separator + "logs");
                if (!toReturn.exists()) { Validate.isTrue(toReturn.mkdirs(), "Couldn't mkdirs() on " + toReturn.getAbsolutePath()); }
                return toReturn;
            }
        }
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

    /**
     * Loads config
     * @param file file to be loaded
     * @param implementationTemplate template of file to be loaded
     * @param <T> template
     * @return template with config
     */
    public static <T> T loadConfig(final File file, final Class<T> implementationTemplate) {
        final Template<T> template = TemplateCreator.getTemplate(implementationTemplate);
        T config;

        if (!file.exists()) {
            try {
                try {
                    config = template.fillDefaults(implementationTemplate.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Couldn't get access to " + implementationTemplate.getName() + " constructor", e);
                }

                Validate.isTrue(file.createNewFile(), "Couldn't create " + file.getAbsolutePath() + " config file");

            } catch (IOException e) {
                throw new RuntimeException("IO exception when creating config file: " + file.getAbsolutePath(), e);
            }
        } else {
            try {
                try {
                    // That's because JVM treats hidden files as read-only -> ಠ_ಠ
                    if (file.isHidden()) {
                        Files.setAttribute(Paths.get(file.getAbsolutePath()), "dos:hidden", Boolean.FALSE, LinkOption.NOFOLLOW_LINKS);
                    }

                    config = template.load(file);
                    if (config == null) {
                        config = template.fillDefaults(implementationTemplate.newInstance());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("IO exception when loading config file: " + file.getAbsolutePath(), e);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Couldn't get access to " + implementationTemplate.getName() + " constructor", e);
            }
        }

        try {
            template.dump(file, config, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't dump configuration file!", e);
        }

        return config;
    }

    /**
     * Saves config
     * @param file file to be saved
     * @param implementationTemplate template of file to be saved
     * @param data data of template
     * @param <T> template
     */
    public static <T> void saveConfig(final File file, final Class<T> implementationTemplate, T data) {
        final Template<T> template = TemplateCreator.getTemplate(implementationTemplate);

        if (!file.exists()) {
            try {
                Validate.isTrue(file.createNewFile(), "Couldn't create " + file.getAbsolutePath() + " config file");
            } catch (IOException e) {
                throw new RuntimeException("IO exception when creating config file: " + file.getAbsolutePath(), e);
            }
        }

        try {
            template.dump(file, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't dump configuration file!", e);
        }
    }
}
