package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JunkAddWorldBorder implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        if (world == null) return;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;
        if ((event.getHand() != EquipmentSlot.HAND)) return;
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if (world.getName().equalsIgnoreCase("junkSurvival") || world.getName().equalsIgnoreCase("world_nether") || world.getName().equalsIgnoreCase("world_the_end")){
            if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&aワールドボーダー拡張"))){
                WorldBorder worldBorder = world.getWorldBorder();
                double worldBorderSize = worldBorder.getSize();
                double newWorldBorderSize = worldBorderSize + 10;
                worldBorder.setSize(newWorldBorderSize);
                itemStack.setAmount(itemStack.getAmount() - 1);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&b&l" + player.getName() + "&rさんが" + world.getName() + " のワールドボーダーを拡張したよ！\n\n現在のワールドボーダーの大きさ : &b&l" +  + newWorldBorderSize));
                world.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        if (player == null) return;
        World world = player.getWorld();
        if (world.getName().equalsIgnoreCase("junkSurvival") || world.getName().equalsIgnoreCase("world_nether") || world.getName().equalsIgnoreCase("world_the_end")){
            WorldBorder worldBorder = world.getWorldBorder();
            double worldBorderSize = worldBorder.getSize();
            double newWorldBorderSize = worldBorderSize - 1;
            worldBorder.setSize(newWorldBorderSize);
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&lワールドボーダーが縮小！\n\n" + world.getName() + "&rの現在のワールドボーダーの大きさ : &c&l" + newWorldBorderSize));
            world.playSound(player.getLocation(),Sound.BLOCK_ANCIENT_DEBRIS_BREAK,1,1);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        double displayedHealth = player.getHealth() / player.getMaxHealth() * player.getHealthScale();
        player.setHealth(displayedHealth);
    }
}