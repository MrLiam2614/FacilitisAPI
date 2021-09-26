package me.mrliam2614.FacilitisAPI;

import me.mrliam2614.FacilitisAPI.consoleManager.ConsoleMessage;
import me.mrliam2614.FacilitisAPI.utils.StrUtils;
import org.bukkit.plugin.java.JavaPlugin;


public class FacilitisAPI extends JavaPlugin {
    public StrUtils strUtils;
    public ConsoleMessage console;

    @Override
    public void onEnable() {
        strUtils = new StrUtils(this);
        console = new ConsoleMessage(this);
    }
}
