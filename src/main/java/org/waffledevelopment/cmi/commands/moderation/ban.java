package org.waffledevelopment.cmi.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class ban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("WMI.command.ban")) {
            player.sendMessage("You do not have permission to execute this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("Please specify a player to ban.");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        if (target.hasPermission("WMI.command.ban.bypass")) {
            player.sendMessage("You cannot ban this player.");
            return true;
        }

        String reason = "Banned by an operator.";
        boolean silent = false;
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                if (args[i].equals("-s")) {
                    silent = true;
                } else {
                    reason = reason + " " + args[i];
                }
            }
        }

        Bukkit.broadcastMessage(target.getName() + " was banned: " + reason);

        return true;
    }
}
