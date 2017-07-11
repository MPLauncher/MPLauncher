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
package pl.mplauncher.launcher

import javafx.stage.Stage
import pl.mplauncher.launcher.config.MPConfig
import pl.mplauncher.launcher.config.MPConfigManager

class MPLauncher {

    public final static String CONFIG_FILE_NAME = "configuration.yml"

    private final Stage stage

    private MPConfig config

    MPLauncher(Stage stage) {
        this.stage = stage
    }

    void initialize() {
        MPConfigManager configManager = MPConfigManager.create()

        MPConfig config = configManager.getConfig(MPConfig.class, CONFIG_FILE_NAME)
    }

    void start() {

    }

    void stop() {

    }

    MPConfig getConfig() {
        return this.config
    }

}

