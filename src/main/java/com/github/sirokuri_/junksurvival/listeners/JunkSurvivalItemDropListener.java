package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class JunkSurvivalItemDropListener implements Listener {

    private final List<Material> materialList = Arrays.asList(Material.values().clone());


    private final Random rand = new Random();

    private final JunkSurvival plugin;

    public JunkSurvivalItemDropListener(JunkSurvival junkSurvival) {
        this.plugin = junkSurvival;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        Player player = event.getPlayer();;
        if (block.getWorld().getName().contains("junkSurvival") || block.getWorld().getName().contains("world_nether") || block.getWorld().getName().contains("world_the_end")) {
            if (player.getGameMode() != GameMode.SURVIVAL) {
                return;
            }
            if (block.getType() == Material.AIR) return;
            try {
                if (material.isOccluding() || material == Material.GLASS || material == Material.BLUE_STAINED_GLASS || material == Material.BLACK_STAINED_GLASS
                        || material == Material.BROWN_STAINED_GLASS || material == Material.CYAN_STAINED_GLASS || material == Material.GRAY_STAINED_GLASS || material == Material.GREEN_STAINED_GLASS
                        || material == Material.LIGHT_BLUE_STAINED_GLASS || material == Material.LIGHT_GRAY_STAINED_GLASS || material == Material.LIME_STAINED_GLASS
                        || material == Material.MAGENTA_STAINED_GLASS || material == Material.ORANGE_STAINED_GLASS || material == Material.PINK_STAINED_GLASS
                        || material == Material.PURPLE_STAINED_GLASS || material == Material.RED_STAINED_GLASS || material == Material.WHITE_STAINED_GLASS || material == Material.YELLOW_STAINED_GLASS) {
                    for (int i = rand.nextInt(9) + 2; 0 < i; i--) {
                        ItemStack item = getRandomItem();
                        World world = block.getWorld();
                        Item entityItem = world.dropItem(location, item);
                        Random random = new Random();
                        int num = random.nextInt(100);
                        if (1 <= num && num <= 4) {
                            ItemStack bomb = plugin.yabaiItem();
                            world.dropItem(location,bomb);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムがドロップしました"));
                        }else if (5 <= num && num <= 15) {
                            ItemStack jumpItem = plugin.JumpItem();
                            world.dropItem(location,jumpItem);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムがドロップしました"));
                        }else if (16 <= num && num <= 18) {
                            ItemStack killItem = plugin.killItem();
                            world.dropItem(location,killItem);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムがドロップしました"));
                        }else if(19 <= num && num <= 20) {
                            ItemStack worldBorder = plugin.addWorldBorderItem();
                            world.dropItem(location,worldBorder);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lすげーアイテムがドロップしました"));
                        }else if(21 <= num && num <= 25) {
                            ItemStack powerItem = plugin.powerItem();
                            world.dropItem(location, powerItem);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lすげーアイテムがドロップしました"));
                        }
                        entityItem.setTicksLived(4800);
                    }
                }
            } catch (IllegalArgumentException illegalArgumentException){
                illegalArgumentException.fillInStackTrace();
            }
        }
    }

    private ItemStack getRandomItem() {
        Material material = null;
        while (material == null || !isValidItem(material)) {
            material = materialList.get(rand.nextInt(materialList.size()));
        }
        ItemStack item = new ItemStack(material);
        item.setAmount(rand.nextInt(64) + 1);
        return item;
    }

    private final List<Material> invalids = Arrays.asList(Material.AIR, Material.FIRE, Material.WATER, Material.LAVA , Material.AIR);

    private boolean isValidItem(Material material) {
        return !invalids.contains(material);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event){
        int cost = event.getExpLevelCost();
        Player player = event.getEnchanter();
        event.setExpLevelCost(cost / 2);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&d元のエンチャントコスト &r: &d" + cost + "\n&d消費エンチャントコスト &r: &d" + event.getExpLevelCost()));
    }
}