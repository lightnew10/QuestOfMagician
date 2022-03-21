package fr.lightnew.commands;

import fr.lightnew.game.GameStats;
import fr.lightnew.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender player, Command command, String s, String[] args) {
        if (player.isOp()) {
            if (args.length == 0)
                player.sendMessage(ChatColor.YELLOW + "Votre statut : " + GameStats.getCurrentState());
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("lobby")) {
                    player.sendMessage(ChatColor.YELLOW + "Nouveau statut : " + ChatColor.RESET + "LOBBY");
                    GameStats.setState(GameStats.LOBBY);
                }
                if (args[0].equalsIgnoreCase("game")) {
                    player.sendMessage(ChatColor.YELLOW + "Nouveau statut : " + ChatColor.RESET + "GAME");
                    for(Player players : Bukkit.getOnlinePlayers())
                        TeamManager.setPlayerInRandomTeam(players);
                    GameStats.setState(GameStats.GAME);
                }
                if (args[0].equalsIgnoreCase("end")) {
                    player.sendMessage(ChatColor.YELLOW + "Nouveau statut : " + ChatColor.RESET + "END");
                    GameStats.setState(GameStats.END);
                }
            }
        }
        return false;
    }
}
