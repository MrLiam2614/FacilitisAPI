package me.mrliam2614.FacilitisAPI.config;

import me.mrliam2614.FacilitisAPI.FacilitisAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.logging.Level;

public class config {
    private final FacilitisAPI plugin;

    public config(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    public void generateConfig(Plugin getPlugin, String fileName) {
        /**
         @param getPlugin: Your plugin class
         @param fileName: Your file name
         **/

        fileName = configName(fileName);
        File configFile = new File(getPlugin.getDataFolder(), fileName);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            getPlugin.saveResource(fileName, false);
        }
        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.console.sendMessage(getPlugin, "There was a problem loading " + fileName + "!\n" + e, "critical");
        }

        Reader defaultConfigStream = null;
        try {
            defaultConfigStream = new InputStreamReader(getPlugin.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            plugin.console.sendMessage(getPlugin, "There was a problem loading " + fileName + "!\n" + e, "critical");
        }
        if (defaultConfigStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
            customConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(Plugin getPlugin, String fileName) {
        fileName = configName(fileName);
        /**
         @param getPlugin: Your plugin class
         @param fileName: Your plugin fileName
         **/
        FileConfiguration customConfig = new YamlConfiguration();
        File configFile = new File(getPlugin.getDataFolder(), fileName);
        try {
            customConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.console.sendMessage(getPlugin, "There was a problem getting " + fileName + "!\n" + e, "critical");
        }
        return customConfig;
    }

    public void saveConfig(Plugin getPlugin, String fileName) {
        fileName = configName(fileName);
        /**
         @param getPlugin: Your plugin class
         @param fileName: Your plugin fileName
         **/
        try {
            this.getConfig(getPlugin, fileName).save(fileName);
        } catch (IOException var2) {
            plugin.console.sendMessage(getPlugin, "Could not save config " + fileName + var2, "error");
        }

    }

    private String configName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (!extension.equalsIgnoreCase(".yml")) {
            fileName += ".yml";
        }
        return fileName;
    }
}
