package com.github.sirokuri_.junksurvival;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.github.sirokuri_.junksurvival.listeners.GiantSpawnListener;
import com.github.sirokuri_.junksurvival.listeners.ItemDropListener;
import com.github.sirokuri_.junksurvival.listeners.MessageListener;
import com.github.sirokuri_.junksurvival.listeners.PreventGriefListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class JunkSurvival extends JavaPlugin {

    private static TaskChainFactory taskChainFactory;

    @Override
    public void onEnable() {
        taskChainFactory = BukkitTaskChainFactory.create(this);
        Bukkit.getPluginManager().registerEvents(new GiantSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(), this);
        Bukkit.getPluginManager().registerEvents(new MessageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PreventGriefListener(), this);
        Bukkit.getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(getName() + " disabled.");
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }
}
