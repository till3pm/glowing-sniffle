package com.mcphone.server;

import android.content.Context;
import android.content.SharedPreferences;

/** App-weite Einstellungen (Sprache, Design) – unabhängig von den Serverprofilen. */
final class AppSettings {
    static final String[] LANG_CODES = {"system", "de", "en", "es", "fr", "tr"};
    static final String[] LANG_NAMES = {"", "Deutsch", "English", "Español", "Français", "Türkçe"};
    static final String[] THEME_CODES = {"system", "light", "dark"};

    private AppSettings() { }

    private static SharedPreferences sp(Context c) {
        return c.getApplicationContext().getSharedPreferences("mc_app", Context.MODE_PRIVATE);
    }

    static String lang(Context c) { return sp(c).getString("lang", "system"); }
    static void setLang(Context c, String v) { sp(c).edit().putString("lang", v).apply(); }
    static String theme(Context c) { return sp(c).getString("theme", "system"); }
    static void setTheme(Context c, String v) { sp(c).edit().putString("theme", v).apply(); }
}
