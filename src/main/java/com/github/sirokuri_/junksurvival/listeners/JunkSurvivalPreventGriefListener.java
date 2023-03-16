package com.github.sirokuri_.junksurvival.listeners;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class JunkSurvivalPreventGriefListener implements Listener {

    private final List<Material> preventPlaceMaterials = Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK);
    private final List<Material> preventRightClickMaterials = Arrays.asList(Material.COMMAND_BLOCK_MINECART);

    private final List<Enchantment> enchantmentList = new ArrayList<>(Arrays.asList(Enchantment.values()));

    private final JunkSurvival plugin;

    public JunkSurvivalPreventGriefListener(JunkSurvival junkSurvival){
        this.plugin = junkSurvival;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Material material = e.getBlockPlaced().getType();
        if (preventPlaceMaterials.contains(material)) {
            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        if (world == null) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null) return;
            if (preventRightClickMaterials.contains(event.getItem().getType())) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
        if (world.getName().equalsIgnoreCase("junkSurvival") || world.getName().equalsIgnoreCase("world_nether") || world.getName().equalsIgnoreCase("world_the_end")){
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if (event.getHand() != EquipmentSlot.HAND) return;
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta == null) return;
                if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cどかーん"))){
                    player.getWorld().createExplosion(player.getLocation(), 10);
                    item.setAmount(item.getAmount() - 1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cどかーんをしようしました"));
                }

                if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&bびよーん"))){
                    Location loc = player.getEyeLocation();
                    Vector baseVec = loc.getDirection();
                    player.setVelocity(baseVec.multiply(5));
                    item.setAmount(item.getAmount() - 1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bびよーんをしようしました"));
                }

                if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cみちづれ"))){
                    List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                    Collections.shuffle(players);
                    Player targetPlayer = players.get(0);
                    Location targetPlayerLocation = targetPlayer.getLocation();
                    World targetLocationWorld = targetPlayerLocation.getWorld();
                    if (targetLocationWorld == null) return;
                    double damage = targetPlayer.getHealth();
                    if (targetLocationWorld.getName().equalsIgnoreCase("junkSurvival") || targetLocationWorld.getName().equalsIgnoreCase("world_nether") || targetLocationWorld.getName().equalsIgnoreCase("world_the_end")){
                        targetPlayer.damage(damage);
                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c" + targetPlayer.getDisplayName() + "&rが通り魔にやられた"));
                        item.setAmount(item.getAmount() - 1);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cみちずれをしようしました"));
                    }
                }

                if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&aぱわー!!"))){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,4000,255),true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,4000,255),true);
                    item.setAmount(item.getAmount() - 1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aぱわーをしようしました"));
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        Player player = (Player) event.getEntity();
        EntityType entityType = player.getType();
        if (entityType == EntityType.PLAYER){
            if (plugin.superEasyMode.contains(player)) {
                double damage = event.getDamage();
                event.setDamage(damage / 95);
            }
            if (plugin.easyMode.contains(player)) {
                double damage = event.getDamage();
                event.setDamage(damage / 80);
            }

            if (plugin.normalMode.contains(player)) {
                double damage = event.getDamage();
                event.setDamage(damage / 75);
            }
            if (plugin.hardMode.contains(player)) {
                double damage = event.getDamage();
                event.setDamage(damage / 50);
            }
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        if (world == null) return;
        if (entity.getType() == EntityType.SKELETON){
            event.setCancelled(true);
        }
        if (entity.getType() == EntityType.CREEPER){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onGame(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getHand() != EquipmentSlot.HAND) return;
            Block block = event.getClickedBlock();
            if (block == null) return;
            Location location = block.getLocation();
            World world = location.getWorld();
            if (world == null) return;
            if (world.getName().equalsIgnoreCase("world")){
                if (block.getType() == Material.EMERALD_BLOCK){
                    if (plugin.superEasyMode.contains(player)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&dすでにそのもーどだよ"));
                    } else {
                        plugin.easyMode.remove(player);
                        plugin.normalMode.remove(player);
                        plugin.hardMode.remove(player);
                        plugin.superEasyMode.add(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aすーぱーいーじーもーどおおおおお！！！！！"));
                    }

                }
                if (block.getType() == Material.DIAMOND_BLOCK){
                    if (plugin.easyMode.contains(player)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&dすでにそのもーどだよ"));
                    } else {
                        plugin.superEasyMode.remove(player);
                        plugin.normalMode.remove(player);
                        plugin.hardMode.remove(player);
                        plugin.easyMode.add(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bいーじーもーどおおおおお！！！！！"));
                    }
                }
                if (block.getType() == Material.IRON_BLOCK){
                    if (plugin.normalMode.contains(player)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&dすでにそのもーどだよ"));
                    } else {
                        plugin.superEasyMode.remove(player);
                        plugin.easyMode.remove(player);
                        plugin.hardMode.remove(player);
                        plugin.normalMode.add(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&lのーまるもーど"));
                    }
                }
                if (block.getType() == Material.REDSTONE_BLOCK){
                    if (plugin.hardMode.contains(player)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&dすでにそのもーどだよ"));
                    } else {
                        plugin.superEasyMode.remove(player);
                        plugin.easyMode.remove(player);
                        plugin.normalMode.remove(player);
                        plugin.hardMode.add(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cきついかもよ？"));
                    }
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getHand() != EquipmentSlot.HAND) return;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null) return;
            if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&cどかーん"))){
                event.setCancelled(true);
            } else if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&bびよーん"))) {
                event.setCancelled(true);
            } else if (itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',"&aぱわー!!"))){
                event.setCancelled(true);
            } else if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&aワールドボーダー拡張"))){
                event.setCancelled(true);
            } else {
                Random random = new Random();
                int randomInt = random.nextInt(255) + 1;
                itemStack.addUnsafeEnchantment(getRandomEnchant(),randomInt);
            }
        }
    }

    private Enchantment getRandomEnchant(){
        Random random = new Random();
        int rand = random.nextInt(enchantmentList.size());
        return enchantmentList.get(rand);
    }
}