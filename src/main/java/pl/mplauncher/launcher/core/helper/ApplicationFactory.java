package pl.mplauncher.launcher.core.helper;

import pl.mplauncher.launcher.users.UsersManager;

public class ApplicationFactory {

    private static UsersManager usersManager = null;

    public static UsersManager getUsersManager() {
        if (usersManager == null) {
            usersManager = new UsersManager();
        }

        return usersManager;
    }

}
