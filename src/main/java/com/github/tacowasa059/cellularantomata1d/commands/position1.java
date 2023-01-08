package com.github.tacowasa059.cellularantomata1d.commands;

import com.github.tacowasa059.cellularantomata1d.CellularAntomata1d;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class position1 implements CommandExecutor {
    private final CellularAntomata1d plugin;

    public position1(CellularAntomata1d plugin) {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player =(Player)sender;
            Location location=player.getLocation();
            plugin.getConfig().set("position1",location);
            plugin.saveConfig();
            player.sendMessage(ChatColor.GREEN + "position1 was set!");
        }
        else{
            System.out.println("Run by player.");
        }
        return true;
    }
}
