package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class JunkSurvivalGiantSpawnListener implements Listener {

    public int playerDeathCount = 0;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        if (player == null) return;
        Location loc = player.getLocation();
        World world = loc.getWorld();
        if (world == null) return;
        if (!world.getName().contains("junkSurvival")) {
            return;
        }
        world.spawnEntity(loc, EntityType.GIANT);
        if (playerDeathCount == 10){
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                Location location = players.getLocation();
                World playersWorld = location.getWorld();
                if(playersWorld == null) return;
                playersWorld.spawnEntity(location,EntityType.WITHER);
            }
            playerDeathCount = 0;
        }else{
            playerDeathCount++;
        }
    }
}
