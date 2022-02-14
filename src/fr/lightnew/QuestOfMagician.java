package fr.lightnew;

import fr.lightnew.commands.Test;
import fr.lightnew.tools.CreateFiles;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
/*
Created by @lightnew
*/
public class QuestOfMagician extends JavaPlugin {

    public static QuestOfMagician instance;

    @Override
    public void onEnable() {
        instance=this;
        saveDefaultConfig();
        //TODO COMMANDS
        getCommand("test").setExecutor(new Test());
        //TODO LISTENERS
        //TODO DIVERS
        CreateFiles.sendFolder();
        CreateFiles.teamConfig();
    }

    @Override
    public void onDisable() {

    }

    public static void log(String s) { Bukkit.getConsoleSender().sendMessage(s);}
}
