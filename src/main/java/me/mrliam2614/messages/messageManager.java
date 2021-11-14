package me.mrliam2614.messages;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.entity.Player;

public class messageManager {
    private final FacilitisAPI plugin;

    public messageManager(FacilitisAPI plugin){
        this.plugin = plugin;
    }

    public void sendMessage(Player player, String message){
        player.sendMessage(plugin.strUtils.colored(message));
    }
}
