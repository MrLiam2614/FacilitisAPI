package me.mrliam2614.FacilitisAPI;

import me.mrliam2614.FacilitisAPI.config.config;
import me.mrliam2614.FacilitisAPI.consoleManager.ConsoleMessage;
import me.mrliam2614.FacilitisAPI.startup.Messages;
import me.mrliam2614.FacilitisAPI.utils.MySql;
import me.mrliam2614.FacilitisAPI.utils.StrUtils;
import me.mrliam2614.FacilitisAPI.vault.vaultImplementation;
import org.bukkit.plugin.java.JavaPlugin;


public class FacilitisAPI extends JavaPlugin {
    public StrUtils strUtils;
    public ConsoleMessage console;
    public vaultImplementation vault;
    public config config;
    public MySql MySql;
    public Messages messages;

    @Override
    public void onEnable() {
        vault = new vaultImplementation(this);
        strUtils = new StrUtils(this);
        console = new ConsoleMessage(this);
        config = new config(this);
        MySql = new MySql(this);
        messages = new Messages(this);

        messages.EnableMessage(this);
    }

    public void onDisable(){
        messages.DisableMessage(this);
    }
}
