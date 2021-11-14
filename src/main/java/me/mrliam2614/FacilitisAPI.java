package me.mrliam2614;

import me.mrliam2614.consoleManager.ConsoleMessage;
import me.mrliam2614.messages.messageManager;
import me.mrliam2614.startup.Messages;
import me.mrliam2614.utils.MySql;
import me.mrliam2614.utils.StrUtils;
import me.mrliam2614.vault.VaultImplementation;
import org.bukkit.plugin.java.JavaPlugin;


public class FacilitisAPI extends JavaPlugin {
    private static FacilitisAPI instance;
    public StrUtils strUtils;
    public ConsoleMessage console;
    public VaultImplementation vault;
    public MySql MySql;
    public Messages messages;
    public messageManager msg;

    @Override
    public void onEnable() {
        instance = this;
        vault = new VaultImplementation(this);
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
