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
package pl.mplauncher.launcher.config

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.apache.commons.lang3.Validate
import org.diorite.config.Config
import org.diorite.config.ConfigManager
import org.diorite.config.serialization.Serialization

import java.util.concurrent.TimeUnit

class MPConfigManagerImpl implements MPConfigManager {

    static {
        Serialization serialization = Serialization.getInstance()
        // TODO: serializers
    }

    private final Map<Class<? extends Config>, Config> configMap = new HashMap<>()
    private final Cache<Class<? extends Config>, Config> configCache = CacheBuilder.newBuilder()
                                                                            .expireAfterAccess(5, TimeUnit.MINUTES)
                                                                            .softValues()
                                                                            .build()

    protected MPConfigManagerImpl() {
    }

    @Override
    <T extends Config> T getConfig(Class<T> tClass, File bindFile) {
        Validate.isTrue(tClass != null)
        Validate.is(bindFile != null)

        T config = this.configCache.getIfPresent(tClass) as T
        if (config == null) {
            config = this.configMap.computeIfAbsent(tClass, { Class<? extends Config> aClass ->
                boolean exists = bindFile.exists()
                config = ConfigManager.createInstance(tClass)

                if (!exists) {
                    config.save(bindFile)
                }
                config.load(bindFile)

                this.configCache.put(tClass, config)
            }) as T
        }

        return config
    }

}

