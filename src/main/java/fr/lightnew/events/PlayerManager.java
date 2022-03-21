package fr.lightnew.events;

import fr.lightnew.game.GameSettings;
import fr.lightnew.game.GameStats;
import fr.lightnew.kits.KitManager;
import fr.lightnew.teams.TeamTempManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager implements Listener {
    private int max_players = 10;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getOnlinePlayers().size() >= max_players)
            player.kickPlayer(ChatColor.RED + "Serveur Plein");
        for (int i = 0; i < 40; i++)
            player.getInventory().clear(i);

        String message_lobby = ChatColor.YELLOW + player.getName() + " à rejoins la partie " + ChatColor.GRAY + "(" + ChatColor.GOLD + Bukkit.getOnlinePlayers().size() + ChatColor.RESET + "/" + ChatColor.YELLOW + 10 + ChatColor.GRAY + ")";
        String message_game = player.getName() + " à rejoins la partie !";
        String message_end = "";

        if (GameStats.isState(GameStats.LOBBY)) {
            player.setGameMode(GameMode.ADVENTURE);
            event.setJoinMessage(message_lobby);
            //add player in default kit
            KitManager.setDefautlKit(player);
            //add prefix bellow name
            player.getInventory().setItem(2, GameSettings.TEAMS);
            player.getInventory().setItem(6, GameSettings.KITS);
        }
        if (GameStats.isState(GameStats.GAME)) {
            event.setJoinMessage(message_game);
        }
        if (GameStats.isState(GameStats.END)) {
            event.setJoinMessage(message_end);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String message_lobby = player.getName() + " à quitter la partie " + ChatColor.GRAY + "(" + ChatColor.GREEN + (Bukkit.getOnlinePlayers().size()-1) + ChatColor.RESET + "/" + ChatColor.YELLOW + 10 + ChatColor.GRAY + ")";
        String message_game = player.getName() + " à quitter la partie";
        String message_end = "";

        if (GameStats.isState(GameStats.LOBBY))
            event.setQuitMessage(message_lobby);

        if (GameStats.isState(GameStats.GAME))
            event.setQuitMessage(message_game);

        if (GameStats.isState(GameStats.END))
            event.setQuitMessage(message_end);
        TeamTempManager.removePlayerFromTeam(player);
    }
}
