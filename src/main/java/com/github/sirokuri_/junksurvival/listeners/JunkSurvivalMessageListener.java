package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class JunkSurvivalMessageListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains("junkSurvival")) {
            player.sendMessage(ChatColor.GREEN + "TPS激高の鯖でアイテムを壊すと色々なものがドロップ!?めちゃくちゃな世界を楽しもう");
        }
    }
}
