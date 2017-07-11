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
package pl.mplauncher.launcher.config.lang

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.apache.commons.lang3.Validate
import pl.mplauncher.launcher.config.MPConfigManager

import java.util.concurrent.TimeUnit

class MPLocaleManagerImpl implements MPLocaleManager {

    private final MPConfigManager manager

    private final Map<MPLocale, MPLocaleFile> localesMap = new HashMap<>()
    private final Cache<MPLocale, MPLocaleFile> localesCache = CacheBuilder.newBuilder()
                                                                    .expireAfterAccess(5, TimeUnit.MINUTES)
                                                                    .softValues()
                                                                    .build()

    protected MPLocaleManagerImpl(MPConfigManager manager) {
        Validate.isTrue(manager != null, "Manager can not be null!")

        this.manager = manager
    }

    @Override
    MPLocaleFile getLocale(MPLocale locale) {
        Validate.isTrue(locale != null, "Locale can not be null!")

        return null
    }

}

