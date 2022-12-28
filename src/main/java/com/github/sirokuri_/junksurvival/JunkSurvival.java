package com.github.sirokuri_.junksurvival;

import com.github.sirokuri_.junksurvival.Commands.JunkCommand;
import com.github.sirokuri_.junksurvival.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class JunkSurvival extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new JunkSurvivalItemDropListener(this), this);
        //Bukkit.getPluginManager().registerEvents(new JunkSurvivalCraftShuffleListener(), this);
        Bukkit.getPluginManager().registerEvents(new JunkSurvivalMessageListener(), this);
        Bukkit.getPluginManager().registerEvents(new JunkSurvivalPreventGriefListener(), this);
        Bukkit.getPluginManager().registerEvents(new JunkSurvivalGiantSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new JunkAddWorldBorder(),this);
        Bukkit.getPluginManager().registerEvents(new JunkSurvivalDisableWorld(),this);
        getCommand("junkSurvivalGive").setExecutor(new JunkCommand(this));
        Bukkit.getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(getName() + " disabled.");
    }

    public ItemStack addWorldBorderItem(){
        ItemStack borderItem = new ItemStack (Material.PAPER);
        ItemMeta itemMeta = borderItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&aワールドボーダー拡張"));
        borderItem.setItemMeta(itemMeta);
        return borderItem;
    }

    public ItemStack yabaiItem(){
        ItemStack bomb = new ItemStack (Material.TNT);
        ItemMeta itemMeta = bomb.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cどかーん"));
        bomb.setItemMeta(itemMeta);
        return bomb;
    }

    public ItemStack JumpItem(){
        ItemStack bomb = new ItemStack (Material.REDSTONE_TORCH);
        ItemMeta itemMeta = bomb.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&bびよーん"));
        bomb.setItemMeta(itemMeta);
        return bomb;
    }

    public ItemStack killItem(){
        ItemStack bomb = new ItemStack (Material.IRON_SWORD);
        ItemMeta itemMeta = bomb.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cみちづれ"));
        bomb.setItemMeta(itemMeta);
        return bomb;
    }
}
