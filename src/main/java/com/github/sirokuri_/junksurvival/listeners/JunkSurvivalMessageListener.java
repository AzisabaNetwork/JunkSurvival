package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class JunkSurvivalMessageListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        if (event.getPlayer().getWorld().getName().contains("junkSurvival")) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "荒らし対策のため、ジャンクサバイバルは一日でワールドリセットされます");
            event.getPlayer().sendMessage(ChatColor.GREEN + "TPS激高の鯖でアイテムを壊すと色々なものがドロップ!?めちゃくちゃな世界を楽しもう");
        }
    }
}
