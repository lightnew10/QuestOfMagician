package fr.lightnew.commands;

import fr.lightnew.kits.KitManager;
import fr.lightnew.tools.SpawnPnj;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("Faite /test <name>");
                KitManager.sendKit(player);
            }
            if (args.length == 1) {
                String name = args[0];
                SpawnPnj.send(player.getLocation(), Villager.Profession.CLERIC, Villager.Type.PLAINS, true, true, name);
            }
        }
        return false;
    }
}
