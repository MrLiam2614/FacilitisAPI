package me.mrliam2614.startup;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.plugin.Plugin;

import java.net.MalformedURLException;

public class Messages {
    public Updater updater;
    private FacilitisAPI plugin;

    public Messages(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    public Plugin pl;

    public void EnableMessage(Plugin getPlugin, boolean AutoUPD, String PlID) {
        String PLPName = getPlugin.getName();
        String PLName = getPlugin.getDescription().getName();
        String Ver = getPlugin.getDescription().getVersion();

        Raw();
        plugin.strUtils.center("&bEnabling &6" + PLName + " &bVersion &6" + Ver);
        plugin.strUtils.center("");
        plugin.strUtils.center("&bLoading &6Directory and Files");
        plugin.strUtils.center("");
        plugin.strUtils.center("&bChecking for &6Update");
        try {
            updater.getUpdate(AutoUPD, PlID, PLPName, pl, Ver);
        } catch (MalformedURLException e) {
            plugin.strUtils.center("&cFailed Checking For &6Update!");
            plugin.strUtils.center("&cError: &6");
            e.printStackTrace();
        }
        Raw();
    }

    public void EnableMessage(Plugin getPlugin) {
        String PLName = getPlugin.getDescription().getName();
        String Ver = getPlugin.getDescription().getVersion();

        Raw();
        plugin.strUtils.center("&bEnabling &6" + PLName + " &bVersion &6" + Ver);
        plugin.strUtils.center("");
        plugin.strUtils.center("&bLoading &6Directory and Files");
        plugin.strUtils.center("");
        plugin.strUtils.center("&6Update &bcheck &cNot initialized");
        Raw();
    }

    public void Raw() {
        plugin.strUtils.center("&a-----------------------------------------------------------");
    }

    public void DisableMessage(Plugin getPlugin) {
        String PLName = getPlugin.getDescription().getName();
        String Ver = getPlugin.getDescription().getVersion();
        Raw();
        plugin.strUtils.center("&cDisabling &6" + PLName + " &cVersion &6" + Ver);
        Raw();
    }

    public void SendMessage(String fromPlugin, String textToWrite) {
        Raw();
        plugin.strUtils.center("&c" + fromPlugin);
        plugin.strUtils.center(textToWrite);
        Raw();
    }
}
