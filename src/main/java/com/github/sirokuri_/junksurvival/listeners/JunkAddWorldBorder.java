package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
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

    private final JunkSurvival plugin;

    public JunkAddWorldBorder(JunkSurvival junkSurvival) {
        this.plugin = junkSurvival;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;
        if ((event.getHand() != EquipmentSlot.HAND)) return;
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&aワールドボーダー拡張"))){
            Bukkit.dispatchCommand(player,"worldborder add 10");
            itemStack.setAmount(itemStack.getAmount() - 1);
        }
    }
}