package com.github.sirokuri_.junksurvival.Commands;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JunkCommand implements CommandExecutor {

    private final JunkSurvival plugin;

    public JunkCommand(JunkSurvival junkSurvival) {
        this.plugin = junkSurvival;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("junkSurvivalGive")) {
            if (args[0].equalsIgnoreCase("get")) {
                if (args.length <= 1) {
                    return true;
                }
                if (args[1].equalsIgnoreCase("1")) {
                    if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                        Player player = (Player) sender;
                        ItemStack borderItem = plugin.addWorldBorderItem();
                        player.getInventory().addItem(borderItem);
                        Bukkit.getLogger().info("[JunkSurvival] " + player.getName() + " に寄付アイテムを付与しました");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l寄付アイテムが付与されました"));
                    }
                }

                if (args[1].equalsIgnoreCase("2")) {
                    if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                        Player player = (Player) sender;
                        ItemStack bombItem = plugin.yabaiItem();
                        player.getInventory().addItem(bombItem);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムが付与されました"));
                    }
                }

                if (args[1].equalsIgnoreCase("3")) {
                    if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                        Player player = (Player) sender;
                        ItemStack jumpItem = plugin.JumpItem();
                        player.getInventory().addItem(jumpItem);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムが付与されました"));
                    }
                }

                if (args[1].equalsIgnoreCase("4")) {
                    if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                        Player player = (Player) sender;
                        ItemStack killItem = plugin.killItem();
                        player.getInventory().addItem(killItem);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&lやべーアイテムが付与されました"));
                    }
                }
            }
            return true;
        }
        return true;
    }
}
