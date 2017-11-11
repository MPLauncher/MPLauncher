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

import pl.mplauncher.launcher.core.control.ConfigurationOverlay;

import java.io.File;

public class AppConfiguration extends Configuration<AppConfiguration> {

    private boolean firstRun = true;
    private ConfigurationOverlay.InstallationType installationType;
    private File dataLocation;

    public boolean isFirstRun() {
        return firstRun;
    }

    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    public ConfigurationOverlay.InstallationType getInstallationType() {
        return installationType;
    }

    public void setInstallationType(ConfigurationOverlay.InstallationType installationType) {
        this.installationType = installationType;
    }

    public File getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(File dataLocation) {
        this.dataLocation = dataLocation;
    }

    @Override
    File getDefaultLocation() {
        File location = null;

        if (ConfigUtils.isGlobalConfigExists()) {
            if (ConfigUtils.getNearJarConfigLocation().exists()) {
                location = ConfigUtils.getNearJarConfigLocation();
            } else {
                location = ConfigUtils.getNearPcConfigLocation();
            }

            if (getInstallationType() == ConfigurationOverlay.InstallationType.Portable) {
                setDataLocation(ConfigUtils.getPortableDataLocation()); //Pendrive letter/location may change!
            }
        }

        return location;
    }

}
