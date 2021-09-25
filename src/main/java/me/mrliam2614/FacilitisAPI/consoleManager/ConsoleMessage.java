package me.mrliam2614.FacilitisAPI.consoleManager;

import me.mrliam2614.FacilitisAPI.FacilitisAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ConsoleMessage {
    private final FacilitisAPI plugin;

    public ConsoleMessage(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    public void sendMessage(Plugin sender, String message, String type) {
        /**
         @param plugin Your plugin
         @param message message to send
         @param type the type of message (info, warnign, alert, error, critical)
         **/
        String startLine;
        switch (type.toLowerCase()) {
            case "info":
                startLine = "&7[&9Info&7]";
                break;
            case "warning":
                startLine = "&7[&6Warning&7]";
                break;
            case "alert":
                startLine = "&7[&cAlert&7]";
                break;
            case "error":
                startLine = "&7[&cError&7]";
                break;
            case "critical":
                startLine = "&7[&4Critical Error&7]";
                break;
            default:
                startLine = "&7[&9Info&7]";
        }
        String pluginName = "&7[&6"+sender.getName()+"&7]";

        String complete = startLine + pluginName + message;
        Bukkit.getConsoleSender().sendMessage(plugin.strUtils.colored(complete));
    }


    public void sendMessage(String message){
        /**
        @param message message to send
         **/
        sendMessage(plugin, message, "info");
    }

}
