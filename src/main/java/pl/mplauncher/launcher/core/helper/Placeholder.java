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

package pl.mplauncher.launcher.core.helper;

import com.google.common.base.Charsets;
import pl.mplauncher.launcher.core.api.mp.component.dto.News;
import pl.mplauncher.launcher.core.config.UserProfile;
import pl.mplauncher.launcher.core.control.QuestionOverlay;
import pl.mplauncher.launcher.core.enums.ModpackType;
import pl.mplauncher.launcher.core.screen.MainScreen;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;
import pl.mplauncher.launcher.core.screen.layout.component.ModpackItem;
import pl.mplauncher.launcher.core.screen.layout.component.NewsList;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Placeholder {

    public static void populateServerList(MainScreen mainScreen) {
        MainLayout layout = mainScreen.layout;

        // Set servers!
        for (int x = 0; x < 53; x++) {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < random.nextInt(30) + 10; i++) {
                char ch = chars[random.nextInt(chars.length)];
                sb.append(ch);
            }

            if (x < 3) {
                layout.addServerToFavoriteList(sb.toString(), "1.11.2",
                        random.nextInt(100), random.nextInt(100) + 100);
            } else {
                layout.addServerToOtherList(sb.toString(), "1.11.2",
                        random.nextInt(100), random.nextInt(100) + 100);
            }
        }
    }

    public static void populateModpackList(MainScreen screen) {
        MainLayout layout = screen.layout;
        Random random = new Random();

        for (int x = 0; x < 18; x++) {
                int rand = random.nextInt(100);

                if (rand < 15) {
                    layout.addModpackToList(ModpackType.VANILLA,"HusialkeBox", "1.15", "To jest desc. vanilli", random.nextInt(15));
                } else if (rand > 15 && rand < 30) {
                    layout.addModpackToList(ModpackType.FTB, "FTB Industrjal", "Beta 1.3", "Uhu lala jak ta lala", random.nextInt(15));
                } else if (rand > 30 && rand < 45) {
                    layout.addModpackToList(ModpackType.KENPACK, "Techblock XD", "0.1.0", "Smiglo ci zasmiglo?", random.nextInt(15));
                } else if (rand > 45 && rand < 60) {
                    layout.addModpackToList(ModpackType.OWN, "Moja wlasna paczka :>", "1.11", "Awuuu moja paczusia <3", random.nextInt(15));
                } else {
                    layout.addModpackToList(ModpackType.OTHER, "MineModders czy jakos tak", "1.0", "Czesc Michma!", random.nextInt(15));
            }
        }
    }

    public static void populateNewsList(MainLayout layout) {
        // Set news!
        for (int x = 0; x < 20; x++) {
            if (x < 3) {
                ((NewsList) layout.centerGridPane.getChildren().get(0)).addNews(NewsList.Category.Important, "NAJLEPSZY NEWS ŚWIATA", new Date());
            } else if (x < 18) {
                ((NewsList) layout.centerGridPane.getChildren().get(0)).addNews(NewsList.Category.Latest, "NAJLEPSZY NEWS ŚWIATA", new Date());
            } else {
                ((NewsList) layout.centerGridPane.getChildren().get(0)).addNews(NewsList.Category.Technical, "NAJLEPSZY NEWS ŚWIATA", new Date());
            }
        }
    }

    private static final String placeholderLogin = "Test";

    public static boolean handleLogin(String login, String password) {
        return (login.equals(placeholderLogin) && password.equals("ForMe"));
    }

    public static UserProfile getTestUser() {
        return new UserProfile(placeholderLogin, UUID.nameUUIDFromBytes((placeholderLogin).getBytes(Charsets.UTF_8)),
                "\" \"", null, false, UserProfile.Type.PREMIUM);
    }

    public static News getNews() {
        News placeholder = new News();
        placeholder.setTitle("NOWY WYGLĄD?");

        String body = "Witajcie gracze i graczki!"
                + System.lineSeparator() + System.lineSeparator() +
                "Jako, iż nasza ekipa robi wszystko ze starannością i dbałością dla was, postanowiłem " +
                "rozpocząć tworzenie nowego stylu launchera!" + System.lineSeparator() +
                "Styl przybrał nazwę „Callipso” i prawdopodobnie do 15-30 dni uda mi się stworzyć jego layout."
                + System.lineSeparator() +
                "Na obecną chwilę mogę napisać, iż szykuje się pare dodatków w nowym wyglądzie, " +
                "całkowita zmiana stylu oraz pełno eastereggów." + System.lineSeparator() + System.lineSeparator() +
                "Czytaj więcej.";

        placeholder.setBody(body);
        placeholder.setAuthor("IceMeltt");
        placeholder.setDate(0);

        URL imageUrl = Thread.currentThread().getContextClassLoader().getResource("images/mc.jpg");
        if (imageUrl != null) {
            placeholder.setImageURL(imageUrl.toString());
        }

        return placeholder;
    }

    public static void alertNotImplemented() {
        new QuestionOverlay(QuestionOverlay.DialogType.Ok, "Not implemented", "This is not implemented yet!");
    }

}
