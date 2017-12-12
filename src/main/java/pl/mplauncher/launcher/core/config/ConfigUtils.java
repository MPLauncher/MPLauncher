/*
   Copyright 2017 MPLauncher Team

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.mplauncher.launcher.core.config;

import org.apache.commons.lang3.Validate;
import pl.mplauncher.launcher.MPLauncher;
import pl.mplauncher.launcher.core.bootstrap.MPLauncherBootstrap;

import java.io.File;

public class ConfigUtils {

    private static final String DATA_DIR = "." + MPLauncher.NAME + "2.0";
    private static final String APP_CONFIG = "." + MPLauncher.NAME + ".config";

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

    public static boolean isApplicationConfigExists() {
        return getJarRelativeConfigLocation().exists() || getGlobalConfigLocation().exists();
    }

    public static File getJarRelativeConfigLocation() {
        File jarPath = new File(MPLauncherBootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return new File(jarPath.getParent() + File.separator + DATA_DIR + File.separator + APP_CONFIG);
    }

    public static File getGlobalConfigLocation() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("win")) { //Windows
            return new File(System.getenv("APPDATA") + File.separator + APP_CONFIG);
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) { //Linux
            return new File(System.getProperty("user.home") + File.separator + ".config" + File.separator + APP_CONFIG);
        } else { //Mac? Solaris?
            return new File(System.getProperty("user.home") + File.separator + APP_CONFIG);
        }
    }

    public static File getDefaultDataLocation() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("win")) { //Windows
            return new File(System.getenv("APPDATA") + File.separator + DATA_DIR + File.separator);
        } else { //Linux / Mac / Solaris
            return new File(System.getProperty("user.home") + File.separator + DATA_DIR + File.separator);
        }
    }

    public static File getPortableDataLocation() {
        return new File(new File(MPLauncherBootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator + DATA_DIR + File.separator);
    }

}
