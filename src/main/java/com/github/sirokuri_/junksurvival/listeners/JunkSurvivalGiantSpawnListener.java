package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JunkSurvivalGiantSpawnListener implements Listener {

    public int playerDeathCount = 0;

    private final Random rand = new Random();

    private final List<EntityType> entityTypeList = Arrays.asList(EntityType.values().clone());

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
                playersWorld.spawnEntity(location,getRandomMobs());
            }
            playerDeathCount = 0;
        }else{
            playerDeathCount++;
        }
    }

    private EntityType getRandomMobs() {
        EntityType entityType = null;
        while (entityType == null || !isValidEntity(entityType)) {
            entityType = entityTypeList.get(rand.nextInt(entityTypeList.size()));
        }
        return entityType;
    }

    private final List<EntityType> invalids = Arrays.asList(EntityType.ENDER_DRAGON,EntityType.WITHER);

    private boolean isValidEntity(EntityType entityType) {
        return !invalids.contains(entityType);
    }
}
