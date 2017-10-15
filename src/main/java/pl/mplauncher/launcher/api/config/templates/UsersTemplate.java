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
package pl.mplauncher.launcher.api.config.templates;

import com.google.common.base.Charsets;
import org.diorite.cfg.annotations.CfgClass;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgName;
import org.diorite.cfg.annotations.defaults.CfgDelegateDefault;

import java.util.*;

@CfgClass(name = "UsersTemplate")
@CfgDelegateDefault("{new}")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("DON'T MODIFY THIS FILE ON YOUR OWN,")
@CfgComment("YOU CAN LOSE YOUR IMPORTANT DATA!")
@CfgComment("-------------------------------------------------------------------")
@CfgComment("Don't ever share this file with anyone!")
@CfgComment("This file contains your user data needed for launcher to:")
@CfgComment("- keep you logged in")
@CfgComment("- read your custom data")
@CfgComment("When you share this file with someone,")
@CfgComment("this person will be able to use your account without your knowledge")
@CfgComment("-------------------------------------------------------------------")
public class UsersTemplate {

    //TODO:Make this verify value
    /*
        Use for verification if data decrypted by launcher is proper.
        This string should be one static value encrypted by PC UID.
        If PC UID is changed, decrypted value wouldn't be that expected value.
        So we'll know that this file is running on other PC and we should reset that file ASAP.

        We won't place here PC UID because it'll be much simpler to decrypt this data.
     */
    @CfgComment("Don't ever touch this line!")
    @CfgName("verify")
    private String verify;

    @CfgName("users")
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Gets the verify value
     * @return string value
     */
    public String getVerify() {
        return verify;
    }

    /**
     * Sets the verify value
     * @param verify string value
     */
    public void setVerify(String verify) {
        this.verify = verify;
    }

    /**
     * Gets the users list
     * @return array list of User
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    //TODO: Sensitive data shouldn't be saved not encrypted in the file. Use some unique PC ID to encrypt this data.

    public enum UserType {
        PREMIUM,
        NONPREMIUM
    }

    public static class User {

        private final String username;
        private final String uuid;
        private final String accessToken;
        private final String clientToken;
        private final boolean remember;
        private final UserType userType;
        private final String userDataDir;
        private final long lastLogin;

        User() {
            username = null;
            uuid = null;
            accessToken = null;
            clientToken = null;
            remember = false;
            userType = null;
            userDataDir = null;
            lastLogin = 0;
        }

        public User(String username, UUID uuid, String accessToken, String clientToken, boolean remember,
                        UserType userType) {
            this.username = username;
            this.uuid = uuid.toString();
            this.accessToken = accessToken;
            this.clientToken = clientToken;
            this.remember = remember;
            this.userType = userType;
            this.userDataDir = username.toLowerCase() + "-" + uuid.toString().split("-")[0];
            this.lastLogin = new Date().getTime();
        }

        public User(String username, boolean remember) {
            this(username, UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8)),
                    "\" \"", null, remember, UserType.NONPREMIUM);
        }

        public String getUsername() {
            return username;
        }

        public String getUuid() {
            return uuid;
        }

        public UUID getUuidAsUUID() {
            return UUID.fromString(uuid);
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

        public String getUserDataDir() {
            return userDataDir;
        }

        public long getLastLogin() {
            return lastLogin;
        }

        public Date getLastLoginDate() { return new Date(lastLogin); }
    }
}
