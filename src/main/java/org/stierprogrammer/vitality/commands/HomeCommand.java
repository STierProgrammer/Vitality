package org.stierprogrammer.vitality.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stierprogrammer.vitality.Vitality;

import java.util.UUID;

public class HomeCommand implements CommandExecutor {
    private final Vitality plugin;

    public HomeCommand(Vitality plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Invalid amount of arguments provided!");
            return true;
        }

        String name = args[0];
        UUID playerID = player.getUniqueId();

        Location location = this.plugin.homeConfig.getHome(playerID, name);

        if (location == null) {
            player.sendMessage(ChatColor.RED + "Home doesn't exist!");
            return true;
        }

        player.teleport(location);

        return true;
    }
}
