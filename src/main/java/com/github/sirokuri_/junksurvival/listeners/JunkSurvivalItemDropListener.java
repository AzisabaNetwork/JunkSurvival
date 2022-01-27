package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class JunkSurvivalItemDropListener implements Listener {

    private final List<Material> materialList = Arrays.asList(Material.values().clone());
    private final Random rand = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        if (!block.getWorld().getName().contains("world")) {
            return;
        }
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) {
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
                    Item entityItem = block.getWorld().dropItem(location, item);
                    entityItem.setTicksLived(4800);
                }
                removeExceedItems(block.getWorld());
            }
        } catch (IllegalArgumentException illegalArgumentException){
            illegalArgumentException.fillInStackTrace();
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

    private void removeExceedItems(World world) {
        JunkSurvival.newSharedChain("RemoveExceedItems").syncFirst(world::getEntities).asyncLast(input -> {
            List<Item> items = input.stream().filter(ent -> ent instanceof Item).
                    map(ent -> (Item) ent).sorted(Comparator.comparingInt(Entity::getTicksLived).reversed()).collect(Collectors.toList());
            for (int i = 0, size = items.size(); i < size - 400; i++) {
                items.get(i).remove();
            }
        }).execute();
    }
}