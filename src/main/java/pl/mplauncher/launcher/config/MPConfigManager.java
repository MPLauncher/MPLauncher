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
package pl.mplauncher.launcher.config;

import org.apache.commons.lang3.Validate;
import org.diorite.config.Config;

import java.io.File;

public interface MPConfigManager {

    static MPConfigManager create() {
        return new MPConfigManagerImpl();
    }

    <T extends Config> T getConfig(Class<T> tClass, File bindFile);

    default <T extends Config> T getConfig(Class<T> tClass, String bindFileName) {
        Validate.isTrue(tClass != null);
        Validate.isTrue(bindFileName != null);

        return this.getConfig(tClass, new File(bindFileName));
    }

}
