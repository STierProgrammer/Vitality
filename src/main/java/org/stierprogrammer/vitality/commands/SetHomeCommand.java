package org.stierprogrammer.vitality.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stierprogrammer.vitality.Vitality;

import java.util.UUID;


public class SetHomeCommand implements CommandExecutor {
    private final Vitality plugin;

    public SetHomeCommand(Vitality plugin) {
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
            sender.sendMessage("Only players are allowed to use this command!");

            return true;
        }

        if (args.length < 1) {
            player.sendMessage("Invalid amount of arguments provided!");

            return true;
        }

        UUID playerID = player.getUniqueId();
        String name = args[0];
        Location location = player.getLocation();

        this.plugin.homeConfig.saveHome(playerID, name, location);
        player.sendMessage(ChatColor.GREEN + "Your home has been set!");

        return true ;
    }
}
