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
package pl.mplauncher.launcher.config.lang;

import org.apache.commons.lang3.Validate;
import pl.mplauncher.launcher.config.MPConfig;
import pl.mplauncher.launcher.config.MPConfigManager;

public interface MPLocaleManager {

    static MPLocaleManager create(MPConfigManager manager) {
        return new MPLocaleManagerImpl(manager);
    }

    static MPLocaleManager create() {
        return create(MPConfigManager.create());
    }

    MPLocaleFile getLocale(MPLocale locale);

    default MPLocaleFile getLocale(MPConfig config) {
        Validate.isTrue(config != null);

        return this.getLocale(config.getLocale());
    }

    default MPLocaleFile getLocale() {
        return this.getLocale(MPLocale.getDefault());
    }

}
