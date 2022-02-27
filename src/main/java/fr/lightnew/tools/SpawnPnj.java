package fr.lightnew.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class SpawnPnj {

    public static void send(Location loc, Villager.Profession profession, Villager.Type type, Boolean adult, Boolean NoAI, String name) {
        Villager villager = (Villager) Bukkit.getWorld("world").spawnEntity(loc, EntityType.VILLAGER);
        if (profession != null)
            villager.setProfession(Villager.Profession.CLERIC);
        if (adult)
            villager.setAdult();
        if (NoAI)
            villager.setAI(false);
        if (name != null)
            villager.setCustomName(name.replace('&', '§'));
        if (type != null)
            villager.setVillagerType(type);
        villager.setCustomNameVisible(true);
    }
}
