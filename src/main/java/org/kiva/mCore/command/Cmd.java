package org.kiva.mCore.command;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kiva.mCore.api.Config;
import org.kiva.mCore.MCore;

import java.util.ArrayList;
import java.util.List;
import static org.kiva.mCore.api.Config.PREFIX;
import static org.kiva.mCore.api.Notifications.Msg;


public class Cmd extends Cmd_Abstract {
    private static String MsgNoPerm = MCore.instance.getConfig().getString("messages.noPermission");

    public Cmd() {super("MCore");}

    public void execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("MCore.admin")) {
            Msg(sender, MsgNoPerm); return;}

        if (args.length == 0) {
            Msg(sender, ChatColor.GOLD +""+ChatColor.BOLD+ChatColor.ITALIC +"MCore "+MCore.instance.getDescription().getVersion());
            Msg(sender, "/" + label + " reload");
            return;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            MCore.instance.reloadConfig();
            Config.LoadCoreConfig();
            MsgNoPerm = MCore.instance.getConfig().getString("messages.noPerm");
            sender.sendMessage("MCore reloaded!");
            return;
        }
        if (args[0].equalsIgnoreCase("version")) {
            Msg(sender, PREFIX +MCore.instance.getDescription().getVersion());
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) return Lists.newArrayList("reload", "version");
        return new ArrayList<>();
    }
}
