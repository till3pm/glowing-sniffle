package com.mcphone.server;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextWatcher;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

final class Ui {
    static int BG = 0xFFFAF9F5, CARD = 0xFFFFFFFF, FIELD = 0xFFF3F1EA, STROKE = 0xFFE4E0D6,
            TEXT = 0xFF1F1E1D, DIM = 0xFF6F6B63, ACCENT = 0xFFD97757, ONACC = 0xFFFFFFFF,
            OK = 0xFF2E7D4F, WARN = 0xFFB7791F, ERR = 0xFFC2412D, HINT = 0xFF9A968D;
    static boolean dark;

    static boolean resolveDark(Context c) {
        String t = AppSettings.theme(c);
        if ("dark".equals(t)) return true;
        if ("light".equals(t)) return false;
        return (c.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    /** Setzt Sprache und Farbpalette (hell/dunkel) gemäß den App-Einstellungen. */
    static void init(Context c) {
        L.init(c);
        dark = resolveDark(c);
        if (dark) {
            BG = 0xFF1A1917; CARD = 0xFF262522; FIELD = 0xFF302F2B; STROKE = 0xFF45433D;
            TEXT = 0xFFF0EEE6; DIM = 0xFFA6A298; ACCENT = 0xFFD97757; ONACC = 0xFFFFFFFF;
            OK = 0xFF5FBF86; WARN = 0xFFE0A840; ERR = 0xFFE5715C; HINT = 0xFF8A867D;
        } else {
            BG = 0xFFFAF9F5; CARD = 0xFFFFFFFF; FIELD = 0xFFF3F1EA; STROKE = 0xFFE4E0D6;
            TEXT = 0xFF1F1E1D; DIM = 0xFF6F6B63; ACCENT = 0xFFD97757; ONACC = 0xFFFFFFFF;
            OK = 0xFF2E7D4F; WARN = 0xFFB7791F; ERR = 0xFFC2412D; HINT = 0xFF9A968D;
        }
    }

    /** In jeder Activity als Erstes aufrufen: Palette, Fensterhintergrund und Systemleisten. */
    static void apply(Activity a) {
        init(a);
        Window w = a.getWindow();
        w.setBackgroundDrawable(new ColorDrawable(BG));
        w.setStatusBarColor(BG);
        w.setNavigationBarColor(BG);
        View d = w.getDecorView();
        int f = d.getSystemUiVisibility();
        int light = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        d.setSystemUiVisibility(dark ? (f & ~light) : (f | light));
    }

    static String sig(Context c) {
        return AppSettings.theme(c) + "|" + AppSettings.lang(c) + "|" + resolveDark(c);
    }

    /** Dialog mit abgerundeten Ecken im passenden Hell-/Dunkel-Stil. */
    static AlertDialog.Builder dialog(Context c) {
        return new AlertDialog.Builder(c, dark ? R.style.DialogDark : R.style.DialogLight);
    }

    /** Gibt einem Dialog-Eingabefeld seitlichen Abstand, damit die runden Ecken sichtbar sind. */
    static View pad(Context c, View v) {
        FrameLayout f = new FrameLayout(c);
        f.setPadding(dp(c, 22), dp(c, 8), dp(c, 22), 0);
        f.addView(v, new FrameLayout.LayoutParams(-1, -2));
        return f;
    }

    /** Drei-Punkte-Symbol (vertikal) für das Einstellungs-Menü. */
    static View dots(Context c) {
        View v = new View(c) {
            private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            @Override protected void onDraw(Canvas cv) {
                paint.setColor(TEXT);
                float cx = getWidth() / 2f, cy = getHeight() / 2f;
                float r = dp(getContext(), 2.2f), gap = dp(getContext(), 7);
                cv.drawCircle(cx, cy - gap, r, paint);
                cv.drawCircle(cx, cy, r, paint);
                cv.drawCircle(cx, cy + gap, r, paint);
            }
        };
        v.setBackground(box(c, FIELD, STROKE, 14));
        v.setClickable(true);
        v.setContentDescription(L.t("Einstellungen"));
        return v;
    }

    interface Pick { void on(int index); }

    abstract static class Watch implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int a, int b, int c) { }
        public void onTextChanged(CharSequence s, int a, int b, int c) { }
    }

    private static Typeface tf;

    static Typeface font(Context c) {
        if (tf == null) tf = Fonts.load(c);
        return tf;
    }

    static int dp(Context c, float v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, c.getResources().getDisplayMetrics());
    }

    static GradientDrawable box(Context c, int fill, int stroke, float radiusDp) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(fill);
        g.setCornerRadius(dp(c, radiusDp));
        if (stroke != 0) g.setStroke(dp(c, 1), stroke);
        return g;
    }

    static TextView text(Context c, String s, float sp, int color) {
        TextView t = new TextView(c);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        t.setTypeface(font(c));
        return t;
    }

    static LinearLayout.LayoutParams row(Context c, int topDp) {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.topMargin = dp(c, topDp);
        return p;
    }

    static LinearLayout card(Context c) {
        LinearLayout l = new LinearLayout(c);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(dp(c, 16), dp(c, 14), dp(c, 16), dp(c, 14));
        l.setBackground(box(c, CARD, STROKE, 16));
        return l;
    }

    static TextView label(Context c, String s) {
        TextView t = text(c, s.toUpperCase(), 11, DIM);
        t.setLetterSpacing(0.12f);
        return t;
    }

    static TextView button(Context c, String s, boolean primary) {
        TextView t = text(c, s, 15, primary ? ONACC : TEXT);
        t.setTypeface(font(c), Typeface.BOLD);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(c, 18), dp(c, 14), dp(c, 18), dp(c, 14));
        t.setBackground(box(c, primary ? ACCENT : 0, primary ? 0 : STROKE, 14));
        t.setClickable(true);
        return t;
    }

    static EditText input(Context c, String value, String hint, boolean number) {
        EditText e = new EditText(c);
        e.setText(value);
        e.setHint(hint);
        e.setTextColor(TEXT);
        e.setHintTextColor(HINT);
        e.setTypeface(font(c));
        e.setTextSize(15);
        e.setSingleLine(true);
        e.setBackground(box(c, FIELD, STROKE, 12));
        e.setPadding(dp(c, 14), dp(c, 12), dp(c, 14), dp(c, 12));
        if (number) e.setInputType(InputType.TYPE_CLASS_NUMBER);
        return e;
    }

    static void chip(Context c, TextView t, boolean on) {
        t.setTextColor(on ? ONACC : TEXT);
        t.setBackground(box(c, on ? ACCENT : FIELD, on ? 0 : STROKE, 12));
    }

    static LinearLayout chips(Context c, String[] labels, int sel, Pick cb) {
        LinearLayout row = new LinearLayout(c);
        TextView[] tv = new TextView[labels.length];
        for (int i = 0; i < labels.length; i++) {
            final int idx = i;
            TextView t = text(c, labels[i], 14, TEXT);
            t.setGravity(Gravity.CENTER);
            t.setPadding(dp(c, 6), dp(c, 12), dp(c, 6), dp(c, 12));
            chip(c, t, i == sel);
            t.setOnClickListener(v -> {
                for (int j = 0; j < tv.length; j++) chip(c, tv[j], j == idx);
                cb.on(idx);
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1);
            if (i > 0) lp.leftMargin = dp(c, 8);
            row.addView(t, lp);
            tv[i] = t;
        }
        return row;
    }
}
