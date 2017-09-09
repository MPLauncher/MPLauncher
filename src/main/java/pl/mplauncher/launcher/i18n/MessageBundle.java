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
package pl.mplauncher.launcher.i18n;

import java.util.*;

public class MessageBundle {

    private static List<MessageBundle> messageBundles = new ArrayList<>();
    private static MessageBundle currentLanguage;
    private static MessageBundle defaultLanguage;
    private Locale locale;
    private Map<String, String> messages = new HashMap<>();

    public MessageBundle(Locale locale) {
        this.locale = locale;
        messageBundles.add(this);
    }

    public void addMessage(String key, String value) {
        this.messages.put(key, value);
    }

    public void setMessages(Map<String, String> newMessages) {
        this.messages = newMessages;
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, MessageBundle.getDefaultLanguage().getMessage(key));
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public Locale getLocale() {
        return locale;
    }

    public static List<MessageBundle> getMessageBundles(){
        return messageBundles;
    }

    public static MessageBundle getCurrentLanguage(){
        return currentLanguage;
    }

    public static void setCurrentLanguage(MessageBundle newCurrentLanguage){
        currentLanguage = newCurrentLanguage;
    }

    public static MessageBundle getBundle(Locale l){
        //could use streams but probably slower
        for (MessageBundle mb : messageBundles) {
            if (mb.getLocale().equals(l)) {
                return mb;
            }
        }
        return null;
    }

    public static MessageBundle getDefaultLanguage(){
        return defaultLanguage;
    }

    public static void setDefaultLanguage(MessageBundle newDefaultLanguage){
        defaultLanguage = newDefaultLanguage;
    }

}
