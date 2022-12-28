package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (player.getLocation().getWorld().getName().equalsIgnoreCase("junkSurvival")){
            if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&aワールドボーダー拡張"))){
                Bukkit.dispatchCommand(player,"worldborder add 10");
                itemStack.setAmount(itemStack.getAmount() - 1);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&d" + player.getName() + "&rさんがワールドボーダーを拡張したよ！"));
                world.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
            }
        }
    }
}