package org.waffledevelopment.cmi.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sudo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("WMI.command.sudo")) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            player.sendMessage("Usage: /sudo [playerName] [command/c:[text]]");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        if (args[1].startsWith("c:")) {
            target.chat(args[1].substring(2));
        } else {
            Bukkit.dispatchCommand(target, args[1]);
        }

        return true;
    }

}
