/*package com.github.sirokuri_.junksurvival.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JunkSurvivalCraftShuffleListener implements Listener {

    private final List<Material> materialList = Arrays.asList(Material.values().clone());
    private final Random rand = new Random();


    @EventHandler
    public void onCraftHideItem(PrepareItemCraftEvent e){
        ItemStack[] itemStacks = e.getInventory().getMatrix();
        for (int i = 0; i < 1; i++) {
            ItemStack itemStack = itemStacks[i];
            if (itemStack == null) {
                continue;
            }
            if (!(itemStack == getRandomItem())) {
                e.getInventory().setResult(getRandomItem());
            }
        }
    }

    private ItemStack getRandomItem() {
        Material material = null;
        while (material == null || !isValidItem(material)) {
            material = materialList.get(rand.nextInt(materialList.size() -1));
        }
        ItemStack item = new ItemStack(material);
        item.setAmount(rand.nextInt(64) + 1);
        return item;
    }

    private final List<Material> invalids = Arrays.asList(Material.AIR, Material.FIRE, Material.WATER, Material.LAVA);

    private boolean isValidItem(Material material) {
        return !invalids.contains(material);
    }
}*/
