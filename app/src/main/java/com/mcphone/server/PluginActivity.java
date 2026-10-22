package com.mcphone.server;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Installiert Plugin-JARs per Download-Link oder Import aus dem Handy-Speicher (Mehrfachauswahl). */
public class PluginActivity extends Activity {
    private static final int REQ_IMPORT = 4101;
    private Prefs p;
    private String serverId;
    private LinearLayout root, installedList;
    private EditText urlInput;
    private TextView status;
    private boolean busy;
    private final ExecutorService io = Executors.newSingleThreadExecutor();
    private final android.os.Handler main = new android.os.Handler(android.os.Looper.getMainLooper());

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        Ui.apply(this);
        Dirs.init(this);
        serverId = ServerProfiles.safeId(getIntent().getStringExtra("server_id"));
        p = new Prefs(this, serverId);
        build();
    }

    private void build() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(Ui.dp(this, 20), Ui.dp(this, 20), Ui.dp(this, 20), Ui.dp(this, 16));
        root.setBackgroundColor(Ui.BG);
        root.setFitsSystemWindows(true);
        setContentView(root);

        root.addView(Ui.text(this, "$ plugin install", 12, Ui.ACCENT));
        TextView title = Ui.text(this, ServerProfiles.name(this, serverId), 26, Ui.TEXT);
        title.setTypeface(Ui.font(this), android.graphics.Typeface.BOLD);
        root.addView(title, Ui.row(this, 4));
        root.addView(Ui.text(this, L.t("Plugin-Installer"), 14, Ui.DIM), Ui.row(this, 2));

        ScrollView sc = new ScrollView(this);
        LinearLayout body = new LinearLayout(this);
        body.setOrientation(LinearLayout.VERTICAL);
        sc.addView(body, new ScrollView.LayoutParams(-1, -2));
        root.addView(sc, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout card = Ui.card(this);
        card.addView(Ui.label(this, L.t("JAR-Downloadlink")));
        urlInput = Ui.input(this, p.s("pluginUrl", ""), "https://…/plugin.jar", false);
        urlInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        urlInput.setSingleLine(true);
        card.addView(urlInput, Ui.row(this, 8));
        TextView note = Ui.text(this, L.t("Füge einen direkten HTTPS-Link zu einer .jar-Datei ein. Nutze nur Plugins aus Quellen, denen du vertraust."), 12, Ui.DIM);
        note.setLineSpacing(0, 1.15f);
        card.addView(note, Ui.row(this, 8));
        TextView install = Ui.button(this, L.t("Plugin installieren"), true);
        install.setOnClickListener(v -> installPlugin());
        card.addView(install, Ui.row(this, 12));
        body.addView(card);

        LinearLayout imp = Ui.card(this);
        imp.addView(Ui.label(this, L.t("Aus dem Handy")));
        TextView impNote = Ui.text(this, L.t("Wähle eine oder mehrere .jar-Dateien aus dem Speicher deines Handys aus. Du kannst mehrere Dateien auf einmal markieren."), 12, Ui.DIM);
        impNote.setLineSpacing(0, 1.15f);
        imp.addView(impNote, Ui.row(this, 8));
        TextView pick = Ui.button(this, L.t("Dateien aus dem Handy importieren"), false);
        pick.setOnClickListener(v -> pickFiles());
        imp.addView(pick, Ui.row(this, 12));
        body.addView(imp, Ui.row(this, 14));

        status = Ui.text(this, "", 12, Ui.DIM);
        status.setLineSpacing(0, 1.15f);
        body.addView(status, Ui.row(this, 12));

        LinearLayout current = Ui.card(this);
        current.addView(Ui.label(this, L.t("Installierte JAR-Dateien")));
        installedList = new LinearLayout(this);
        installedList.setOrientation(LinearLayout.VERTICAL);
        current.addView(installedList, Ui.row(this, 8));
        body.addView(current, Ui.row(this, 12));

        TextView back = Ui.button(this, L.t("Zurück zur Serverliste"), false);
        back.setOnClickListener(v -> finish());
        root.addView(back, Ui.row(this, 12));
        refreshPlugins();
        if (!"paper".equalsIgnoreCase(p.s("software", "paper"))) {
            say(L.t("Dieser Server ist Vanilla. Plugins benötigen Paper; ändere die Server-Software im Setup."), Ui.WARN);
        }
    }

    private void say(String msg, int color) {
        status.setText(msg);
        status.setTextColor(color);
    }

    /** Prüft, ob gerade Plugins installiert werden dürfen; zeigt sonst den Grund an. */
    private boolean canInstall() {
        if (busy) return false;
        if (!"paper".equalsIgnoreCase(p.s("software", "paper"))) {
            say(L.t("✗ Plugins funktionieren nur mit Paper, nicht mit Vanilla."), Ui.ERR);
            return false;
        }
        if (ServerService.running && serverId.equals(ServerService.activeServerId)) {
            say(L.t("Bitte stoppe diesen Server vor der Installation."), Ui.WARN);
            return false;
        }
        return true;
    }

    private void installPlugin() {
        if (!canInstall()) return;
        final String link = urlInput.getText().toString().trim();
        final String filename;
        try {
            URL u = new URL(link);
            if (!"https".equalsIgnoreCase(u.getProtocol())) throw new IllegalArgumentException(L.t("Nur HTTPS-Links sind erlaubt."));
            String path = u.getPath();
            filename = path.substring(path.lastIndexOf('/') + 1);
            if (!filename.toLowerCase(Locale.ROOT).endsWith(".jar") || !filename.matches("[A-Za-z0-9._-]{1,120}\\.[Jj][Aa][Rr]"))
                throw new IllegalArgumentException(L.t("Der Link muss direkt auf einen Dateinamen mit .jar enden."));
        } catch (Exception e) {
            say(L.t("✗ Ungültiger Link: ") + e.getMessage(), Ui.ERR);
            return;
        }
        busy = true;
        urlInput.setEnabled(false);
        say(L.t("Download wird gestartet: ") + filename, Ui.DIM);
        p.put("pluginUrl", link);
        io.execute(() -> {
            try {
                File plugins = new File(Dirs.server(this, serverId), "plugins");
                if (!plugins.exists() && !plugins.mkdirs()) throw new IOException(L.t("Plugin-Ordner konnte nicht angelegt werden."));
                File dest = new File(plugins, filename);
                Net.download(link, dest, msg -> main.post(() -> status.setText(msg)), filename);
                main.post(() -> {
                    busy = false;
                    say(L.t("✓ Installiert: ") + filename + L.t(". Der Server lädt das Plugin beim nächsten Start."), Ui.OK);
                    urlInput.setEnabled(true);
                    refreshPlugins();
                });
            } catch (Exception e) {
                main.post(() -> {
                    busy = false;
                    say(L.t("✗ Installation fehlgeschlagen: ") + String.valueOf(e.getMessage()), Ui.ERR);
                    urlInput.setEnabled(true);
                });
            }
        });
    }

    // ---------- Import aus dem Handy-Speicher ----------

    private void pickFiles() {
        if (!canInstall()) return;
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        i.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"application/java-archive", "application/x-java-archive",
                "application/octet-stream", "application/zip"});
        try {
            startActivityForResult(i, REQ_IMPORT);
        } catch (Exception e) {
            say(L.t("✗ Dateiauswahl konnte nicht geöffnet werden."), Ui.ERR);
        }
    }

    @Override protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (req != REQ_IMPORT || res != RESULT_OK || data == null) return;
        List<Uri> uris = new ArrayList<>();
        ClipData cd = data.getClipData();
        if (cd != null) {
            for (int i = 0; i < cd.getItemCount(); i++) uris.add(cd.getItemAt(i).getUri());
        } else if (data.getData() != null) {
            uris.add(data.getData());
        }
        if (!uris.isEmpty()) importFiles(uris);
    }

    private void importFiles(final List<Uri> uris) {
        if (!canInstall()) return;
        busy = true;
        say(L.t("Dateien werden importiert …"), Ui.DIM);
        io.execute(() -> {
            int ok = 0, bad = 0;
            File plugins = new File(Dirs.server(this, serverId), "plugins");
            if (!plugins.isDirectory()) plugins.mkdirs();
            for (Uri u : uris) {
                File tmp = null;
                try {
                    String name = safeName(displayName(u));
                    if (name == null || !plugins.isDirectory()) { bad++; continue; }
                    tmp = new File(plugins, name + ".part");
                    try (InputStream in = getContentResolver().openInputStream(u);
                         OutputStream out = new FileOutputStream(tmp)) {
                        if (in == null) throw new IOException("stream");
                        byte[] buf = new byte[65536];
                        int n;
                        while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
                    }
                    File dest = new File(plugins, name);
                    if (dest.exists() && !dest.delete()) throw new IOException("replace");
                    if (!tmp.renameTo(dest)) throw new IOException("rename");
                    ok++;
                } catch (Exception e) {
                    bad++;
                    if (tmp != null) tmp.delete();
                }
            }
            final int fo = ok, fb = bad;
            main.post(() -> {
                busy = false;
                refreshPlugins();
                if (fo == 0) {
                    say(L.t("✗ Keine Datei importiert. Es werden nur .jar-Dateien unterstützt."), Ui.ERR);
                } else {
                    String msg = L.f("✓ %d Plugin(s) importiert. Der Server lädt sie beim nächsten Start.", fo);
                    if (fb > 0) msg += "\n" + L.f("%d Datei(en) übersprungen (keine gültige .jar-Datei) oder fehlgeschlagen.", fb);
                    say(msg, fb > 0 ? Ui.WARN : Ui.OK);
                }
            });
        });
    }

    private String displayName(Uri u) {
        try (Cursor c = getContentResolver().query(u, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null)) {
            if (c != null && c.moveToFirst()) {
                String n = c.getString(0);
                if (n != null && !n.isEmpty()) return n;
            }
        } catch (Exception ignored) { }
        return u.getLastPathSegment();
    }

    /** Liefert einen sicheren Dateinamen (nur .jar, keine Pfade) oder null. */
    static String safeName(String n) {
        if (n == null) return null;
        int slash = Math.max(n.lastIndexOf('/'), n.lastIndexOf('\\'));
        if (slash >= 0) n = n.substring(slash + 1);
        if (!n.toLowerCase(Locale.ROOT).endsWith(".jar")) return null;
        n = n.replaceAll("[^A-Za-z0-9._-]", "_");
        if (n.length() > 120) n = n.substring(n.length() - 120);
        if (n.startsWith(".")) n = "_" + n;
        return n.length() > 4 ? n : null;
    }

    private void refreshPlugins() {
        if (installedList == null) return;
        installedList.removeAllViews();
        File dir = new File(Dirs.server(this, serverId), "plugins");
        File[] fs = dir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".jar"));
        if (fs == null || fs.length == 0) {
            installedList.addView(Ui.text(this, L.t("Noch keine Plugins installiert."), 13, Ui.DIM));
            return;
        }
        Arrays.sort(fs, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        for (File f : fs) {
            TextView t = Ui.text(this, "•  " + f.getName(), 13, Ui.TEXT);
            installedList.addView(t, Ui.row(this, 4));
        }
    }

    @Override protected void onDestroy() {
        io.shutdownNow();
        super.onDestroy();
    }
}
