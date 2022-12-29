package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() == null) return;
            if (preventRightClickMaterials.contains(event.getItem().getType())) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta == null) return;
            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cどかーん"))){
                player.getWorld().createExplosion(player.getLocation(), 10);
                item.setAmount(item.getAmount() - 1);
            }

            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&bびよーん"))){
                player.setVelocity(player.getEyeLocation().toVector().multiply(10));
                item.setAmount(item.getAmount() - 1);
            }

            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cみちづれ"))){
                List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                Collections.shuffle(players);
                Player targetPlayer = players.get(0);
                double damage = 20;
                if (targetPlayer.getLocation().getWorld().getName().equalsIgnoreCase("junkSurvival")){
                    targetPlayer.damage(damage);
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c" + targetPlayer.getDisplayName() + "&rが通り魔にやられた"));
                    item.setAmount(item.getAmount() - 1);
                }
            }
        }
    }
}