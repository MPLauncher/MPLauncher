package pl.mplauncher.launcher.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsersConfiguration extends Configuration<UsersConfiguration> {

    private List<UserProfile> users = new ArrayList<>();

    public List<UserProfile> getUsers() {
        return users;
    }

    public void setUsers(List<UserProfile> users) {
        this.users = users;
    }

    @Override
    File getDefaultLocation() {
        System.out.println(ConfigurationFactory.getAppSetup().getDataLocation());
        return new File(ConfigurationFactory.getAppSetup().getDataLocation(), "users.json");
    }

}
