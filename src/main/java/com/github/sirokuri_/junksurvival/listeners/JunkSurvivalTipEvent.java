package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class JunkSurvivalTipEvent implements Listener {

    private final JunkSurvival plugin;

    public JunkSurvivalTipEvent(JunkSurvival junkSurvivalTipEvent) {
        this.plugin = junkSurvivalTipEvent;
        JunkTimer();
    }

    private void JunkTimer() {
        plugin.task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Location location = player.getLocation();
                    World world = location.getWorld();
                    if (world == null) return;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&ljunkSurvival&r対象ワールドにいる\n&a&l全プレイヤーに一定時間のバフが付与されたよ！"));
                    if (world.getName().equalsIgnoreCase("junkSurvival") || world.getName().equalsIgnoreCase("world_nether") || world.getName().equalsIgnoreCase("world_the_end")){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,4000,255),true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,4000,255),true);
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    }
                }
            }
        };
        plugin.task.runTaskTimer(plugin, 0, 50000);
    }
}
