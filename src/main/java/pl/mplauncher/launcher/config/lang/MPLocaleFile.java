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

import org.diorite.config.Config;
import org.diorite.config.annotations.CustomKey;
import org.diorite.config.annotations.GroovyValidator;

import java.util.Arrays;
import java.util.Collection;

public interface MPLocaleFile extends Config {

    @CustomKey("locale")
    @GroovyValidator(isTrue = "x != null", elseThrow = "Locale can not be null!")
    default MPLocale getLocale() { // default language: EN
        return MPLocale.EN;
    }

    @CustomKey("translation-authors")
    @GroovyValidator(isTrue = "x != null && x.size() > 0", elseThrow = "TranslationAuthors can not be null!")
    default Collection<String> getTranslationAuthors() {
        return Arrays.asList(
                "MPLauncher Team"
        );
    }

}
