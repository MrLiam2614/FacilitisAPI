package me.mrliam2614.FacilitisAPI;

import me.mrliam2614.FacilitisAPI.consoleManager.ConsoleMessage;
import me.mrliam2614.FacilitisAPI.messages.messageManager;
import me.mrliam2614.FacilitisAPI.startup.Messages;
import me.mrliam2614.FacilitisAPI.utils.MySql;
import me.mrliam2614.FacilitisAPI.utils.StrUtils;
import me.mrliam2614.FacilitisAPI.vault.vaultImplementation;
import org.bukkit.plugin.java.JavaPlugin;


public class FacilitisAPI extends JavaPlugin {
    private static FacilitisAPI instance;
    public StrUtils strUtils;
    public ConsoleMessage console;
    public vaultImplementation vault;
    public MySql MySql;
    public Messages messages;
    public messageManager msg;

    @Override
    public void onEnable() {
        instance = this;
        vault = new vaultImplementation(this);
        strUtils = new StrUtils(this);
        console = new ConsoleMessage(this);
        MySql = new MySql(this);
        messages = new Messages(this);
        msg = new messageManager(this);

        messages.EnableMessage(this);
    }

    public void onDisable(){
        messages.DisableMessage(this);
    }

    public static FacilitisAPI getInstance(){
        return instance;
    }
}
