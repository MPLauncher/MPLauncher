/*
 * Copyright 2017-2018 MPLauncher Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mplauncher.launcher.core.config.smart;

import pl.mplauncher.launcher.core.config.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SmartConfiguration<T extends Configuration> {

    private T configuration;
    private List<SmartOption> allOptions = new ArrayList<>();

    public SmartConfiguration(T configuration) {
        this.configuration = configuration;
    }

    public List<SmartOption> getOptions() {
        load();
        return allOptions;
    }

    public SmartOption getOption(String name) {
        if (allOptions.isEmpty())
            load();

        return allOptions
                .stream()
                .filter(smartOption -> smartOption.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void save() {
        Class configClass = configuration.getClass();

        for (Field field : configClass.getDeclaredFields()) {
            try {
                if (!Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);
                    field.set(configuration, getOption(field.getName()).getValue());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        configuration.save();
    }

    public void load() {
        Class configClass = configuration.getClass();

        for (Field field : configClass.getDeclaredFields()) {
            try {
                if (!Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);

                    String name = field.getName();
                    Object value = field.get(configuration);

                    String[] options = null;
                    SmartOptionType type = SmartOptionType.CHECKBOX;

                    if (field.isAnnotationPresent(SmartConfigurationOption.class)) {
                        SmartConfigurationOption option = field.getAnnotation(SmartConfigurationOption.class);
                        options = option.options();
                        type = option.type();
                    }

                    allOptions.add(new SmartOption(name, value, options, type));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
