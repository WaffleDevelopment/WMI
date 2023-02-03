package org.waffledevelopment.cmi.commands.misc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/* Fun Fact: The AFK acronym has been around since the early days of internet culture,
   specifically in chat rooms in the 1990s. It even dates back to an online news
   bulletin from FidoNews in 1989, alongside other emoticons and abbreviations
*/

public class afkCheck implements CommandExecutor{

    private final Map<String,afk.afkInfo> afkPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Player target = null;
            if (args.length > 0) {
                target = Bukkit.getPlayer(args[0]);
            } else {
                target = player;
            }

            if (target == null) {
                sender.sendMessage("Player not found.");
                return true;
            }

            // Check the AFK status of the target player
            if (afkPlayers.containsKey(target.getUniqueId().toString())) {
                sender.sendMessage(target.getName() + " is AFK.");
            } else {
                sender.sendMessage(target.getName() + " is not AFK.");
            }
        } else {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        return true;
    }
}