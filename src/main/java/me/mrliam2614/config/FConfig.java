package me.mrliam2614.config;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FConfig {
    private final File file;
    private final FileConfiguration fileConfig;
    private final Plugin plugin;

    //Constructors
    public FConfig(Plugin plugin, String fileName, boolean defaults) {
        this.plugin = plugin;
        fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        file = new File(plugin.getDataFolder() + File.separator + fileName);
        fileConfig = new YamlConfiguration();

        if (defaults) {
            plugin.saveResource(fileName, false);
        }
        try {
            fileConfig.load(file);
            saveConfig();
        } catch (IOException | InvalidConfigurationException e) {
            FacilitisAPI.getInstance().console.sendMessage(plugin, e.toString(), "error");
        }
    }

    public FConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        file = new File(plugin.getDataFolder() + File.separator + fileName);
        fileConfig = new YamlConfiguration();

        plugin.saveResource(fileName, false);

        try {
            fileConfig.load(file);
            saveConfig();
        } catch (IOException | InvalidConfigurationException e) {
            FacilitisAPI.getInstance().console.sendMessage(plugin, e.toString(), "error");
        }
    }

    public FConfig(Plugin plugin, String fileName, String copyFile) {
        this.plugin = plugin;
        copyFile = copyFile.endsWith(".yml") ? copyFile : copyFile + ".yml";
        fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";

        file = new File(plugin.getDataFolder() + File.separator + copyFile);
        fileConfig = new YamlConfiguration();

        plugin.saveResource(copyFile, false);

        try {
            file.renameTo(new File(plugin.getDataFolder() + File.separator + fileName));
            fileConfig.load(file);
            saveConfig();
        } catch (IOException | InvalidConfigurationException e) {
            FacilitisAPI.getInstance().console.sendMessage(plugin, e.toString(), "error");
        }
    }

    //Methods
    public void saveConfig() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            FacilitisAPI.getInstance().console.sendMessage(plugin, e.toString(), "error");
        }
    }

    public FileConfiguration getConfig() {
        return fileConfig;
    }
}