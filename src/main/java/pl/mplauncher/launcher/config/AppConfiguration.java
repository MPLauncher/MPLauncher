package pl.mplauncher.launcher.config;

import pl.mplauncher.launcher.control.ConfigurationOverlay;

import java.io.File;

public class AppConfiguration extends Configuration<AppConfiguration> {

    private boolean firstRun = true;
    private ConfigurationOverlay.InstallationType installationType;
    private File dataLocation;

    public boolean isFirstRun() {
        return firstRun;
    }

    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    public ConfigurationOverlay.InstallationType getInstallationType() {
        return installationType;
    }

    public void setInstallationType(ConfigurationOverlay.InstallationType installationType) {
        this.installationType = installationType;
    }

    public File getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(File dataLocation) {
        this.dataLocation = dataLocation;
    }

    @Override
    File getDefaultLocation() {
        File location = null;

        if (ConfigUtils.isGlobalConfigExists()) {
            if (ConfigUtils.getNearJarConfigLocation().exists()) {
                location = ConfigUtils.getNearJarConfigLocation();
            } else {
                location = ConfigUtils.getNearPcConfigLocation();
            }

            if (getInstallationType() == ConfigurationOverlay.InstallationType.Portable) {
                setDataLocation(ConfigUtils.getPortableDataLocation()); //Pendrive letter/location may change!
            }
        }

        return location;
    }

}
