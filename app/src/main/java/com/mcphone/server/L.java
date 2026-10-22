package com.mcphone.server;

import android.content.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Einfache Übersetzung: Der deutsche Text ist der Schlüssel.
 * Spalten je Zeile: de, en, es, fr, tr. Unbekannte Texte bleiben unverändert.
 */
final class L {
    private static final String[] CODES = {"de", "en", "es", "fr", "tr"};
    private static final String[][] T = {
        {"Wähle eine Config-Datei zum Anzeigen oder Bearbeiten.", "Choose a config file to view or edit.", "Elige un archivo de configuración para ver o editar.", "Choisis un fichier de configuration à afficher ou modifier.", "Görüntülemek veya düzenlemek için bir yapılandırma dosyası seç."},
        {"Bearbeiten", "Edit", "Editar", "Modifier", "Düzenle"},
        {"+  Config-Datei erstellen", "+  Create config file", "+  Crear archivo de configuración", "+  Créer un fichier de configuration", "+  Yapılandırma dosyası oluştur"},
        {"Zurück zur Serverliste", "Back to server list", "Volver a la lista de servidores", "Retour à la liste des serveurs", "Sunucu listesine dön"},
        {"Datei nicht gefunden.", "File not found.", "Archivo no encontrado.", "Fichier introuvable.", "Dosya bulunamadı."},
        {"Datei ist größer als 1 MB. Große Dateien werden nicht im Editor geöffnet.", "File is larger than 1 MB. Large files are not opened in the editor.", "El archivo supera 1 MB. Los archivos grandes no se abren en el editor.", "Le fichier dépasse 1 Mo. Les gros fichiers ne s'ouvrent pas dans l'éditeur.", "Dosya 1 MB'dan büyük. Büyük dosyalar düzenleyicide açılmaz."},
        {"Änderungen an Server-Configs gelten meist nach dem nächsten Neustart.", "Changes to server configs usually apply after the next restart.", "Los cambios en la configuración del servidor suelen aplicarse tras el próximo reinicio.", "Les modifications de la configuration du serveur s'appliquent généralement après le prochain redémarrage.", "Sunucu yapılandırma değişiklikleri genellikle bir sonraki yeniden başlatmada geçerli olur."},
        {"Änderungen speichern", "Save changes", "Guardar cambios", "Enregistrer les modifications", "Değişiklikleri kaydet"},
        {"Dateiliste", "File list", "Lista de archivos", "Liste des fichiers", "Dosya listesi"},
        {"Datei konnte nicht gelesen werden: ", "Could not read file: ", "No se pudo leer el archivo: ", "Impossible de lire le fichier : ", "Dosya okunamadı: "},
        {"✗ Datei ist größer als 1 MB und wurde nicht gespeichert.", "✗ File is larger than 1 MB and was not saved.", "✗ El archivo supera 1 MB y no se guardó.", "✗ Le fichier dépasse 1 Mo et n'a pas été enregistré.", "✗ Dosya 1 MB'dan büyük ve kaydedilmedi."},
        {"Ordner konnte nicht erstellt werden.", "Could not create folder.", "No se pudo crear la carpeta.", "Impossible de créer le dossier.", "Klasör oluşturulamadı."},
        {"✓ Gespeichert. Änderungen werden je nach Datei beim nächsten Start wirksam.", "✓ Saved. Depending on the file, changes take effect on the next start.", "✓ Guardado. Según el archivo, los cambios se aplican en el próximo inicio.", "✓ Enregistré. Selon le fichier, les modifications prennent effet au prochain démarrage.", "✓ Kaydedildi. Değişiklikler dosyaya göre bir sonraki başlatmada etkili olur."},
        {"Datei gespeichert: ", "File saved: ", "Archivo guardado: ", "Fichier enregistré : ", "Dosya kaydedildi: "},
        {"✗ Speichern fehlgeschlagen: ", "✗ Saving failed: ", "✗ Error al guardar: ", "✗ Échec de l'enregistrement : ", "✗ Kaydetme başarısız: "},
        {"z. B. plugins/Beispiel/config.yml", "e.g. plugins/Example/config.yml", "p. ej. plugins/Ejemplo/config.yml", "p. ex. plugins/Exemple/config.yml", "örn. plugins/Ornek/config.yml"},
        {"Config-Datei erstellen", "Create config file", "Crear archivo de configuración", "Créer un fichier de configuration", "Yapılandırma dosyası oluştur"},
        {"Relativer Pfad im Server-Ordner; erlaubt sind gängige Text-Configs.", "Relative path inside the server folder; common text configs are allowed.", "Ruta relativa dentro de la carpeta del servidor; se permiten configuraciones de texto comunes.", "Chemin relatif dans le dossier du serveur ; les fichiers de configuration texte courants sont autorisés.", "Sunucu klasörü içinde göreli yol; yaygın metin yapılandırmalarına izin verilir."},
        {"Abbrechen", "Cancel", "Cancelar", "Annuler", "İptal"},
        {"Erstellen", "Create", "Crear", "Créer", "Oluştur"},
        {"Dateiendung wird nicht unterstützt.", "File extension is not supported.", "La extensión de archivo no es compatible.", "L'extension du fichier n'est pas prise en charge.", "Dosya uzantısı desteklenmiyor."},
        {"Die Datei existiert bereits.", "The file already exists.", "El archivo ya existe.", "Le fichier existe déjà.", "Dosya zaten var."},
        {"Datei konnte nicht erstellt werden.", "Could not create file.", "No se pudo crear el archivo.", "Impossible de créer le fichier.", "Dosya oluşturulamadı."},
        {"Datei konnte nicht erstellt werden: ", "Could not create file: ", "No se pudo crear el archivo: ", "Impossible de créer le fichier : ", "Dosya oluşturulamadı: "},
        {"Bitte einen Dateipfad angeben.", "Please enter a file path.", "Indica una ruta de archivo.", "Indique un chemin de fichier.", "Lütfen bir dosya yolu gir."},
        {"Pfad außerhalb des Serverordners ist nicht erlaubt.", "Paths outside the server folder are not allowed.", "No se permiten rutas fuera de la carpeta del servidor.", "Les chemins hors du dossier du serveur ne sont pas autorisés.", "Sunucu klasörü dışındaki yollara izin verilmez."},
        {"Nur Text-Config-Dateien können bearbeitet werden.", "Only text config files can be edited.", "Solo se pueden editar archivos de configuración de texto.", "Seuls les fichiers de configuration texte peuvent être modifiés.", "Yalnızca metin yapılandırma dosyaları düzenlenebilir."},
        {"● offline", "● offline", "● desconectado", "● hors ligne", "● çevrimdışı"},
        {"● online", "● online", "● en línea", "● en ligne", "● çevrimiçi"},
        {"● startet", "● starting", "● iniciando", "● démarrage", "● başlıyor"},
        {"● anderer Server aktiv", "● another server active", "● otro servidor activo", "● autre serveur actif", "● başka sunucu etkin"},
        {"Spieler", "Players", "Jugadores", "Joueurs", "Oyuncular"},
        {"Laufzeit", "Uptime", "Tiempo activo", "Durée", "Çalışma süresi"},
        {"Adresse", "Address", "Dirección", "Adresse", "Adres"},
        {"Befehl, z. B. list", "Command, e.g. list", "Comando, p. ej. list", "Commande, p. ex. list", "Komut, örn. list"},
        {"Server starten", "Start server", "Iniciar servidor", "Démarrer le serveur", "Sunucuyu başlat"},
        {"Liste", "List", "Lista", "Liste", "Liste"},
        {"» Bitte zuerst den Server stoppen.", "» Please stop the server first.", "» Detén primero el servidor.", "» Arrête d'abord le serveur.", "» Lütfen önce sunucuyu durdur."},
        {"Tipp: Akku-Optimierung für diese App ausschalten →", "Tip: turn off battery optimization for this app →", "Consejo: desactiva la optimización de batería para esta app →", "Astuce : désactive l'optimisation de la batterie pour cette app →", "İpucu: Bu uygulama için pil optimizasyonunu kapat →"},
        {"» Es kann jeweils nur ein Server gleichzeitig laufen. Stoppe zuerst den aktiven Server.", "» Only one server can run at a time. Stop the active server first.", "» Solo puede ejecutarse un servidor a la vez. Detén primero el servidor activo.", "» Un seul serveur peut tourner à la fois. Arrête d'abord le serveur actif.", "» Aynı anda yalnızca bir sunucu çalışabilir. Önce etkin sunucuyu durdur."},
        {"» Dieser Server läuft nicht – erst den passenden Server starten.", "» This server is not running – start the right server first.", "» Este servidor no está en ejecución: inicia primero el servidor correcto.", "» Ce serveur ne tourne pas – démarre d'abord le bon serveur.", "» Bu sunucu çalışmıyor – önce doğru sunucuyu başlat."},
        {"Anderer Server läuft", "Another server is running", "Otro servidor en ejecución", "Un autre serveur tourne", "Başka sunucu çalışıyor"},
        {"Server stoppen", "Stop server", "Detener servidor", "Arrêter le serveur", "Sunucuyu durdur"},
        {"» Ein anderer Server ist aktiv. Stoppe ihn zuerst, um diesen Server zu starten.", "» Another server is active. Stop it first to start this one.", "» Otro servidor está activo. Detenlo primero para iniciar este.", "» Un autre serveur est actif. Arrête-le d'abord pour démarrer celui-ci.", "» Başka bir sunucu etkin. Bunu başlatmak için önce onu durdur."},
        {"» Bereit. Drücke „Server starten“.", "» Ready. Press “Start server”.", "» Listo. Pulsa «Iniciar servidor».", "» Prêt. Appuie sur « Démarrer le serveur ».", "» Hazır. “Sunucuyu başlat”a bas."},
        {"… neuer Versuch ohne Seccomp", "… retrying without seccomp", "… nuevo intento sin seccomp", "… nouvel essai sans seccomp", "… seccomp olmadan yeniden deneniyor"},
        {"$ Android-Java testen", "$ Testing Android Java", "$ Probando Java de Android", "$ Test de Java Android", "$ Android Java test ediliyor"},
        {"✓ Android-Java bereit", "✓ Android Java ready", "✓ Java de Android listo", "✓ Java Android prêt", "✓ Android Java hazır"},
        {"… vorhandene Android-Java-Laufzeit unbrauchbar: ", "… existing Android Java runtime unusable: ", "… el entorno Java de Android existente no es utilizable: ", "… l'environnement Java Android existant est inutilisable : ", "… mevcut Android Java çalışma zamanı kullanılamıyor: "},
        {"$ Android-kompatible Java-21-Laufzeit laden", "$ Downloading Android-compatible Java 21 runtime", "$ Descargando entorno Java 21 compatible con Android", "$ Téléchargement de l'environnement Java 21 compatible Android", "$ Android uyumlu Java 21 çalışma zamanı indiriliyor"},
        {"Java-Home im Android-Runtime-Archiv nicht gefunden", "Java home not found in the Android runtime archive", "No se encontró Java home en el archivo del entorno Android", "Java home introuvable dans l'archive de l'environnement Android", "Android çalışma zamanı arşivinde Java home bulunamadı"},
        {"… Android-Java fehlgeschlagen: ", "… Android Java failed: ", "… Java de Android falló: ", "… Java Android a échoué : ", "… Android Java başarısız: "},
        {"… versuche Alpine-Java als Rückfall", "… trying Alpine Java as fallback", "… probando Java de Alpine como alternativa", "… essai de Java Alpine en secours", "… yedek olarak Alpine Java deneniyor"},
        {"✓ Linux-Umgebung bereit", "✓ Linux environment ready", "✓ Entorno Linux listo", "✓ Environnement Linux prêt", "✓ Linux ortamı hazır"},
        {"Java-Installation in Linux fehlgeschlagen (Exit ", "Java installation in Linux failed (exit ", "La instalación de Java en Linux falló (salida ", "L'installation de Java dans Linux a échoué (code ", "Linux'ta Java kurulumu başarısız (çıkış "},
        {"Java startet in der Linux-Umgebung nicht", "Java does not start in the Linux environment", "Java no se inicia en el entorno Linux", "Java ne démarre pas dans l'environnement Linux", "Java Linux ortamında başlamıyor"},
        {"✓ Linux + Java bereit", "✓ Linux + Java ready", "✓ Linux + Java listo", "✓ Linux + Java prêt", "✓ Linux + Java hazır"},
        {"$ proot laden (Termux-Paketquelle)", "$ Downloading proot (Termux package source)", "$ Descargando proot (fuente de paquetes Termux)", "$ Téléchargement de proot (source de paquets Termux)", "$ proot indiriliyor (Termux paket kaynağı)"},
        {"proot nicht in der Paketquelle gefunden", "proot not found in package source", "proot no encontrado en la fuente de paquetes", "proot introuvable dans la source de paquets", "proot paket kaynağında bulunamadı"},
        {"proot fehlt im Paket", "proot missing from package", "Falta proot en el paquete", "proot absent du paquet", "proot pakette yok"},
        {"✓ proot bereit", "✓ proot ready", "✓ proot listo", "✓ proot prêt", "✓ proot hazır"},
        {"$ Alpine Linux laden", "$ Downloading Alpine Linux", "$ Descargando Alpine Linux", "$ Téléchargement d'Alpine Linux", "$ Alpine Linux indiriliyor"},
        {"Alpine-Abbild nicht gefunden", "Alpine image not found", "Imagen de Alpine no encontrada", "Image Alpine introuvable", "Alpine imajı bulunamadı"},
        {"✓ Alpine entpackt", "✓ Alpine extracted", "✓ Alpine extraído", "✓ Alpine extrait", "✓ Alpine çıkarıldı"},
        {"data.tar fehlt in ", "data.tar missing in ", "Falta data.tar en ", "data.tar absent dans ", "data.tar eksik: "},
        {"Archivformat nicht unterstützt (nur gzip/xz)", "Archive format not supported (gzip/xz only)", "Formato de archivo no compatible (solo gzip/xz)", "Format d'archive non pris en charge (gzip/xz uniquement)", "Arşiv biçimi desteklenmiyor (yalnızca gzip/xz)"},
        {" bei ", " at ", " en ", " sur ", " – "},
        {"Neustart des Downloads", "Restarting download", "Reiniciando la descarga", "Redémarrage du téléchargement", "İndirme yeniden başlatılıyor"},
        {"Download unvollständig", "Download incomplete", "Descarga incompleta", "Téléchargement incomplet", "İndirme eksik"},
        {"… Verbindung unterbrochen, Versuch ", "… connection lost, attempt ", "… conexión interrumpida, intento ", "… connexion interrompue, essai ", "… bağlantı kesildi, deneme "},
        {"Speichern fehlgeschlagen: ", "Saving failed: ", "Error al guardar: ", "Échec de l'enregistrement : ", "Kaydetme başarısız: "},
        {" (Ziel: ", " (target: ", " (destino: ", " (cible : ", " (hedef: "},
        {"Kein Paper-Build fuer ", "No Paper build for ", "Sin build de Paper para ", "Aucun build Paper pour ", "Paper derlemesi yok: "},
        {"Version nicht gefunden: ", "Version not found: ", "Versión no encontrada: ", "Version introuvable : ", "Sürüm bulunamadı: "},
        {"Keine Java-Laufzeit gefunden", "No Java runtime found", "No se encontró un entorno Java", "Aucun environnement Java trouvé", "Java çalışma zamanı bulunamadı"},
        {"Unbekanntes Format (erwartet .tar.xz oder .tar.gz)", "Unknown format (expected .tar.xz or .tar.gz)", "Formato desconocido (se esperaba .tar.xz o .tar.gz)", "Format inconnu (.tar.xz ou .tar.gz attendu)", "Bilinmeyen biçim (.tar.xz veya .tar.gz bekleniyordu)"},
        {" Dateien entpackt", " files extracted", " archivos extraídos", " fichiers extraits", " dosya çıkarıldı"},
        {"java -version fehlgeschlagen:\n", "java -version failed:\n", "java -version falló:\n", "java -version a échoué :\n", "java -version başarısız:\n"},
        {"Plugin-Installer", "Plugin installer", "Instalador de plugins", "Installateur de plugins", "Eklenti yükleyici"},
        {"JAR-Downloadlink", "JAR download link", "Enlace de descarga del JAR", "Lien de téléchargement du JAR", "JAR indirme bağlantısı"},
        {"Füge einen direkten HTTPS-Link zu einer .jar-Datei ein. Nutze nur Plugins aus Quellen, denen du vertraust.", "Paste a direct HTTPS link to a .jar file. Only use plugins from sources you trust.", "Pega un enlace HTTPS directo a un archivo .jar. Usa solo plugins de fuentes de confianza.", "Colle un lien HTTPS direct vers un fichier .jar. N'utilise que des plugins de sources fiables.", "Doğrudan bir .jar dosyasına HTTPS bağlantısı yapıştır. Yalnızca güvendiğin kaynaklardan eklenti kullan."},
        {"Plugin installieren", "Install plugin", "Instalar plugin", "Installer le plugin", "Eklentiyi yükle"},
        {"Installierte JAR-Dateien", "Installed JAR files", "Archivos JAR instalados", "Fichiers JAR installés", "Yüklü JAR dosyaları"},
        {"Dieser Server ist Vanilla. Plugins benötigen Paper; ändere die Server-Software im Setup.", "This server is Vanilla. Plugins require Paper; change the server software in the setup.", "Este servidor es Vanilla. Los plugins requieren Paper; cambia el software del servidor en la configuración inicial.", "Ce serveur est Vanilla. Les plugins nécessitent Paper ; change le logiciel du serveur dans l'assistant.", "Bu sunucu Vanilla. Eklentiler Paper gerektirir; kurulum sihirbazında sunucu yazılımını değiştir."},
        {"✗ Plugins funktionieren nur mit Paper, nicht mit Vanilla.", "✗ Plugins only work with Paper, not with Vanilla.", "✗ Los plugins solo funcionan con Paper, no con Vanilla.", "✗ Les plugins ne fonctionnent qu'avec Paper, pas avec Vanilla.", "✗ Eklentiler yalnızca Paper ile çalışır, Vanilla ile çalışmaz."},
        {"Bitte stoppe diesen Server vor der Installation.", "Please stop this server before installing.", "Detén este servidor antes de instalar.", "Arrête ce serveur avant l'installation.", "Kurulumdan önce lütfen bu sunucuyu durdur."},
        {"Nur HTTPS-Links sind erlaubt.", "Only HTTPS links are allowed.", "Solo se permiten enlaces HTTPS.", "Seuls les liens HTTPS sont autorisés.", "Yalnızca HTTPS bağlantılarına izin verilir."},
        {"Der Link muss direkt auf einen Dateinamen mit .jar enden.", "The link must end directly in a file name with .jar.", "El enlace debe terminar directamente en un nombre de archivo con .jar.", "Le lien doit se terminer directement par un nom de fichier en .jar.", "Bağlantı doğrudan .jar uzantılı bir dosya adıyla bitmeli."},
        {"✗ Ungültiger Link: ", "✗ Invalid link: ", "✗ Enlace no válido: ", "✗ Lien invalide : ", "✗ Geçersiz bağlantı: "},
        {"Download wird gestartet: ", "Starting download: ", "Iniciando descarga: ", "Démarrage du téléchargement : ", "İndirme başlatılıyor: "},
        {"Plugin-Ordner konnte nicht angelegt werden.", "Could not create plugins folder.", "No se pudo crear la carpeta de plugins.", "Impossible de créer le dossier des plugins.", "Eklenti klasörü oluşturulamadı."},
        {"✓ Installiert: ", "✓ Installed: ", "✓ Instalado: ", "✓ Installé : ", "✓ Yüklendi: "},
        {". Der Server lädt das Plugin beim nächsten Start.", ". The server loads the plugin on the next start.", ". El servidor cargará el plugin en el próximo inicio.", ". Le serveur chargera le plugin au prochain démarrage.", ". Sunucu eklentiyi bir sonraki başlatmada yükler."},
        {"✗ Installation fehlgeschlagen: ", "✗ Installation failed: ", "✗ La instalación falló: ", "✗ Échec de l'installation : ", "✗ Kurulum başarısız: "},
        {"Noch keine Plugins installiert.", "No plugins installed yet.", "Aún no hay plugins instalados.", "Aucun plugin installé pour le moment.", "Henüz eklenti yüklenmedi."},
        {"Aus dem Handy", "From your phone", "Desde tu móvil", "Depuis ton téléphone", "Telefondan"},
        {"Dateien aus dem Handy importieren", "Import files from phone", "Importar archivos desde el móvil", "Importer des fichiers depuis le téléphone", "Telefondan dosya içe aktar"},
        {"Wähle eine oder mehrere .jar-Dateien aus dem Speicher deines Handys aus. Du kannst mehrere Dateien auf einmal markieren.", "Choose one or more .jar files from your phone's storage. You can select several files at once.", "Elige uno o varios archivos .jar del almacenamiento de tu móvil. Puedes seleccionar varios a la vez.", "Choisis un ou plusieurs fichiers .jar dans le stockage de ton téléphone. Tu peux en sélectionner plusieurs à la fois.", "Telefonunun depolamasından bir veya birden fazla .jar dosyası seç. Aynı anda birden fazla dosya işaretleyebilirsin."},
        {"✗ Dateiauswahl konnte nicht geöffnet werden.", "✗ Could not open the file picker.", "✗ No se pudo abrir el selector de archivos.", "✗ Impossible d'ouvrir le sélecteur de fichiers.", "✗ Dosya seçici açılamadı."},
        {"Dateien werden importiert …", "Importing files …", "Importando archivos …", "Importation des fichiers …", "Dosyalar içe aktarılıyor …"},
        {"✓ %d Plugin(s) importiert. Der Server lädt sie beim nächsten Start.", "✓ %d plugin(s) imported. The server loads them on the next start.", "✓ %d plugin(s) importado(s). El servidor los cargará en el próximo inicio.", "✓ %d plugin(s) importé(s). Le serveur les chargera au prochain démarrage.", "✓ %d eklenti içe aktarıldı. Sunucu bunları bir sonraki başlatmada yükler."},
        {"%d Datei(en) übersprungen (keine gültige .jar-Datei) oder fehlgeschlagen.", "%d file(s) skipped (not a valid .jar file) or failed.", "%d archivo(s) omitido(s) (no es un .jar válido) o con error.", "%d fichier(s) ignoré(s) (pas un .jar valide) ou en échec.", "%d dosya atlandı (geçerli .jar değil) veya başarısız oldu."},
        {"✗ Keine Datei importiert. Es werden nur .jar-Dateien unterstützt.", "✗ No file imported. Only .jar files are supported.", "✗ No se importó ningún archivo. Solo se admiten archivos .jar.", "✗ Aucun fichier importé. Seuls les fichiers .jar sont pris en charge.", "✗ Hiçbir dosya içe aktarılmadı. Yalnızca .jar dosyaları desteklenir."},
        {"Deine Server", "Your servers", "Tus servidores", "Tes serveurs", "Sunucuların"},
        {"Verwalte deine Minecraft-Welten an einem Ort.", "Manage your Minecraft worlds in one place.", "Gestiona tus mundos de Minecraft en un solo lugar.", "Gère tes mondes Minecraft au même endroit.", "Minecraft dünyalarını tek yerden yönet."},
        {"+  Server erstellen", "+  Create server", "+  Crear servidor", "+  Créer un serveur", "+  Sunucu oluştur"},
        {"Noch keine Server", "No servers yet", "Aún no hay servidores", "Aucun serveur pour le moment", "Henüz sunucu yok"},
        {"Erstelle deinen ersten Server. Du kannst mehrere Profile mit eigenen Einstellungen, Plugins und Config-Dateien verwalten.", "Create your first server. You can manage multiple profiles with their own settings, plugins and config files.", "Crea tu primer servidor. Puedes gestionar varios perfiles con sus propios ajustes, plugins y archivos de configuración.", "Crée ton premier serveur. Tu peux gérer plusieurs profils avec leurs propres réglages, plugins et fichiers de configuration.", "İlk sunucunu oluştur. Kendi ayarları, eklentileri ve yapılandırma dosyalarıyla birden fazla profil yönetebilirsin."},
        {"Noch nicht eingerichtet", "Not set up yet", "Aún sin configurar", "Pas encore configuré", "Henüz kurulmadı"},
        {"Öffnen", "Open", "Abrir", "Ouvrir", "Aç"},
        {"Einrichten", "Set up", "Configurar", "Configurer", "Kur"},
        {"Name", "Name", "Nombre", "Nom", "Ad"},
        {"Configs", "Configs", "Configs", "Configs", "Ayarlar"},
        {"z. B. Survival-Welt", "e.g. Survival world", "p. ej. Mundo survival", "p. ex. Monde survie", "örn. Hayatta kalma dünyası"},
        {"Neuer Server", "New server", "Nuevo servidor", "Nouveau serveur", "Yeni sunucu"},
        {"Gib deinem Server einen Namen.", "Give your server a name.", "Ponle un nombre a tu servidor.", "Donne un nom à ton serveur.", "Sunucuna bir ad ver."},
        {"Weiter", "Next", "Siguiente", "Suivant", "İleri"},
        {"Servername", "Server name", "Nombre del servidor", "Nom du serveur", "Sunucu adı"},
        {"Server benennen", "Rename server", "Renombrar servidor", "Renommer le serveur", "Sunucuyu yeniden adlandır"},
        {"Speichern", "Save", "Guardar", "Enregistrer", "Kaydet"},
        {"Server startet …", "Server starting …", "Servidor iniciando …", "Démarrage du serveur …", "Sunucu başlıyor …"},
        {"» stoppe Server …", "» stopping server …", "» deteniendo servidor …", "» arrêt du serveur …", "» sunucu durduruluyor …"},
        {"✗ Linux-Umgebung oder server.jar fehlt – bitte Setup erneut ausführen.", "✗ Linux environment or server.jar missing – please run the setup again.", "✗ Falta el entorno Linux o server.jar: ejecuta de nuevo la configuración.", "✗ Environnement Linux ou server.jar manquant – relance l'assistant.", "✗ Linux ortamı veya server.jar eksik – lütfen kurulumu yeniden çalıştır."},
        {"⚠ libnotag.so fehlt – Heap-Tagging bleibt aktiv", "⚠ libnotag.so missing – heap tagging stays active", "⚠ Falta libnotag.so: el heap tagging sigue activo", "⚠ libnotag.so manquant – le heap tagging reste actif", "⚠ libnotag.so eksik – heap tagging etkin kalır"},
        {"Online · %d Spieler", "Online · %d players", "En línea · %d jugadores", "En ligne · %d joueurs", "Çevrimiçi · %d oyuncu"},
        {"» Server beendet (Exit %d)", "» Server stopped (exit %d)", "» Servidor detenido (salida %d)", "» Serveur arrêté (code %d)", "» Sunucu durdu (çıkış %d)"},
        {"Stoppen", "Stop", "Detener", "Arrêter", "Durdur"},
        {"Spiel", "Game", "Juego", "Jeu", "Oyun"},
        {"Leistung", "Performance", "Rendimiento", "Performances", "Performans"},
        {"Installation", "Installation", "Instalación", "Installation", "Kurulum"},
        {"Version", "Version", "Versión", "Version", "Sürüm"},
        {"Zurück", "Back", "Atrás", "Retour", "Geri"},
        {"Fertig", "Done", "Listo", "Terminé", "Bitti"},
        {"Dieser Assistent richtet einen Minecraft-Server ein, der direkt auf deinem Handy läuft. ", "This assistant sets up a Minecraft server that runs directly on your phone. ", "Este asistente configura un servidor de Minecraft que se ejecuta directamente en tu móvil. ", "Cet assistant configure un serveur Minecraft qui tourne directement sur ton téléphone. ", "Bu sihirbaz doğrudan telefonunda çalışan bir Minecraft sunucusu kurar. "},
        {"Du wählst Software, Version und die wichtigsten Einstellungen – den Rest übernimmt die App.", "You choose the software, version and the most important settings – the app takes care of the rest.", "Tú eliges el software, la versión y los ajustes principales; la app se encarga del resto.", "Tu choisis le logiciel, la version et les réglages principaux – l'app s'occupe du reste.", "Yazılımı, sürümü ve en önemli ayarları sen seçersin – gerisini uygulama halleder."},
        {"Server-Software", "Server software", "Software del servidor", "Logiciel du serveur", "Sunucu yazılımı"},
        {"Der offizielle Mojang-Server. Reines Minecraft ohne Plugins.", "The official Mojang server. Pure Minecraft without plugins.", "El servidor oficial de Mojang. Minecraft puro, sin plugins.", "Le serveur officiel de Mojang. Minecraft pur, sans plugins.", "Resmi Mojang sunucusu. Eklentisiz saf Minecraft."},
        {"Optimierter Server mit besserer Performance – die bessere Wahl fürs Handy. Plugins möglich.", "Optimized server with better performance – the better choice for a phone. Plugins supported.", "Servidor optimizado con mejor rendimiento: la mejor opción para el móvil. Admite plugins.", "Serveur optimisé aux meilleures performances – le meilleur choix pour un téléphone. Plugins possibles.", "Daha iyi performanslı optimize sunucu – telefon için daha iyi seçim. Eklentiler desteklenir."},
        {"Erneut versuchen", "Try again", "Reintentar", "Réessayer", "Tekrar dene"},
        {"$ Versionen werden geladen …", "$ Loading versions …", "$ Cargando versiones …", "$ Chargement des versions …", "$ Sürümler yükleniyor …"},
        {"Minecraft %s braucht Java %s oder neuer.", "Minecraft %s needs Java %s or newer.", "Minecraft %s necesita Java %s o superior.", "Minecraft %s nécessite Java %s ou plus récent.", "Minecraft %s için Java %s veya daha yenisi gerekir."},
        {"Server-Name (MOTD)", "Server name (MOTD)", "Nombre del servidor (MOTD)", "Nom du serveur (MOTD)", "Sunucu adı (MOTD)"},
        {"Max. Spieler", "Max. players", "Máx. jugadores", "Joueurs max.", "Maks. oyuncu"},
        {"Schwierigkeit", "Difficulty", "Dificultad", "Difficulté", "Zorluk"},
        {"Friedlich", "Peaceful", "Pacífico", "Pacifique", "Barışçıl"},
        {"Leicht", "Easy", "Fácil", "Facile", "Kolay"},
        {"Schwer", "Hard", "Difícil", "Difficile", "Zor"},
        {"Spielmodus", "Game mode", "Modo de juego", "Mode de jeu", "Oyun modu"},
        {"Survival", "Survival", "Supervivencia", "Survie", "Hayatta kalma"},
        {"Kreativ", "Creative", "Creativo", "Créatif", "Yaratıcı"},
        {"Abenteuer", "Adventure", "Aventura", "Aventure", "Macera"},
        {"An", "On", "Sí", "Activé", "Açık"},
        {"Aus", "Off", "No", "Désactivé", "Kapalı"},
        {"Online-Modus (Account-Prüfung)", "Online mode (account verification)", "Modo online (verificación de cuenta)", "Mode en ligne (vérification des comptes)", "Çevrimiçi mod (hesap doğrulama)"},
        {"Aus = auch nicht-offizielle Clients können beitreten, aber jeder kann sich mit fremdem Namen anmelden.", "Off = unofficial clients can join too, but anyone can log in with someone else's name.", "No = también pueden unirse clientes no oficiales, pero cualquiera puede entrar con un nombre ajeno.", "Désactivé = les clients non officiels peuvent aussi rejoindre, mais n'importe qui peut se connecter avec le nom d'un autre.", "Kapalı = resmi olmayan istemciler de katılabilir, ancak herkes başkasının adıyla giriş yapabilir."},
        {"Arbeitsspeicher für den Server", "Memory for the server", "Memoria para el servidor", "Mémoire pour le serveur", "Sunucu için bellek"},
        {"Dein Handy hat ca. %d MB RAM. Es werden höchstens 60 %% angeboten, damit Android genug übrig bleibt.", "Your phone has about %d MB of RAM. At most 60 %% is offered so Android has enough left.", "Tu móvil tiene unos %d MB de RAM. Se ofrece como máximo el 60 %% para que Android tenga suficiente.", "Ton téléphone a environ %d Mo de RAM. 60 %% au maximum sont proposés pour qu'Android en garde assez.", "Telefonunda yaklaşık %d MB RAM var. Android'e yeterince kalması için en fazla %%60 sunulur."},
        {"Sichtweite (Chunks)", "View distance (chunks)", "Distancia de visión (chunks)", "Distance de vue (chunks)", "Görüş mesafesi (chunk)"},
        {"Kleinere Sichtweite = weniger Last für das Handy.", "Smaller view distance = less load on the phone.", "Menor distancia de visión = menos carga para el móvil.", "Distance de vue plus faible = moins de charge pour le téléphone.", "Daha küçük görüş mesafesi = telefona daha az yük."},
        {"Eine Linux-Umgebung (Alpine mit Java) und die Server-Datei werden automatisch geladen und eingerichtet. ", "A Linux environment (Alpine with Java) and the server file are downloaded and set up automatically. ", "Un entorno Linux (Alpine con Java) y el archivo del servidor se descargan y configuran automáticamente. ", "Un environnement Linux (Alpine avec Java) et le fichier du serveur sont téléchargés et configurés automatiquement. ", "Bir Linux ortamı (Java'lı Alpine) ve sunucu dosyası otomatik olarak indirilip kurulur. "},
        {"Mit der Installation akzeptierst du die Minecraft-EULA (aka.ms/MinecraftEULA).", "By installing you accept the Minecraft EULA (aka.ms/MinecraftEULA).", "Al instalar aceptas el EULA de Minecraft (aka.ms/MinecraftEULA).", "En installant, tu acceptes le CLUF de Minecraft (aka.ms/MinecraftEULA).", "Kurulumla Minecraft EULA'yı (aka.ms/MinecraftEULA) kabul etmiş olursun."},
        {"$ bereit", "$ ready", "$ listo", "$ prêt", "$ hazır"},
        {"✓ Bereits installiert. Tippe auf „Fertig“.", "✓ Already installed. Tap “Done”.", "✓ Ya instalado. Pulsa «Listo».", "✓ Déjà installé. Appuie sur « Terminé ».", "✓ Zaten yüklü. “Bitti”ye dokun."},
        {"$ Server-Datei laden (", "$ Downloading server file (", "$ Descargando archivo del servidor (", "$ Téléchargement du fichier serveur (", "$ Sunucu dosyası indiriliyor ("},
        {"✓ Alles bereit. Tippe auf „Fertig“.", "✓ All ready. Tap “Done”.", "✓ Todo listo. Pulsa «Listo».", "✓ Tout est prêt. Appuie sur « Terminé ».", "✓ Her şey hazır. “Bitti”ye dokun."},
        {"Einstellungen", "Settings", "Ajustes", "Paramètres", "Ayarlar"},
        {"Sprache", "Language", "Idioma", "Langue", "Dil"},
        {"Systemstandard", "System default", "Predeterminado del sistema", "Par défaut du système", "Sistem varsayılanı"},
        {"Design", "Theme", "Tema", "Thème", "Tema"},
        {"System", "System", "Sistema", "Système", "Sistem"},
        {"Hell", "Light", "Claro", "Clair", "Açık"},
        {"Dunkel", "Dark", "Oscuro", "Sombre", "Koyu"}
    };
    private static final Map<String, String[]> MAP = new HashMap<>();
    private static volatile int idx = 0;

    static {
        for (String[] r : T) MAP.put(r[0], r);
    }

    private L() { }

    /** Liest die gewählte Sprache; bei „System“ die Gerätesprache (Fallback Englisch). */
    static void init(Context c) {
        String l = AppSettings.lang(c);
        if ("system".equals(l)) l = Locale.getDefault().getLanguage();
        int i = 1;
        for (int k = 0; k < CODES.length; k++) if (CODES[k].equals(l)) i = k;
        idx = i;
    }

    static String t(String de) {
        if (idx == 0 || de == null) return de;
        String[] r = MAP.get(de);
        return r == null || r[idx].isEmpty() ? de : r[idx];
    }

    static String f(String de, Object... args) {
        return String.format(Locale.ROOT, t(de), args);
    }
}
