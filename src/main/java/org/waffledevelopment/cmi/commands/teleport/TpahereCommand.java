package org.waffledevelopment.cmi.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpahereCommand implements CommandExecutor {
    private final Map<String, TpahereRequest> tpahereRequests = new HashMap<>();

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
            if (target == null) {
                sender.sendMessage("Player not found.");
                return true;
            }
        }    // Check if the use current location flag was set
        if (args.length > 1 && args[1].equals("-c")) {
            if (!player.hasPermission("cmi.teleport.currentlocation")) {
                player.sendMessage("You do not have permission to teleport to a player's current location.");
                return true;
            }
            useCurrentLocation = true;
        }

        // Check if a TPA request for the target player is already pending
        if (tpahereRequests.containsKey(target.getUniqueId().toString())) {
            player.sendMessage("A TPA request for this player is already pending.");
            return true;
        }

        // Create the TPA request and add it to the map
        TpahereRequest request = new TpahereRequest(player, target, useCurrentLocation);
        tpahereRequests.put(target.getUniqueId().toString(), request);

        // Notify the target player of the TPA request
        target.sendMessage(player.getName() + " has requested to teleport to your location.");
        target.sendMessage("Use '/tpaccept' to accept or '/tpdeny' to deny the request.");

        return true;
    }
}
