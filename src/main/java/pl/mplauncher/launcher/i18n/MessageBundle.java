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
