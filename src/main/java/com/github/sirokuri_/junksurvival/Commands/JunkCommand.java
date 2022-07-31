package com.github.sirokuri_.junksurvival.Commands;

import com.github.sirokuri_.junksurvival.JunkSurvival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class JunkCommand implements CommandExecutor {

    private final JunkSurvival plugin;

    public JunkCommand(JunkSurvival junkSurvival) {
        this.plugin = junkSurvival;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("junkSurvivalGive")) {
            if (sender.hasPermission("junkSurvivalGive.permission.Admin")) {
                Player player = (Player) sender;
                ItemStack borderItem = plugin.addWorldBorderItem();
                player.getInventory().addItem(borderItem);
                Bukkit.getLogger().info("[JunkSurvival] " + player.getName() + " に寄付アイテムを付与しました");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l寄付アイテムが付与されました"));
            }
            return true;
        }
        return true;
    }
}
