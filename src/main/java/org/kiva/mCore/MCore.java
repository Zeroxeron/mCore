package org.kiva.mCore;
import org.bukkit.plugin.java.JavaPlugin;
import org.kiva.mCore.command.Cmd;

import static org.kiva.mCore.api.Config.LoadCoreConfig;
import static org.kiva.mCore.api.Notifications.CreateNotification;

public final class MCore extends JavaPlugin {
    public static MCore instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        LoadCoreConfig();
        // Plugin startup logic
        new Cmd();
        CreateNotification("&aAyo from x_Kiva_x! Running v."+this.getDescription().getVersion()+" mCore!", MsgModes.CONSOLE);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
