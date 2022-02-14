package fr.lightnew.tools;

import fr.lightnew.QuestOfMagician;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFiles {
    private final static File folderTeams = new File(QuestOfMagician.instance.getDataFolder() + "/Teams");
    private final static File folderPlayerData = new File(QuestOfMagician.instance.getDataFolder() + "/DataFolder");

    public static void sendFolder() {
        if (!folderTeams.exists()) {
            if (folderTeams.mkdir()) {
                QuestOfMagician.log(ChatColor.GREEN + "Folder 'Teams' is successfully create");
            } else QuestOfMagician.log(ChatColor.RED + "ERROR of create 'Team' Folder (contact developer)");
        }
        if (!folderPlayerData.exists()) {
            if (folderPlayerData.mkdir()) {
                QuestOfMagician.log(ChatColor.GREEN + "Folder 'PlayerData' is successfully create");
            } else QuestOfMagician.log(ChatColor.RED + "ERROR of create 'PlayerData' Folder (contact developer)");
        }
    }

    public static void playerFile(Player player) {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/DataFolder", player.getName()+".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("Player-info.uuid", player.getUniqueId());
                config.set("Player-info.kills", 0);
                config.set("Player-info.deaths", 0);
                config.set("Player-info.successfully-quest", 0);
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void teamFile(String name, ArrayList<Player> list) {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/Teams", name+".yml");
        try  {
            if (!file.exists()) {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("Players-in-teams.amount", list.size());
                config.set("Players-in-teams.list", list);
                config.save(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void teamConfig() {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/Teams", "configTeams.yml");
        try {
            if (!file.exists()) {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("Teams.amount", 2 + "#Max 4 teams");
                config.save(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
