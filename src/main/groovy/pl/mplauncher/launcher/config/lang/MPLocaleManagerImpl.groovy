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
import org.apache.logging.log4j.core.util.IOUtils
import org.diorite.commons.io.DioriteFileUtils
import pl.mplauncher.launcher.MPLauncherApp
import pl.mplauncher.launcher.config.MPConfigManager

import java.util.concurrent.TimeUnit

class MPLocaleManagerImpl implements MPLocaleManager {

    public final static File LOCALES_DIRECTORY = new File("lang")

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

        MPLocaleFile lang = this.localesCache.getIfPresent(locale)
        if (lang == null) {
            lang = this.localesMap.computeIfAbsent(locale, { MPLocale targetLocale ->
                File bindFile = new File(LOCALES_DIRECTORY, locale.getName() + ".yml")
                boolean exists = bindFile.exists()

                if (!exists) {
                    println(LOCALES_DIRECTORY.getName() + "/" + locale.getName() + ".yml")

                    InputStream stream = MPLauncherApp.class.getClassLoader()
                            .getResourceAsStream(LOCALES_DIRECTORY.getName() + "/" + locale.getName() + ".yml")

                    if (stream != null) {
                        DioriteFileUtils.createFile(bindFile)
                        InputStreamReader reader = new InputStreamReader(stream)
                        FileWriter writer = new FileWriter(bindFile)

                        IOUtils.copy(reader, writer)

                        reader.close()
                        writer.close()
                    }
                }

                lang = this.manager.getConfig(MPLocaleFile.class, bindFile)
                this.localesCache.put(locale, lang)

                return lang
            })
        }

        return lang
    }

}

