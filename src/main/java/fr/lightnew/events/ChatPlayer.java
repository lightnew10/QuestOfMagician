package fr.lightnew.events;

import fr.lightnew.game.GameStats;
import fr.lightnew.teams.TeamTempManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatPlayer implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        if(GameStats.getCurrentState().equals(GameStats.LOBBY) || GameStats.getCurrentState().equals(GameStats.END)){
            event.setFormat(ChatColor.WHITE + event.getPlayer().getName() + " : " + message);
        } else {
            if (String.valueOf(message.charAt(0)).equals("!")) {
                if (!TeamTempManager.getPrefixPlayer(event.getPlayer()).equals("No Team")) {
                    event.setFormat(ChatColor.WHITE + "[Global] " + TeamTempManager.getPrefixAndColorPlayer(event.getPlayer()) + "" + ChatColor.WHITE + event.getPlayer().getName() + " : " + message.substring(1));
                } else event.setFormat(ChatColor.WHITE + "[Global] " + event.getPlayer().getName() + " : " + message.substring(1));
            } else {
                event.setCancelled(true);

                if(TeamTempManager.list_one_team.contains(event.getPlayer())) {
                    for (Player teamMate : TeamTempManager.list_one_team)
                        teamMate.sendMessage(ChatColor.WHITE + "[" + TeamTempManager.getPrefixColorPlayer(event.getPlayer()) + "Equipe" + ChatColor.WHITE + "] " + TeamTempManager.getPrefixAndColorPlayer(event.getPlayer()) + ChatColor.WHITE + event.getPlayer().getName() + " : " + message);
                } else {
                    for (Player teamMate : TeamTempManager.list_two_team)
                        teamMate.sendMessage(ChatColor.WHITE + "[" + TeamTempManager.getPrefixColorPlayer(event.getPlayer()) + "Equipe" + ChatColor.WHITE + "] " + TeamTempManager.getPrefixAndColorPlayer(event.getPlayer()) + ChatColor.WHITE + event.getPlayer().getName() + " : " + message);
                }
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event){
        Player victim = event.getEntity().getPlayer();
        Player attacker = event.getEntity().getKiller();
        event.setDeathMessage(TeamTempManager.getPrefixAndColorPlayer(victim) + victim.getName() + ChatColor.YELLOW + " à été tuer par " + TeamTempManager.getPrefixAndColorPlayer(attacker) + attacker.getName());
    }

}
