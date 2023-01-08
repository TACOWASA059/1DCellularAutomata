package com.github.tacowasa059.cellularantomata1d.commands;

import com.github.tacowasa059.cellularantomata1d.CellSet;
import com.github.tacowasa059.cellularantomata1d.CellularAntomata1d;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class GenerateAutomata implements CommandExecutor {
    private final CellularAntomata1d plugin;
    private BukkitTask task;//繰り返し処理タスク

    public GenerateAutomata(CellularAntomata1d plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "引数が不足しています。ルール番号を指定してください。");
            } else {
                int rule = Integer.parseInt(args[0]);
                if (rule < 0 || rule > 255) {
                    player.sendMessage(ChatColor.RED + "ルール番号が不適切です。0-255を指定してください。");
                }

                Location location1 = plugin.getConfig().getLocation("position1");
                Location location2 = plugin.getConfig().getLocation("position2");


                //設定されてないときにNUllを返す
                if (location1 != null || location2 != null) {
                    Location location_min=new Location(player.getWorld(),Math.min(location1.getX(),location2.getX()),Math.min(location1.getY(),location2.getY()),Math.min(location1.getZ(),location2.getZ()));
                    Location location_max=new Location(player.getWorld(),Math.max(location1.getX(),location2.getX()),Math.max(location1.getY(),location2.getY()),Math.max(location1.getZ(),location2.getZ()));
                    int n =Math.abs( (int) (location1.getX() - location2.getX()));
                    int tmax = Math.abs((int) (location1.getZ() - location2.getZ()));
                    plugin.cellSet = new CellSet(rule, n, tmax,location_min);
                    task=plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                        plugin.cellSet.SetNextState();
                        if(plugin.cellSet.getT()>tmax){
                            task.cancel();
                        }
                    }, 0L, 1L);
                    player.sendMessage(ChatColor.GREEN + "Success!");
                    player.sendMessage(ChatColor.AQUA+"The smallest row of z-coordinates is the initial state, so if you want to change the initial value, change it there.");
                } else {
                    player.sendMessage(ChatColor.RED + "Set position1 and position2");
                }
            }
        }
        return true;
    }
}
