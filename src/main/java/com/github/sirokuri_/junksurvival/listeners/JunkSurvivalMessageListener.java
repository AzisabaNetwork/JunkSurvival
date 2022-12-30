package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JunkSurvivalMessageListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains("junkSurvival")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"TPS激高の鯖で&a&lアイテム&rを壊すと色々なものが&a&lドロップ!?&r中には&a&lワールドの境界線&rを広げる&a&lアイテム&rなども!\nめちゃくちゃな世界を楽しもう!\n&a&lワールド移動&rや&a&l一定時間でつよつよなバフ&rが付与されるよ!"));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,4000,255),true);
        }
    }
}
