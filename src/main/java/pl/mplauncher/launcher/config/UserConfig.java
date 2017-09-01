package pl.mplauncher.launcher.config;

import com.google.common.base.Charsets;
import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;

import java.util.List;
import java.util.UUID;

@CfgClass(name = "UserConfig")
@CfgDelegateDefault("{new}")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("DON'T MODIFY THIS FILE ON YOUR OWN,")
@CfgComment("YOU CAN LOSE YOUR IMPORTANT DATA!")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("Don't ever share this file with anyone!")
@CfgComment("This file contains your user data needed for launcher to:")
@CfgComment("- keep you logged in")
@CfgComment("- save your custom settings")
@CfgComment("- keep your favorite servers")
@CfgComment("When you share this file with someone,")
@CfgComment("this person will be able to use your account without your knowledge")
@CfgComment("-------------------------------------------------------------------")
public class UserConfig {

    @CfgName("users")
    public List<UserData> users;
    
    //TODO: Add more data about: favorite servers, resolution, sound settings, ram etc.

    public enum UserType {
        Premium,
        NonPremium
    }

    public static class UserData {
        String username;
        UUID uuid;
        String accessToken;
        String clientToken;
        boolean remember;
        UserType userType;

        public UserData(String username, UUID uuid, String accesstoken, String clienttoken, boolean remember) {
            this.username = username;
            this.uuid = uuid;
            this.accessToken = accesstoken;
            this.clientToken = clienttoken;
            this.remember = remember;
            this.userType = UserType.Premium;
        }

        public UserData(String username, boolean remember) {
            this.username = username;
            this.uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));
            this.accessToken = "\" \"";
            this.clientToken = null;
            this.remember = remember;
            this.userType = UserType.NonPremium;
        }

        public String getUsername() {
            return username;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getClientToken() {
            return clientToken;
        }

        public boolean remember() {
            return remember;
        }

        public UserType getUserType() {
            return userType;
        }
    }
}
