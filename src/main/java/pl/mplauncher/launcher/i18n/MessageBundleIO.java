package pl.mplauncher.launcher.i18n;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Locale;

public class MessageBundleIO {

    public static void load() throws IOException{
        File dir = new File(System.getenv("APPDATA")+ File.separator + ".mplauncher" + File.separator + "lang");
         if (!dir.exists())
         {
            dir.mkdirs();
            File tmp = new File(dir, "tmp.json");
            tmp.createNewFile();
            Files.delete(tmp.toPath());
        }
        if(dir.listFiles().length > 0){
            for(File f : dir.listFiles()) {
                if (FilenameUtils.getExtension(f.getPath()).equals("json")) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new FileReader(f.getPath()));
                    MessageBundle mb = gson.fromJson(reader, MessageBundle.class);
                    if(Locale.getDefault().equals(mb.getLocale())){
                        MessageBundle.setCurrentLanguage(mb);
                    }else if(mb.getLocale().equals(Locale.US)){
                        MessageBundle.setDefaultLanguage(mb);
                    }
                }
            }
        }else{
            //TODO download files from server
        }
    }
}
