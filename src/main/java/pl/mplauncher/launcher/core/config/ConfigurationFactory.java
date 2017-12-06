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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigurationFactory {

    static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static AppConfiguration appConfiguration = null;
    private static UsersRepository usersRepository = null;

    public static AppConfiguration getAppSetup() {
        return getAppSetup(false);
    }

    public static AppConfiguration getAppSetup(boolean fromFile) {
        if (appConfiguration == null) {
            appConfiguration = new AppConfiguration();
        }

        if (fromFile) {
            appConfiguration.load();
        }

        return appConfiguration;
    }

    public static UsersRepository getUsersRepository() {
        return getUsersRepository(false);
    }

    public static UsersRepository getUsersRepository(boolean fromFile) {
        if (usersRepository == null) {
            usersRepository = new UsersRepository();
        }

        if (fromFile) {
            usersRepository.load();
        }

        return usersRepository;
    }

}
