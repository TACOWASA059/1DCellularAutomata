package com.github.tacowasa059.cellularantomata1d;

import com.github.tacowasa059.cellularantomata1d.commands.GenerateAutomata;
import com.github.tacowasa059.cellularantomata1d.commands.position1;
import com.github.tacowasa059.cellularantomata1d.commands.position2;
import org.bukkit.plugin.java.JavaPlugin;

public final class CellularAntomata1d extends JavaPlugin {
    public CellSet cellSet;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("position1").setExecutor(new position1(this));
        getCommand("position2").setExecutor(new position2(this));
        getCommand("gen1dAutomata").setExecutor(new GenerateAutomata(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
