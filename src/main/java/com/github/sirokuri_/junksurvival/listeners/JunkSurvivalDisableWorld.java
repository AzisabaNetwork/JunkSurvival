/*package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.Objects;

public class JunkSurvivalDisableWorld implements Listener {

    @EventHandler
    public void onWorld(PlayerPortalEvent event){
        World world = Objects.requireNonNull(event.getTo()).getWorld();
        if (world == null) return;
        if (world.getName().equalsIgnoreCase("world_nether") || world.getName().equalsIgnoreCase("world_the_end")){
            event.setCancelled(true);
        }
    }
}*/