package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JunkSurvivalMessageListener implements Listener {

    @SuppressWarnings({"deprecation"})
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains("junkSurvival")) {
            player.sendMessage(ChatColor.GREEN + "TPS激高の鯖でアイテムを壊すと色々なものがドロップ!?めちゃくちゃな世界を楽しもう");
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,4000,255),true);
        }
    }
}
