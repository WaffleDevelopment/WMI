package org.waffledevelopment.cmi.commands.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// AFK full-form is: Away From Keyboard.

public class afk implements CommandExecutor {

    private static final Map<UUID, afkInfo> afkPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            boolean toggleAfk = true;
            String reason = "";
            boolean silent = false;

            for (String arg : args) {
                if (arg.startsWith("-p:")) {
                    player = Bukkit.getPlayer(arg.substring(3));
                    if (player == null) {
                        sender.sendMessage("Player not found.");
                        return true;
                    }
                } else if (arg.equals("-s")) {
                    silent = true;
                } else {
                    reason = arg;
                    toggleAfk = false;
                }
            }

            if (toggleAfk) {
                if (afkPlayers.containsKey(player.getUniqueId())) {
                    afkInfo afkInfo = afkPlayers.remove(player.getUniqueId());
                    if (!silent) {
                        Bukkit.broadcastMessage(player.getDisplayName() + " is no longer AFK");
                    }
                } else {
                    afkPlayers.put(player.getUniqueId(), new afkInfo(player, reason, silent));
                    if (!silent) {
                        Bukkit.broadcastMessage(player.getDisplayName() + " is now AFK");
                    }
                }
            } else {
                afkPlayers.put(player.getUniqueId(), new afkInfo(player, reason, silent));
                if (!silent) {
                    Bukkit.broadcastMessage(player.getDisplayName() + " is now AFK");
                }
            }
        } else {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        return true;
    }

    public static class afkInfo {
        private final Player player;
        private final String reason;
        private final boolean silent;

        afkInfo(Player player, String reason, boolean silent) {
            this.player = player;
            this.reason = reason;
            this.silent = silent;
        }
    }
}
