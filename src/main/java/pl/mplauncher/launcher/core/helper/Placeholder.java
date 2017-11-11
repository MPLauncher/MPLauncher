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
package pl.mplauncher.launcher.core.helper;

import com.google.common.base.Charsets;
import pl.mplauncher.launcher.core.api.mp.component.dto.News;
import pl.mplauncher.launcher.core.config.UserProfile;
import pl.mplauncher.launcher.core.control.QuestionOverlay;
import pl.mplauncher.launcher.core.screen.MainScreen;
import pl.mplauncher.launcher.core.screen.layout.MainLayout;

import java.net.URL;
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
