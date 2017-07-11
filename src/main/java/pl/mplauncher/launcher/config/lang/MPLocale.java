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

import java.util.Locale;

public enum MPLocale {

    EN("en"),
    PL("pl");

    private final String name;

    MPLocale(String name) {
        Validate.isTrue(name != null, "Name can not be null!");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MPLocale findLocale(String name) {
        Validate.isTrue(name != null);

        for (MPLocale locale : values()) {
            if (locale.getName().equalsIgnoreCase(name)) {
                return locale;
            }
        }

        return null;
    }

    public static MPLocale getDefault() {
        String name = Locale.getDefault().getCountry();

        MPLocale locale = findLocale(name);
        if (locale == null) {
            locale = EN;
        }

        return locale;
    }

}
