package org.kiva.mCore.command;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.kiva.mCore.MCore;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class Cmd_Abstract implements CommandExecutor, TabCompleter {

    public Cmd_Abstract(String command){
        PluginCommand pluginCommand = MCore.instance.getCommand(command);
        if(pluginCommand != null){
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    public List<String> complete(CommandSender ignoredSender, String[] ignoredArgs){
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, label, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return filter(complete(sender, args), args);
    }

    private List<String> filter(List<String> list, String[] args){
        if(list == null) return null;
        String last = args[args.length - 1];
        List<String> result = new ArrayList<>();
        for(String arg : list){
            if(arg.toLowerCase().startsWith(last.toLowerCase())) result.add(arg);
        }
        return result;
    }
}
