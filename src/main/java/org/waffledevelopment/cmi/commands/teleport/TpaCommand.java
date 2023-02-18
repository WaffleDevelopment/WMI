package org.waffledevelopment.cmi.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpaCommand implements CommandExecutor {
    private final Map<String, TpaRequest> tpaRequests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        Player target = null;
        boolean useCurrentLocation = false;

        // Check if a target player name was provided
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);

            // Check if the player wants to use the current location of the target player
            if (args.length > 1 && args[1].equals("-c") && player.hasPermission("cmi.teleport.currentlocation")) {
                useCurrentLocation = true;
            }
        }

        // Check if a valid target player was found
        if (target == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        // Create a TPA request
        TpaRequest request = new TpaRequest(player, target, useCurrentLocation);
        tpaRequests.put(player.getUniqueId().toString(), request);

        // Send a request to the target player
        target.sendMessage(player.getName() + " has requested to teleport to you. Use '/tpaccept' to accept or '/tpdeny' to deny.");
        player.sendMessage("Teleport request sent to " + target.getName() + ".");

        return true;
    }
}

