package org.waffledevelopment.cmi.commands.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class air implements CommandExecutor {

    // Changes the Amount of Air a Player has.

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player player = (Player) sender;
            Player target;
            int airAm;

            //Getting Target
            if (Bukkit.getPlayer(args[0]) != null){
                target = Bukkit.getPlayer(args[0]);
            } else {
                target = player;
            }

            //Amount of AIR needed
            if (args.length > 1){
                airAm = Integer.parseInt(args[1]);
            } else {
                sender.sendMessage("No Amount of Air Specified");
                return true;
            }

            // Add air to the target player's air bar
            assert target != null;
            target.setRemainingAir(target.getRemainingAir() + airAm);
            sender.sendMessage("Added " + airAm + " air to " + target.getName() + ".");
        } else {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        return true;
    }
}
