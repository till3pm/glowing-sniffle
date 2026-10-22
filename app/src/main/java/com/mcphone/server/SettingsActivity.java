package com.mcphone.server;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** Einstellungen: Sprache und Light-/Dark-Design. */
public class SettingsActivity extends Activity {
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        Ui.apply(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(Ui.dp(this, 20), Ui.dp(this, 20), Ui.dp(this, 20), Ui.dp(this, 16));
        root.setBackgroundColor(Ui.BG);
        root.setFitsSystemWindows(true);
        setContentView(root);

        root.addView(Ui.text(this, "$ settings", 12, Ui.ACCENT));
        TextView title = Ui.text(this, L.t("Einstellungen"), 29, Ui.TEXT);
        title.setTypeface(Ui.font(this), android.graphics.Typeface.BOLD);
        root.addView(title, Ui.row(this, 4));

        ScrollView sc = new ScrollView(this);
        LinearLayout body = new LinearLayout(this);
        body.setOrientation(LinearLayout.VERTICAL);
        sc.addView(body, new ScrollView.LayoutParams(-1, -2));
        LinearLayout.LayoutParams sl = new LinearLayout.LayoutParams(-1, 0, 1);
        sl.topMargin = Ui.dp(this, 16);
        root.addView(sc, sl);

        // Sprache
        LinearLayout langCard = Ui.card(this);
        langCard.addView(Ui.label(this, L.t("Sprache")));
        String cur = AppSettings.lang(this);
        for (int i = 0; i < AppSettings.LANG_CODES.length; i++) {
            final String code = AppSettings.LANG_CODES[i];
            boolean on = code.equals(cur);
            String name = i == 0 ? L.t("Systemstandard") : AppSettings.LANG_NAMES[i];
            TextView r = Ui.text(this, (on ? "▸ " : "   ") + name, 16, on ? Ui.ONACC : Ui.TEXT);
            r.setPadding(Ui.dp(this, 14), Ui.dp(this, 12), Ui.dp(this, 14), Ui.dp(this, 12));
            r.setBackground(Ui.box(this, on ? Ui.ACCENT : Ui.FIELD, on ? 0 : Ui.STROKE, 12));
            r.setOnClickListener(v -> {
                if (code.equals(AppSettings.lang(this))) return;
                AppSettings.setLang(this, code);
                recreate();
            });
            langCard.addView(r, Ui.row(this, i == 0 ? 10 : 6));
        }
        body.addView(langCard);

        // Design
        LinearLayout themeCard = Ui.card(this);
        themeCard.addView(Ui.label(this, L.t("Design")));
        String t = AppSettings.theme(this);
        int sel = 0;
        for (int i = 0; i < AppSettings.THEME_CODES.length; i++) if (AppSettings.THEME_CODES[i].equals(t)) sel = i;
        themeCard.addView(Ui.chips(this, new String[]{L.t("System"), L.t("Hell"), L.t("Dunkel")}, sel, i -> {
            if (AppSettings.THEME_CODES[i].equals(AppSettings.theme(this))) return;
            AppSettings.setTheme(this, AppSettings.THEME_CODES[i]);
            recreate();
        }), Ui.row(this, 10));
        body.addView(themeCard, Ui.row(this, 14));

        TextView back = Ui.button(this, L.t("Zurück"), false);
        back.setOnClickListener(v -> finish());
        root.addView(back, Ui.row(this, 12));
    }
}
