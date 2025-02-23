package mvc.model;

import java.util.prefs.Preferences;

public class SettingsModel {
    private String[] settings = new String[4];
    private static final Preferences prefs = Preferences.userNodeForPackage(SettingsModel.class);
    public static void saveSetting(String key, String value) {
        prefs.put(key, value);
    }
    public static String getConfig(String key) {
        return prefs.get(key, null);
    }
    public static String[] getConfig() {
        String[] str = new String[4];
        str[0] = getConfig("saveLocation");
        str[1] = getConfig("autoSave");
        str[2] = getConfig("autoLoad");
        str[3] = getConfig("theme");
        return str;
    }
    public String[] loadConfig() {
        settings = SettingsModel.getConfig();
        return settings;
    }


}
