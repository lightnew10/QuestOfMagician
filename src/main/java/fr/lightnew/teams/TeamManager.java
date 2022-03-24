package fr.lightnew.teams;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.tools.CreateFiles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamManager {

    public static Boolean getFullTeamOne() {
        if (TeamTempManager.list_one_team.size() == QuestOfMagician.instance.getConfig().getInt("Teams-settings.teams-size"))
            return true;
        return false;
    }
    public static Boolean getFullTeamTwo() {
        if (TeamTempManager.list_two_team.size() == QuestOfMagician.instance.getConfig().getInt("Teams-settings.teams-size"))
            return true;
        return false;
    }

    //this function is used for set player in no team in 'random' team
    public static void setPlayerInRandomTeam(Player player) {
        if (TeamTempManager.playerInNoTeam(player)) {
            int t = TeamTempManager.size_teams * 2;
            if (Bukkit.getOnlinePlayers().size() < t)
                TeamTempManager.size_teams = Bukkit.getOnlinePlayers().size() / 2;

            if (!TeamTempManager.list_one_team.contains(player) || !TeamTempManager.list_two_team.contains(player)) {
                if (!getFullTeamOne())
                    TeamTempManager.list_one_team.add(player);
                else
                    TeamTempManager.list_two_team.add(player);
            }
        }
    }

    //this function is used to do create file and place config player in team file view 'CreateFile.playerFile'
    public static void setPlayerInTeams(Player player) {
        //verification is effected in this function 'setPlayerInRandomTeam'
        setPlayerInRandomTeam(player);
        //create player temporary file
        CreateFiles.playerFile(player);
    }
}
