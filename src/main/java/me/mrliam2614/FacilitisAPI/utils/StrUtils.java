package me.mrliam2614.FacilitisAPI.utils;

import me.mrliam2614.FacilitisAPI.FacilitisAPI;
import org.bukkit.ChatColor;

public class StrUtils {
    private final FacilitisAPI plugin;

    public StrUtils(FacilitisAPI plugin){
        this.plugin = plugin;
    }

    public String intToStr(int i){
        return Integer.toString(i);
    }
    public String floatToStr(float i){
        return floatToStr(i);
    }
    public String doubleToStr(double i){
        return doubleToStr(i);
    }

    public String colored(String message){
        /**
         @param message to translate with colors
         **/
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
