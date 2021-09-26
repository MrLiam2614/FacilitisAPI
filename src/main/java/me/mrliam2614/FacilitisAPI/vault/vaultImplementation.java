package me.mrliam2614.FacilitisAPI.vault;

import me.mrliam2614.FacilitisAPI.FacilitisAPI;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.logging.Logger;

public class vaultImplementation {
    public FacilitisAPI plugin;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    public vaultImplementation(FacilitisAPI plugin) {
        this.plugin = plugin;
    }


    public void Start() {
        if (!setupEconomy()) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        setupPermissions();
        setupChat();
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            plugin.console.sendMessage(plugin, "Disabled due to no Vault dependency found!", "critical");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            plugin.console.sendMessage(plugin, "Disabled due to no Economy Plugin - dependency found!", "critical");
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    private void setupChat() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        ServicesManager sm = Bukkit.getServicesManager();
        Chat chatHook = new chatImplementation(plugin, perms);
        sm.register(Chat.class, chatHook, plugin, ServicePriority.Highest);

        chat = sm.getRegistration(Chat.class).getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
    }

    public Economy getEconomy() {
        return econ;
    }

    public Permission getPermissions() {
        return perms;
    }

    public Chat getChat() {
        return chat;
    }
}