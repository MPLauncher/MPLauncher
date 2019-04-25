/*
 * Copyright 2017-2019 MPLauncher Team
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

package pl.mplauncher.launcher.core.manager;

import com.jfoenix.controls.JFXListView;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pl.mplauncher.launcher.core.enums.ModpackType;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;
import pl.mplauncher.launcher.core.screen.layout.component.ModpackItem;

public class ModpackManager {

    private Map<String, ModpackItem> items = new HashMap<>();

    public Optional<ModpackItem> findPackByName(String name) {
        return items.values().stream()
            .filter(pack -> pack.getName().equalsIgnoreCase(name))
            .peek(System.out::println)
            .findFirst();
    }

    public Optional<ModpackItem> findPackByType(MainLayout layout, ModpackType type, String name) {
        JFXListView<ModpackItem> list;

        switch (type) {
            case VANILLA:
                list = layout.vanillaModpackList;
                break;
            case FTB:
                list = layout.ftbModpackList;
                break;
            case OWN:
                list = layout.ownModpackList;
                break;
            case KENPACK:
                list = layout.kenpackModpackList;
                break;
            default:
                list = layout.otherModpackList;
                break;
        }

        return list.getItems().stream()
            .filter(item -> item.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    public void addPack(ModpackItem item) {
        items.put(item.getName(), item);
    }
}
