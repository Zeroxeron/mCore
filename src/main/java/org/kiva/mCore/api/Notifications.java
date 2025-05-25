package org.kiva.mCore.api;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kiva.mCore.MsgModes;

import static org.bukkit.Bukkit.getServer;
import static org.kiva.mCore.api.Config.*;

public class Notifications {
    /**
     * Creates a message notification.
     * @param msg Text to make the notification of.
     */
    public static void CreateNotification(String msg, MsgModes mode){
        msg = r(PREFIX + "&8 > &7" + msg);
        switch (mode){
            case MsgModes.PLAYERS: // Players only
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.hasPermission("MCore.notify")){
                        player.sendMessage(msg);
                    }
                }
                break;
            case MsgModes.CONSOLE: // Console only
                getServer().getConsoleSender().sendMessage(msg);
                break;
            case MsgModes.ALL: // Players + Console
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.hasPermission("MCore.notify")){
                        player.sendMessage(msg);
                    }
                }
                getServer().getConsoleSender().sendMessage(msg);
                break;
            case MsgModes.DEBUG: // Debug mode -> Console
                if (DEBUG){
                    getServer().getConsoleSender().sendMessage(msg);
                }
                break;
            default:
                getServer().getConsoleSender().sendMessage("Wrong message mode used: "+mode);
                getServer().getConsoleSender().sendMessage(msg);
                break;
        }
    }

    public static void SendMsg(CommandSender sender, String msg){
        if(sender.hasPermission("MCore.notify")) {
            sender.sendMessage(r(msg));
        }
    }
    public static void SendMsgNoPrefix(CommandSender sender, String msg){
        if(sender.hasPermission("MCore.notify")) {
            sender.sendMessage(r(msg));
        }
    }
    public static void ComponentNP(CommandSender sender, BaseComponent component){
        if(sender.hasPermission("MCore.notify")) {
            if (sender instanceof Player pl) {
                pl.spigot().sendMessage(component);
            } else {
                sender.sendMessage(component.toString());
            }
        }
    }

    public static void SendMsgComponent(CommandSender sender, String msg, ClickEvent.Action Click_Action , String Click_Action_Command, HoverEvent.Action Hover_Action, String Hover_Action_Text){
        if (sender instanceof Player pl) {
            ComponentBuilder component =
                    new ComponentBuilder(msg)
                            .event(new ClickEvent(Click_Action, Click_Action_Command))
                            .event(new HoverEvent(Hover_Action,new ComponentBuilder().append(Hover_Action_Text).create()));
            pl.spigot().sendMessage(component.create());
        } else {
            sender.sendMessage(msg);
        }
    }

    public static String r(String msg) {
        msg = msg.replace("&1", ""+ChatColor.DARK_BLUE);
        msg = msg.replace("&2", ""+ChatColor.DARK_GREEN);
        msg = msg.replace("&3", ""+ChatColor.DARK_AQUA);
        msg = msg.replace("&4", ""+ChatColor.DARK_RED);
        msg = msg.replace("&5", ""+ChatColor.DARK_PURPLE);
        msg = msg.replace("&6", ""+ChatColor.GOLD);
        msg = msg.replace("&7", ""+ChatColor.GRAY);
        msg = msg.replace("&8", ""+ChatColor.DARK_GRAY);
        msg = msg.replace("&9", ""+ChatColor.BLUE);
        msg = msg.replace("&0", ""+ChatColor.BLACK);
        msg = msg.replace("&a", ""+ChatColor.GREEN);
        msg = msg.replace("&b", ""+ChatColor.AQUA);
        msg = msg.replace("&c", ""+ChatColor.RED);
        msg = msg.replace("&d", ""+ChatColor.LIGHT_PURPLE);
        msg = msg.replace("&e", ""+ChatColor.YELLOW);
        msg = msg.replace("&f", ""+ChatColor.WHITE);
        msg = msg.replace("&r", ""+ChatColor.RESET);
        msg = msg.replace("&k", ""+ChatColor.MAGIC);
        msg = msg.replace("&m", ""+ChatColor.STRIKETHROUGH);
        msg = msg.replace("&o", ""+ChatColor.ITALIC);
        msg = msg.replace("&n", ""+ChatColor.UNDERLINE);
        msg = msg.replace("&l", ""+ChatColor.BOLD);
        return msg;
    }
}

