package fr.lightnew.tools;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.teams.TeamTempManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CreateFiles {
    private final static File folderPlayerData = new File(QuestOfMagician.instance.getDataFolder() + "/Caches");

    public static void sendFolder() {
        if (!folderPlayerData.exists()) {
            if (folderPlayerData.mkdir()) {
                QuestOfMagician.log(ChatColor.GREEN + "Folder 'Caches' is successfully create");
            } else QuestOfMagician.log(ChatColor.RED + "ERROR of create 'PlayerData' Folder (contact developer)");
        }
    }

    public static void playerFile(Player player) {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/Caches", player.getName()+".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("Player-info.uuid", player.getUniqueId());
                config.set("Player-info.Team", TeamTempManager.getPrefixPlayer(player));
                config.set("Player-info.kills", 0);
                config.set("Player-info.deaths", 0);
                config.set("Player-info.successfully-quest", 0);
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeFilesInFolder() {
        if (folderPlayerData.exists()) {
            if (folderPlayerData.length() > 0) {
                for (File files : folderPlayerData.listFiles()) {
                    if (files.delete()) {
                        QuestOfMagician.log(ChatColor.RED + "" + folderPlayerData.getName() + " is deleted");
                    }
                }
            }
        }
    }
}
