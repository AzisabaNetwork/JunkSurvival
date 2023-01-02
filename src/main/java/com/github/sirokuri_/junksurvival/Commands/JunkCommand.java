package com.github.sirokuri_.junksurvival.Commands;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

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
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lすげーアイテムが付与されました"));
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

                if (args[1].equalsIgnoreCase("5")) {
                    if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                        Player player = (Player) sender;
                        ItemStack powerItem = plugin.powerItem();
                        player.getInventory().addItem(powerItem);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lすげーアイテムが付与されました"));
                    }
                }
            }
            return true;
        }
        return true;
    }
}
