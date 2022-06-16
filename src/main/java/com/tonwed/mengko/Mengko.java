package com.tonwed.mengko;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public final class Mengko extends JavaPlugin {


    @Override
    public void onLoad() {
        // Plugin startup logic
        FileConfiguration config = getConfig();
        boolean debug = config.getBoolean("debug", false);
        getLogger().info("插件载入完成" + debug);
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Event(), this);
        if (Bukkit.getPluginCommand("mksend") != null) {
            Bukkit.getPluginCommand("mksend").setExecutor(new Command());
            getLogger().info("命令已注册");
        }
        TCP.Start(7120);
        getLogger().info("插件已启用");

    }
    @Override
    public void onDisable() {
        getLogger().info("插件已卸载");
        // Plugin shutdown logic
    }
}
