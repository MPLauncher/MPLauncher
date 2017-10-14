package pl.mplauncher.launcher.api.config.templates;

import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgFooterComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;
import pl.mplauncher.launcher.control.ConfigurationOverlay;

import java.io.File;

@CfgClass(name = "AppSetupTemplate")
@CfgDelegateDefault("{new}")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("DON'T MODIFY THIS FILE ON YOUR OWN,")
@CfgComment("IT CAN DESTROY LAUNCHER INSTALLATION!")
@CfgComment("-------------------------------------------------------------------")
@CfgFooterComment("-------------------------------------------------------------------")
@CfgFooterComment("THE END OF THE CONFIG FILE")
@CfgFooterComment("-------------------------------------------------------------------")
public class AppSetupTemplate {

    @CfgComment("Is it first run?")
    @CfgName("firstRun")
    private boolean firstRun = true;

    @CfgComment("Type of installation")
    @CfgComment("installationType")
    private ConfigurationOverlay.InstallationType installationType;

    @CfgComment("Location of launcher data")
    @CfgName("dataLocation")
    private File dataLocation;

    /**
     * Gets the first run state
     * @return boolean value
     */
    public boolean getFirstRun() {
        return firstRun;
    }

    /**
     * Sets the first run state
     * @param state boolean value
     */
    public void setFirstRun(boolean state) {
        this.firstRun = state;
    }

    /**
     * Returns installation type
     * @return installationType value
     */
    public ConfigurationOverlay.InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * Sets the installation type
     * @param installationType value
     */
    public void setInstallationType(ConfigurationOverlay.InstallationType installationType) {
        this.installationType = installationType;
    }

    /**
     * Returns data location
     * @return file value
     */
    public File getDataLocation() {
        return dataLocation;
    }

    /**
     * Sets the data location
     * @param dataLocation file value
     */
    public void setDataLocation(File dataLocation) {
        this.dataLocation = dataLocation;
    }
}