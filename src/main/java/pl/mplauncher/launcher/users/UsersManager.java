package pl.mplauncher.launcher.users;

import pl.mplauncher.launcher.core.config.UserProfile;

public class UsersManager {

    private UserProfile currentProfile;

    public UserProfile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(UserProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

}
