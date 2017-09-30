package pl.mplauncher.launcher.api.config.templates;

import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgFooterComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;
import pl.mplauncher.launcher.control.ConfigurationOverlay;

import java.io.File;

@CfgClass(name = "AppSetup")
@CfgDelegateDefault("{new}")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("DON'T MODIFY THIS FILE ON YOUR OWN,")
@CfgComment("IT CAN DESTROY LAUNCHER INSTALLATION!")
@CfgComment("-------------------------------------------------------------------")
@CfgFooterComment("-------------------------------------------------------------------")
@CfgFooterComment("THE END OF THE CONFIG FILE")
@CfgFooterComment("-------------------------------------------------------------------")
public class AppSetup {

    @CfgComment("Is it first run?")
    @CfgName("firstRun")
    public boolean firstRun = true;

    @CfgComment("Type of installation")
    @CfgComment("installationType")
    public ConfigurationOverlay.InstallationType installationType;

    @CfgComment("Location of launcher data")
    @CfgName("dataLocation")
    public File dataLocation;
}