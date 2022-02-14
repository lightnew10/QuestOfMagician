package fr.lightnew.teams;

import fr.lightnew.QuestOfMagician;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamTempManager {

    /*
    List of Teams (4 teams max)
    */
    public static ArrayList<Player> list_one_team = new ArrayList<>();
    public static ArrayList<Player> list_two_team = new ArrayList<>();
    public static ArrayList<Player> list_three_team = new ArrayList<>();
    public static ArrayList<Player> list_four_team = new ArrayList<>();

    public static boolean playerInNoTeam(Player player) {
        if (list_one_team.contains(player) || list_two_team.contains(player) || list_three_team.contains(player) || list_four_team.contains(player))
            return false;
        return true;
    }
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
    public static ChatColor getColorTeamOne() {return ChatColor.valueOf(QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-color.one"));}
    public static ChatColor getColorTeamTwo() {return ChatColor.valueOf(QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-color.two"));}
    public static ChatColor getColorTeamThree() {return ChatColor.valueOf(QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-color.three"));}
    public static ChatColor getColorTeamFour() {return ChatColor.valueOf(QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-color.four"));}

    public static String getPrefixTeamOne() {return QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-prefix.one");}
    public static String getPrefixTeamTwo() {return QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-prefix.two");}
    public static String getPrefixTeamThree() {return QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-prefix.three");}
    public static String getPrefixTeamFour() {return QuestOfMagician.instance.getConfig().getString("Teams-settings.teams-prefix.four");}

    public static String getPrefixAndColorPlayer(Player player) {
        String defaultPrefix = "No Team";
        if (list_one_team.contains(player))
            return getColorTeamOne() + getPrefixTeamOne();
        if (list_two_team.contains(player))
            return getColorTeamTwo() + getPrefixTeamTwo();
        if (list_three_team.contains(player))
            return getColorTeamThree() + getPrefixTeamThree();
        if (list_four_team.contains(player))
            return getColorTeamFour() + getPrefixTeamFour();
        return defaultPrefix;
    }
    public static String getPrefixPlayer(Player player) {
        String defaultPrefix = "No Team";
        if (list_one_team.contains(player))
            return getPrefixTeamOne();
        if (list_two_team.contains(player))
            return getPrefixTeamTwo();
        if (list_three_team.contains(player))
            return getPrefixTeamThree();
        if (list_four_team.contains(player))
            return getPrefixTeamFour();
        return defaultPrefix;
    }
    public static ChatColor getPrefixColorPlayer(Player player) {
        ChatColor defaultColor = ChatColor.RESET;
        if (list_one_team.contains(player))
            return getColorTeamOne();
        if (list_two_team.contains(player))
            return getColorTeamTwo();
        if (list_three_team.contains(player))
            return getColorTeamThree();
        if (list_four_team.contains(player))
            return getColorTeamFour();
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
    public static void setPlayerInTeamThree(Player player) {
        removeAllTeam(player);
        list_three_team.add(player);
        player.setPlayerListName(getPrefixAndColorPlayer(player) + " " + player.getName());
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.DESTROY);
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.CREATE);
    }
    public static void setPlayerInTeamFour(Player player) {
        removeAllTeam(player);
        list_four_team.add(player);
        player.setPlayerListName(getPrefixAndColorPlayer(player) + " " + player.getName());
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.DESTROY);
        ChangeNameTag.changePrefixNameAndColor(player, getPrefixColorPlayer(player), getPrefixPlayer(player), ChangeTeamAction.CREATE);
    }
}
