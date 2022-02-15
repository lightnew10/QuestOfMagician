package fr.lightnew;

import fr.lightnew.commands.Test;
import fr.lightnew.events.CancelledEvents;
import fr.lightnew.events.Interaction;
import fr.lightnew.events.InventoryInteract;
import fr.lightnew.events.PlayerManager;
import fr.lightnew.game.GameStats;
import fr.lightnew.kits.FilesKits;
import fr.lightnew.tools.CreateFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
/*
Created by @lightnew french developer
*/
public class QuestOfMagician extends JavaPlugin {

    public static QuestOfMagician instance;

    @Override
    public void onEnable() {
        instance=this;
        saveDefaultConfig();
        log("[" + ChatColor.GREEN + "Quest Of Magician" + ChatColor.RESET + "] is Enable");
        //TODO COMMANDS
        getCommand("test").setExecutor(new Test());
        //TODO LISTENERS
        Bukkit.getPluginManager().registerEvents(new CancelledEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerManager(), this);
        Bukkit.getPluginManager().registerEvents(new Interaction(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(), this);
        //TODO DIVERS
        if (getConfig().getInt("Teams-settings.teams-available") < 2 || getConfig().getInt("Teams-settings.teams-available") > 4)
            getConfig().set("Teams-settings.teams-available", 2);
        CreateFiles.sendFolder();
        CreateFiles.teamConfig();
        //kits files
        FilesKits.sendDefaultFolder();
        //game state
        GameStats.setState(GameStats.LOBBY);
    }

    @Override
    public void onDisable() {
        log("[" + ChatColor.RED + "Quest Of Magician" + ChatColor.RESET + "] is Disable");
        CreateFiles.removeFilesInFolder();
    }

    public static void log(String s) { Bukkit.getConsoleSender().sendMessage(s);}
}
