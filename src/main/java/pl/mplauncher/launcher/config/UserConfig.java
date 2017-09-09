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

    /*
        Use for verification if data decrypted by launcher is proper.
        This string should be one static value encrypted by PC UID.
        If PC UID is changed, decrypted value wouldn't be that expected value.
        So we'll know that this file is running on other PC and we should reset that file ASAP.

        We won't place here PC UID because it'll be much simpler to decrypt this data.
     */
    @CfgComment("Don't ever touch this line!")
    @CfgName("verify")
    public String verify;

    @CfgName("users")
    public List<UserData> users;

    //TODO: Add more data about: favorite servers, resolution, sound settings, ram etc.
    //TODO: Sensitive data shouldn't be saved not encrypted in the file. Use some unique PC ID to encrypt this data.

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
