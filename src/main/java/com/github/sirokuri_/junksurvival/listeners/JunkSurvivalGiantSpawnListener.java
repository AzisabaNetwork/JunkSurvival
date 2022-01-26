package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class JunkSurvivalGiantSpawnListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Location loc = e.getEntity().getLocation();
        World world = loc.getWorld();
        if (world == null) return;
        if (!world.getName().contains("world")) {
            return;
        }

        loc.getWorld().spawnEntity(loc, EntityType.GIANT);
    }
}
