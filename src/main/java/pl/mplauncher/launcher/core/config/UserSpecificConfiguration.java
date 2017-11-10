package pl.mplauncher.launcher.core.config;

import java.io.File;

public class UserSpecificConfiguration extends Configuration<UserSpecificConfiguration>{

    private transient UserProfile profile;

    private String language;
    private String theme;
    private boolean debugApplication;
    private boolean autostart;
    private boolean disableEventTheme;
    private boolean enableDynamicIcon;
    private boolean disableLogCatch;
    private boolean showMinecraftConsole;
    private boolean minimizeToTray;

    public UserSpecificConfiguration(UserProfile profile) {
        this.profile = profile;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String locale) {
        this.language = locale;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isDebugApplication() {
        return debugApplication;
    }

    public void setDebugApplication(boolean debugApplication) {
        this.debugApplication = debugApplication;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }

    public boolean isDisableEventTheme() {
        return disableEventTheme;
    }

    public void setDisableEventTheme(boolean disableEventTheme) {
        this.disableEventTheme = disableEventTheme;
    }

    public boolean isEnableDynamicIcon() {
        return enableDynamicIcon;
    }

    public void setEnableDynamicIcon(boolean enableDynamicIcon) {
        this.enableDynamicIcon = enableDynamicIcon;
    }

    public boolean isDisableLogCatch() {
        return disableLogCatch;
    }

    public void setDisableLogCatch(boolean disableLogCatch) {
        this.disableLogCatch = disableLogCatch;
    }

    public boolean isShowMinecraftConsole() {
        return showMinecraftConsole;
    }

    public void setShowMinecraftConsole(boolean showMinecraftConsole) {
        this.showMinecraftConsole = showMinecraftConsole;
    }

    public boolean isMinimizeToTray() {
        return minimizeToTray;
    }

    public void setMinimizeToTray(boolean minimizeToTray) {
        this.minimizeToTray = minimizeToTray;
    }

    @Override
    File getDefaultLocation() {
        return new File(ConfigUtils.getLocationForData(ConfigUtils.DataDirectory.CONFIG),
                "userdata" + File.separator +
                profile.getUserDataDir() +
                File.separator + "config.json");
    }

}
