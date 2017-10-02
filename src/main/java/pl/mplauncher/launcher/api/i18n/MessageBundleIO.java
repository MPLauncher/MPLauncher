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
package pl.mplauncher.launcher.api.i18n;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class MessageBundleIO {

    public final static Gson gson = new Gson();

    //Temporary for writing.
    public static void load() throws IOException {
        //InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("lang_en_EN.json");
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("lang_pl_PL.json");
        JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
        MessageBundle mb = gson.fromJson(reader, MessageBundle.class);
        MessageBundle.setCurrentLanguage(mb);
    }

    /*
    public static void load() throws IOException {
        File dir = new File(System.getenv("APPDATA") + File.separator + ".mplauncher" + File.separator + "lang");

        if (!dir.exists()) {
            dir.mkdirs();
            File tmp = new File(dir, "tmp.json");
            tmp.createNewFile();
            Files.delete(tmp.toPath());
        }

        if (dir.listFiles().length > 0) {
            for (File f : dir.listFiles()) {
                if (FilenameUtils.getExtension(f.getPath()).equals("json")) {
                    JsonReader reader = new JsonReader(new FileReader(f.getPath()));
                    MessageBundle mb = gson.fromJson(reader, MessageBundle.class);
                    if (Locale.getDefault().equals(mb.getLocale())) {
                        MessageBundle.setCurrentLanguage(mb);
                    } else if(mb.getLocale().equals(Locale.US)) {
                        MessageBundle.setDefaultLanguage(mb);
                    }
                }
            }
        } else {
            //TODO download files from server
        }
    }
    */
}
