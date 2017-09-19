package pl.mplauncher.launcher.api.config;

import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;

@CfgClass(name = "UserData")
@CfgDelegateDefault("{new}")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("DON'T MODIFY THIS FILE ON YOUR OWN,")
@CfgComment("YOU CAN LOSE YOUR IMPORTANT DATA!")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("This file contains your user data needed for launcher to:")
@CfgComment("- save your custom settings")
@CfgComment("- keep your favorite servers")
@CfgComment("-------------------------------------------------------------------")
public class UserData {

    //TODO: Add more data about: favorite servers, resolution, sound settings, ram etc.

}
