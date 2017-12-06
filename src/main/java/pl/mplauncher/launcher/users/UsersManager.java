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
package pl.mplauncher.launcher.users;

import pl.mplauncher.launcher.core.config.ConfigurationFactory;
import pl.mplauncher.launcher.core.config.UserProfile;

import java.util.Date;

public class UsersManager {

    private UserProfile currentProfile;

    public UserProfile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(UserProfile currentProfile) {
        this.currentProfile = currentProfile;
        this.currentProfile.setLastLogin(new Date().getTime());
    }

    public boolean hasUser(String uuid) {
        return ConfigurationFactory.getUsersRepository().
                getUsers()
                .stream()
                .map(UserProfile::getUUID)
                .anyMatch(uuid::equals);
    }

}
