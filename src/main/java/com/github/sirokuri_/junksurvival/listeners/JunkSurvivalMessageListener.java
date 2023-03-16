package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JunkSurvivalMessageListener implements Listener {

    private final JunkSurvival plugin;

    public JunkSurvivalMessageListener(JunkSurvival junkSurvival){
        this.plugin = junkSurvival;
    }

    BossBar bossBar = Bukkit.createBossBar(ChatColor.RED + "JunkSurvivalへようこそ!", BarColor.PURPLE, BarStyle.SOLID);

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains("junkSurvival") || player.getWorld().getName().contains("world_nether") || player.getWorld().getName().contains("world_the_end")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"TPS激高の鯖で&a&lアイテム&rを壊すと色々なものが&a&lドロップ!?&r中には&a&lワールドの境界線&rを広げる&a&lアイテム&rなども!\nめちゃくちゃな世界を楽しもう!\n&a&lワールド移動&rや&a&l一定時間でつよつよなバフ&rが付与されるよ!"));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,4000,255),true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,4000,255),true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        bossBar.addPlayer(player);
        bossBar.setTitle(ChatColor.RED + "JunkSurvivalへようこそ!");
        double displayedHealth = player.getHealth() / player.getMaxHealth() * player.getHealthScale();
        player.setHealth(displayedHealth);
        plugin.superEasyMode.add(player);
        plugin.easyMode.remove(player);
        plugin.normalMode.remove(player);
        plugin.hardMode.remove(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        bossBar.removePlayer(player);
        plugin.superEasyMode.remove(player);
        plugin.easyMode.remove(player);
        plugin.normalMode.remove(player);
        plugin.hardMode.remove(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        event.setMessage(message + "///");
    }
}
