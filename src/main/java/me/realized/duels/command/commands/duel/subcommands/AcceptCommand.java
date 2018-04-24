package me.realized.duels.command.commands.duel.subcommands;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.command.BaseCommand;
import me.realized.duels.request.Request;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptCommand extends BaseCommand {

    public AcceptCommand(final DuelsPlugin plugin) {
        super(plugin, "accept", "accept [player]", null, 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final Player player = (Player) sender;
        final Player target = Bukkit.getPlayerExact(args[1]);

        if (target == null) {
            sender.sendMessage("Player not found");
            return;
        }

        final Request request;

        if ((request = requestManager.remove(target, player)) == null) {
            sender.sendMessage("You do not have a request from " + target.getName());
            return;
        }

        sender.sendMessage("Accepted request from " + target.getName());
        target.sendMessage(ChatColor.GREEN + sender.getName() + " accepted your request");
        duelManager.startMatch(player, target, request);
    }
}
