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

import com.google.common.base.Charsets;

import java.util.Date;
import java.util.UUID;

public class UserProfile {

    private transient UserSpecificConfiguration configuration;

    private String username;
    private String uuid;

    private Type userType;

    private String accessToken;
    private String clientToken;

    private boolean remember;
    private long lastLogin;

    private String userDataDir;

    public UserProfile(String username, UUID uuid, String accessToken, String clientToken, boolean remember,
                       Type userType) {
        this.username = username;
        this.uuid = uuid.toString();
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.remember = remember;
        this.userType = userType;
        this.userDataDir = username.toLowerCase() + "-" + uuid.toString().split("-")[0];
        this.lastLogin = new Date().getTime();
    }

    public UserProfile(String username, boolean remember) {
        this(username, UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8)),
                "\" \"", null, remember, Type.NONPREMIUM);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public Type getUserType() {
        return userType;
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserDataDir() {
        return userDataDir;
    }

    public void setUserDataDir(String userDataDir) {
        this.userDataDir = userDataDir;
    }

    public UserSpecificConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = new UserSpecificConfiguration(this);
            configuration.load(true);
        }

        return configuration;
    }

    public void setConfiguration(UserSpecificConfiguration configuration) {
        this.configuration = configuration;
    }

    public enum Type {
        PREMIUM,
        NONPREMIUM
    }

}
