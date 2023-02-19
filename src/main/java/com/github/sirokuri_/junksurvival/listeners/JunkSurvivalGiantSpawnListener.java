package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;
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
        if (!world.getName().contains("junkSurvival") || !world.getName().contains("world_nether") || !world.getName().contains("world_the_end")) {
            return;
        }
        world.spawnEntity(loc, EntityType.GIANT);
        if (playerDeathCount == 5){
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                Location location = players.getLocation();
                World playersWorld = location.getWorld();
                if (playersWorld == null) return;
                if (playersWorld.getName().contains("junkSurvival") || playersWorld.getName().contains("world_nether") || playersWorld.getName().contains("world_the_end")){
                    playersWorld.spawnEntity(location,getRandomMobs());
                }
            }
            playerDeathCount = 0;
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"プレイヤー合計死亡回数により &c" + getRandomMobs() + " &rが召喚!\nプレイヤーの合計死亡回数がリセットされました"));
        }else{
            playerDeathCount++;
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"プレイヤーの合計死亡回数 : &c" + playerDeathCount));
        }
    }

    /*@EventHandler
    public void onExplode(EntityExplodeEvent event){
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        if (world == null) return;
        if (entity.getType() == EntityType.CREEPER){
            event.setCancelled(true);
            world.createExplosion(entity.getLocation(),1,false);
        }
    }*/

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
