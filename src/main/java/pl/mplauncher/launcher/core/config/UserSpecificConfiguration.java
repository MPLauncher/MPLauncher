/*
 * Copyright 2017-2019 MPLauncher Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mplauncher.launcher.core.config;

import pl.mplauncher.launcher.core.config.smart.SmartConfigurationOption;
import pl.mplauncher.launcher.core.config.smart.SmartOptionType;

import java.io.File;

public class UserSpecificConfiguration extends Configuration<UserSpecificConfiguration>{

    private transient UserProfile profile;

    @SmartConfigurationOption(type = SmartOptionType.LIST, options = {"POLSKI", "ANGIELSKI", "BAKA"})
    private String language;

    @SmartConfigurationOption(type = SmartOptionType.LIST, options = {"NAJLEPSZY", "KAWAII"})
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
