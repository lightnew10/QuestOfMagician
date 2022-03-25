package fr.lightnew.commands;

import fr.lightnew.QuestOfMagician;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class SpawnTeams implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.YELLOW + "Utilisation : /setspawn <choose>");
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("spawn")) {
                        QuestOfMagician.instance.getConfig().set("location.spawn", player.getLocation());
                        QuestOfMagician.instance.saveConfig();
                        player.sendMessage(ChatColor.YELLOW + "Vous avez poser le point de " + ChatColor.RESET + "SPAWN");
                    }

                    if (args[0].equalsIgnoreCase("aqua")) {
                        QuestOfMagician.instance.getConfig().set("location.aqua", player.getLocation());
                        QuestOfMagician.instance.saveConfig();
                        player.sendMessage(ChatColor.YELLOW + "Vous avez poser le point de spawn de l'équipe " + ChatColor.AQUA + "AQUA");
                    }

                    if (args[0].equalsIgnoreCase("red")) {
                        QuestOfMagician.instance.getConfig().set("location.red", player.getLocation());
                        QuestOfMagician.instance.saveConfig();
                        player.sendMessage(ChatColor.YELLOW + "Vous avez poser le point de spawn de l'équipe " + ChatColor.RED + "RED");
                    }

                    if (args[0].equalsIgnoreCase("end")) {
                        QuestOfMagician.instance.getConfig().set("location.red", player.getLocation());
                        QuestOfMagician.instance.saveConfig();
                        player.sendMessage(ChatColor.YELLOW + "Vous avez poser le point de spawn de l'équipe " + ChatColor.RESET + "END");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        List<String> list = Arrays.asList("spawn", "end", "aqua", "red");
        String input = args[0].toLowerCase();

        List<String> completion = null;
        for (String s : list) {
            if (s.startsWith(input)) {
                if (completion == null) {
                    completion = new ArrayList<>();
                }
                completion.add(s);
            }
        }
        if (completion != null)
            Collections.sort(completion);
        return completion;
    }

    public static Location getLocationSpawn() {return QuestOfMagician.instance.getConfig().getLocation("location.spawn");}

    public static Location getLocationAqua() {return QuestOfMagician.instance.getConfig().getLocation("location.aqua");}

    public static Location getLocationRed() {return QuestOfMagician.instance.getConfig().getLocation("location.red");}

    public static Location getLocationEnd() {return QuestOfMagician.instance.getConfig().getLocation("location.end");}
}
