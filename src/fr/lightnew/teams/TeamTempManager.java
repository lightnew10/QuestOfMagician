package fr.lightnew.teams;

import fr.lightnew.QuestOfMagician;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamTempManager {

    /*
    List of Teams (4 teams max)
    */
    private static ArrayList<Player> list_one_team = new ArrayList<>();
    private static ArrayList<Player> list_two_team = new ArrayList<>();
    private static ArrayList<Player> list_three_team = new ArrayList<>();
    private static ArrayList<Player> list_four_team = new ArrayList<>();

    public static void removeAllTeam(Player player) {
        list_one_team.remove(player);
        list_two_team.remove(player);
        list_three_team.remove(player);
        list_four_team.remove(player);
    }
    private static void setPlayerInTeamOneList(Player player) {
        removeAllTeam(player);
        list_one_team.add(player);
    }
    private static void setPlayerInTeamTwoList(Player player) {
        removeAllTeam(player);
        list_two_team.add(player);
    }
    private static void setPlayerInTeamThreeList(Player player) {
        removeAllTeam(player);
        list_three_team.add(player);
    }
    private static void setPlayerInTeamFourList(Player player) {
        removeAllTeam(player);
        list_four_team.add(player);
    }

    /*
    Settings List of Teams
    */
    public static int size_teams = QuestOfMagician.instance.getConfig().getInt("Teams-settings.teams-size");

    /*Color of teams*/
    public static ChatColor setColorTeamOne() {return ChatColor.RESET;}
    public static ChatColor setColorTeamTwo() {return ChatColor.RESET;}
    public static ChatColor setColorTeamThree() {return ChatColor.RESET;}
    public static ChatColor setColorTeamFour() {return ChatColor.RESET;}
}
