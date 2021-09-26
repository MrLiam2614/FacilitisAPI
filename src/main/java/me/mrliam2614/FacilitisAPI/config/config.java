package me.mrliam2614.FacilitisAPI.config;

import me.mrliam2614.FacilitisAPI.FacilitisAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

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
        File configFile = new File(getPlugin.getDataFolder(), fileName);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            getPlugin.saveResource("arenas.yml", false);
        }
        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.console.sendMessage(getPlugin, "There was a problem loading " + fileName + "!\n" + e, "critical");
        }
        saveConfig(getPlugin, fileName);
    }

    public void saveConfig(Plugin getPlugin, String fileName) {
        /**
         @param getPlugin: Your plugin class
         @param fileName: Your file name
         **/
        FileConfiguration customConfig = new YamlConfiguration();
        File configFile = new File(getPlugin.getDataFolder(), fileName);
        try {
            customConfig.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(Plugin getPlugin, String fileName) {
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

    private String configName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (!extension.equalsIgnoreCase("yml")) {
            fileName += ".yml";
        }
        return fileName;
    }
}
