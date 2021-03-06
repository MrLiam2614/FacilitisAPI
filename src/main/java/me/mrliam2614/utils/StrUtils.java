package me.mrliam2614.utils;

import me.mrliam2614.FacilitisAPI;
import org.bukkit.ChatColor;

public class StrUtils {
    private final FacilitisAPI plugin;

    public StrUtils(FacilitisAPI plugin) {
        this.plugin = plugin;
    }

    public String intToStr(int i) {
        return Integer.toString(i);
    }

    public String floatToStr(float i) {
        return floatToStr(i);
    }

    public String doubleToStr(double i) {
        return doubleToStr(i);
    }

    public String colored(String message) {
        /**
         @param message to translate with colors
         **/
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void center(String str) {
        int maxWidth = 60;
        int spaces = (maxWidth - ChatColor.stripColor(str).length()) / 2;
        plugin.console.sendMessage("|" + repeat(" ", spaces - 1) + str);
    }

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++)
            sb.append(str);
        return sb.toString();
    }
}
