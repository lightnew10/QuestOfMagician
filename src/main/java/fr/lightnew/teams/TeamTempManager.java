package fr.lightnew.teams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamTempManager {

    /*
    List of Teams (2 teams max)
    */
    public static ArrayList<Player> list_one_team = new ArrayList<>(); // AQUA
    public static ArrayList<Player> list_two_team = new ArrayList<>(); // RED

    public static boolean playerInNoTeam(Player player) {
        if (list_one_team.contains(player) || list_two_team.contains(player))
            return false;
        return true;
    }
    public static void removeAllTeam(Player player) {
        list_one_team.remove(player);
        list_two_team.remove(player);
    }
    private static void setPlayerInTeamOneList(Player player) {
        removeAllTeam(player);
        list_one_team.add(player);
    }
    private static void setPlayerInTeamTwoList(Player player) {
        removeAllTeam(player);
        list_two_team.add(player);
    }

    /*
    Settings List of Teams
    */
    public static int size_teams = 5;

    /*Color of teams*/
    public static ChatColor getColorTeamOne() {return ChatColor.AQUA;}
    public static ChatColor getColorTeamTwo() {return ChatColor.RED;}

    public static String getPrefixTeamOne() {return "Aqua ";}
    public static String getPrefixTeamTwo() {return "Red ";}

    public static String getPrefixAndColorPlayer(Player player) {
        String defaultPrefix = "No Team";
        if (list_one_team.contains(player))
            return getColorTeamOne() + getPrefixTeamOne();
        if (list_two_team.contains(player))
            return getColorTeamTwo() + getPrefixTeamTwo();
        return defaultPrefix;
    }
    public static String getPrefixPlayer(Player player) {
        String defaultPrefix = "No Team";
        if (list_one_team.contains(player))
            return getPrefixTeamOne();
        if (list_two_team.contains(player))
            return getPrefixTeamTwo();
        return defaultPrefix;
    }
    public static ChatColor getPrefixColorPlayer(Player player) {
        ChatColor defaultColor = ChatColor.RESET;
        if (list_one_team.contains(player))
            return getColorTeamOne();
        if (list_two_team.contains(player))
            return getColorTeamTwo();
        return defaultColor;
    }

    public static void setPlayerInTeamOne(Player player) {
        removeAllTeam(player);
        list_one_team.add(player);
        player.setPlayerListName(getPrefixAndColorPlayer(player) + " " + player.getName());
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.DESTROY);
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.CREATE);
    }
    public static void setPlayerInTeamTwo(Player player) {
        removeAllTeam(player);
        list_two_team.add(player);
        player.setPlayerListName(getPrefixAndColorPlayer(player) + " " + player.getName());
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.DESTROY);
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.CREATE);
    }
}
