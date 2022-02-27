package fr.lightnew.events;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.game.GameSettings;
import fr.lightnew.game.GameStats;
import fr.lightnew.teams.ChangeNameTag;
import fr.lightnew.teams.ChangeTeamAction;
import fr.lightnew.teams.TeamTempManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;

public class PlayerManager implements Listener {
    private int max_players = QuestOfMagician.instance.getConfig().getInt("GameSettings.max-players");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (int i = 0; i < 40; i++)
            player.getInventory().clear(i);

        String message_lobby = QuestOfMagician.instance.getConfig().getString("PlayerManager.join.messages-join.lobby");
        String message_game = QuestOfMagician.instance.getConfig().getString("PlayerManager.join.messages-join.game");
        String message_end = QuestOfMagician.instance.getConfig().getString("PlayerManager.join.messages-join.end");

        if (GameStats.isState(GameStats.LOBBY)) {
            event.setJoinMessage(replaceDefaultString(message_lobby, player));
            GameSettings.sendBaseLobby(player);
        }
        if (GameStats.isState(GameStats.GAME)) {
            event.setJoinMessage(replaceDefaultString(message_game, player));
        }
        if (GameStats.isState(GameStats.END)) {
            event.setJoinMessage(replaceDefaultString(message_end, player));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String message_lobby = QuestOfMagician.instance.getConfig().getString("PlayerManager.quit.messages-quit.lobby");
        String message_game = QuestOfMagician.instance.getConfig().getString("PlayerManager.quit.messages-quit.game");
        String message_end = QuestOfMagician.instance.getConfig().getString("PlayerManager.quit.messages-quit.end");

        if (GameStats.isState(GameStats.LOBBY)) {
            event.setQuitMessage(replaceDefaultString(message_lobby, player));
            ChangeNameTag.changePrefixNameAndColor(player, ChatColor.RESET, "", ChangeTeamAction.DESTROY);
        }
        if (GameStats.isState(GameStats.GAME)) {
            event.setQuitMessage(replaceDefaultString(message_game, player));
        }
        if (GameStats.isState(GameStats.END)) {
            event.setQuitMessage(replaceDefaultString(message_end, player));
        }
    }
    private String replaceDefaultString(String s, Player player) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        return s.replace('&', '§').replace("%players%", String.valueOf(players.size()))
                .replace("%max_players%", String.valueOf(max_players)).replace("%prefix%", TeamTempManager.getPrefixPlayer(player)).replace("%player%", player.getName());
    }
}
