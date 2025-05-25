package org.kiva.mCore.api;

import org.kiva.mCore.MCore;

public class Config {
    public static String  PREFIX = "MCore";
    public static String MSG_NO_PERM = "";
    public static boolean DEBUG = false;

    public static void LoadCoreConfig(){
        // Global stuff
        PREFIX       = MCore.instance.getConfig().getString ("global.prefix");
        // Messages
        MSG_NO_PERM    = MCore.instance.getConfig().getString ("messages.noPerm");
        DEBUG    = MCore.instance.getConfig().getBoolean ("debug");
    }
}