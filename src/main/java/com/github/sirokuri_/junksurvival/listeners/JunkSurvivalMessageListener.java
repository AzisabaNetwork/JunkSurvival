package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class JunkSurvivalMessageListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld().getName().contains("junkSurvival")) {
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "荒らし対策のため、ジャンクサバイバルは一週間間隔でリセットされます");
        }
    }
}
