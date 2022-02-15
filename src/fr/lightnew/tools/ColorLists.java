package fr.lightnew.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class ColorLists {
    public static List<String> color(List<String> m) {
        List<String> result = m;
        Bukkit.getOnlinePlayers().forEach(p -> {
            for (int i = 0; i< m.size(); i++)
                result.set(i, ChatColor.translateAlternateColorCodes('&', m.get(i)));
        });
        return result;
    }
}
