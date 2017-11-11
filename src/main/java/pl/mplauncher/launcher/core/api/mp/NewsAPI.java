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
package pl.mplauncher.launcher.core.api.mp;

import pl.mplauncher.launcher.core.api.mp.component.dto.News;

import java.net.URL;

public class NewsAPI {

    public News latest() {
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

}
