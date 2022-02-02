package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class JunkSurvivalGiantSpawnListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Location loc = e.getEntity().getLocation();
        World world = loc.getWorld();
        if (world == null) return;
        if (!world.getName().contains("junkSurvival")) {
            return;
        }
        loc.getWorld().spawnEntity(loc, EntityType.GIANT);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        if (world == null) return;
        if (world.getName().contains("junkSurvival")) {
            if (!(entity.getType() == EntityType.GIANT)){
                event.setCancelled(true);
            }
        }
    }
}
