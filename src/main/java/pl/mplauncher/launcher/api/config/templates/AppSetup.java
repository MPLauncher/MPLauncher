package pl.mplauncher.launcher.api.config.templates;

import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgFooterComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;

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

    @CfgComment("Path of launcher instance")
    @CfgName("path")
    public String path;
}