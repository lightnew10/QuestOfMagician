package fr.lightnew.events;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.game.GameSettings;
import fr.lightnew.teams.TeamManager;
import fr.lightnew.teams.TeamTempManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryInteract implements Listener {
    private final String message_already_in_team = QuestOfMagician.instance.getConfig().getString("GameSettings.message-error.already-in-team");
    private final String message_full_team = QuestOfMagician.instance.getConfig().getString("GameSettings.message-error.team-full");

    @EventHandler
    public void onInvInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item != null) {
            if (Objects.equals(item, GameSettings.ITEM_ONE)) {
                if (TeamManager.getFullTeamOne())
                    if (!TeamTempManager.list_one_team.contains(player))
                        TeamTempManager.setPlayerInTeamOne(player);
                    else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_already_in_team));
                else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_full_team));
            }
            if (Objects.equals(item, GameSettings.ITEM_TWO)) {
                if (TeamManager.getFullTeamTwo())
                    if (!TeamTempManager.list_two_team.contains(player))
                        TeamTempManager.setPlayerInTeamTwo(player);
                    else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_already_in_team));
                else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_full_team));
            }
            if (Objects.equals(item, GameSettings.ITEM_THREE)) {
                if (TeamManager.getFullTeamThree())
                    if (!TeamTempManager.list_three_team.contains(player))
                        TeamTempManager.setPlayerInTeamThree(player);
                    else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_already_in_team));
                else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_full_team));
            }
            if (Objects.equals(item, GameSettings.ITEM_FOUR)) {
                if (TeamManager.getFullTeamFour())
                    if (!TeamTempManager.list_four_team.contains(player))
                        TeamTempManager.setPlayerInTeamFour(player);
                    else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_already_in_team));
                else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_full_team));
            }
        }
    }
}
