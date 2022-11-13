package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JunkSurvivalPreventGriefListener implements Listener {

    private final List<Material> preventPlaceMaterials = Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK);
    private final List<Material> preventRightClickMaterials = Collections.singletonList(Material.COMMAND_BLOCK_MINECART);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Material material = e.getBlockPlaced().getType();
        if (preventPlaceMaterials.contains(material)) {
            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() == null) return;
            if (preventRightClickMaterials.contains(e.getItem().getType())) {
                e.setCancelled(true);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR){
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta == null) return;
            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cどかーん"))){
                e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 10);
                item.setAmount(item.getAmount() - 1);
            }

            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&bびよーん"))){
                e.getPlayer().setVelocity(e.getPlayer().getEyeLocation().toVector().multiply(10));
                item.setAmount(item.getAmount() - 1);
            }
            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cみちづれ"))){
                List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                Collections.shuffle(players);
                Player player = players.get(0);
                double damage = 20;
                player.damage(damage);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c" + player.getDisplayName() + "&rが通り魔にやられた"));
                item.setAmount(item.getAmount() - 1);
            }
        }
    }
}
