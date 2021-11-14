package me.mrliam2614.startup;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Updater {

    public FacilitisAPI plugin;

    public Updater(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    public void getUpdate(boolean Auto, String ID, String PlName, Plugin pl, String Ver) throws MalformedURLException {
        String web = "https://api.spiget.org/v2/resources/" + ID + "/download";
        URL website = new URL(web);
        String VERSION_URL = "https://api.spigotmc.org/legacy/update.php?resource=" + ID;

        try {
            String UrlVersion = new Scanner(new URL(VERSION_URL).openStream(),
                    StandardCharsets.UTF_8.toString()).nextLine();

            String CurrVersion = Ver;

            if (CurrVersion.equalsIgnoreCase("updating")) {
                plugin.strUtils.center("&aYour version is in updating mode!");
            } else if (!CurrVersion.equalsIgnoreCase(UrlVersion)) {
                plugin.strUtils.center("&6Update &cFound");
                if (Auto == true) {
                    plugin.strUtils.center("&bDownloading &6Update");
                    File Update = new File(plugin.getDataFolder() + "/../.Updates", PlName + ".jar");
                    File directory = new File(plugin.getDataFolder() + "/../.Updates");

                    File dir = directory.getAbsoluteFile();
                    if (!dir.exists())
                        dir.mkdirs();


                    //Download Update
                    try {
						/*JsonParser jp = new JsonParser();
					    JsonElement root = jp.parse(new InputStreamReader((InputStream) website.getContent()));
					    JsonObject rootobj = root.getAsJsonObject();
					    String zipcode = rootobj.getAsJsonObject("file").get("url").getAsString();

					    String downWeb = "https://www.spigotmc.org/"+zipcode;
					    URL downUrl = new URL(downWeb);
					    */
                        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                        FileOutputStream os = new FileOutputStream(Update);
                        os.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        os.getChannel().force(true);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    plugin.strUtils.center("&cYou have disabled &6Auto Download Update &coption");
                    plugin.strUtils.center("&cSo i can't download the update");
                }

            } else {
                plugin.strUtils.center("&aCurrent version is updated!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
