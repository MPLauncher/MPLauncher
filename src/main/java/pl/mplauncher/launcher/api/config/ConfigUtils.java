package pl.mplauncher.launcher.api.config;

import org.apache.commons.lang3.Validate;
import org.diorite.cfg.system.Template;
import org.diorite.cfg.system.TemplateCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class ConfigUtils {

    public static <T> T loadConfig(final File file, final Class<T> implementationTemplate) {
        return loadConfig(file, implementationTemplate, true);
    }

    /**
     * Loads config
     * @param file file to be loaded
     * @param implementationTemplate template of file to be loaded
     * @param <T> template
     * @return template with config
     */
    public static <T> T loadConfig(final File file, final Class<T> implementationTemplate, boolean fileVisibleOnWindows) {
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

                if (!fileVisibleOnWindows && !file.isHidden()) {
                    Files.setAttribute(Paths.get(file.getAbsolutePath()), "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
                }

            } catch (IOException e) {
                throw new RuntimeException("IO exception when creating config file: " + file.getAbsolutePath(), e);
            }
        } else {
            try {
                try {
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
            template.dump(file, config, false);
        } catch (IOException e) {
            throw new RuntimeException("Can't dump configuration file!", e);
        }

        return config;
    }
}
