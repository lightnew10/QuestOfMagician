package fr.lightnew.kits;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.tools.ColorLists;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesKits {
    public static File folder = new File(QuestOfMagician.instance.getDataFolder() + "/kits");

    public static void sendDefaultFolder() {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                QuestOfMagician.log("Folder 'kits' is successfully create");
                defaultFile();
            } else QuestOfMagician.log(ChatColor.RED + folder.getName() + " ERROR");
        }
    }

    public static void defaultFile() {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/kits", "defaultKit.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                //set default format for create kit
                config.set("kit.gui.item.material", "IRON_SWORD");
                config.set("kit.gui.item.name", "&eName Of Kit");
                config.set("kit.gui.item.slot", 10);
                config.set("kit.gui.item.lore", new ArrayList<String>());

                List<String> eff = new ArrayList<>();
                eff.add("ABSORPTION");
                config.set("kit.player.effect.name", eff);
                List<Integer> dur = new ArrayList<>();
                dur.add(9999);
                config.set("kit.player.effect.duration", dur);
                List<Integer> am = new ArrayList<>();
                am.add(1);
                config.set("kit.player.effect.amplifier", am);

                config.set("kit.items.sword.material", "IRON_SWORD");
                config.set("kit.items.sword.name", "&eKit 1");
                config.set("kit.items.sword.amount", 1);
                config.set("kit.items.sword.slot", 1);
                config.set("kit.items.sword.lores", new ArrayList<String>());
                config.set("kit.items.sword.enchantments", new ArrayList<String>());
                config.set("kit.items.sword.enchantmentLevels", new ArrayList<Integer>());
                config.set("kit.items.sword.colorLeather", 0);
                config.set("kit.items.sword.unbreakable", false);
                config.set("kit.items.sword.glowing", false);

                config.set("kit.items.leather.material", "LEATHER_BOOTS");
                config.set("kit.items.leather.name", "&cLeather stuff");
                config.set("kit.items.leather.amount", 1);
                config.set("kit.items.leather.slot", 2);
                config.set("kit.items.leather.lores", new ArrayList<String>());
                config.set("kit.items.leather.enchantments", new ArrayList<String>());
                config.set("kit.items.leather.enchantmentLevels", new ArrayList<Integer>());
                config.set("kit.items.leather.colorLeather", 3);
                config.set("kit.items.leather.unbreakable", true);
                config.set("kit.items.leather.glowing", true);

                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //example for search in file [THIS IS A EXAMPLE PRESET !]
    public static List<ItemStack> getItemsInFileKits() {
        ItemStack item;
        ItemMeta meta;
        YamlConfiguration config;
        List<ItemStack> result = null;

        File[] files = folder.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                //parameter file
                config = YamlConfiguration.loadConfiguration(file);
                ConfigurationSection section = config.getConfigurationSection("kit.items");
                FileConfiguration fc = config;
                //verify
                if (section != null) {
                    //search in file
                    for (String key : section.getKeys(false)) {

                        //create parameter for create item
                        String material = fc.getString("kit.items." + key + ".material");
                        int amount = fc.getInt("kit.items." + key + ".amount");
                        String name = fc.getString("kit.items." + key + ".name");
                        List<String> lores = fc.getStringList("kit.items." + key + ".lores");
                        String[] enchantments = fc.getStringList("kit.items" + key + ".enchantments").toArray(new String[0]);
                        String[] enchantmentLevels = fc.getStringList("kit.items" + key + ".enchantmentLevels").toArray(new String[0]);
                        int color = fc.getInt("kit.items" + key + ".colorLeather");
                        boolean unbreakable = fc.getBoolean("kit.items" + key + ".unbreakable");
                        boolean glowing = fc.getBoolean("kit.items" + key + ".glowing");
                        lores = ColorLists.color(lores);
                        //create item

                        if (Material.getMaterial(material) != null) {
                            if (amount < 1)
                                item = new ItemStack(Material.getMaterial(material), 1);
                            else
                                item = new ItemStack(Material.getMaterial(material), amount);
                        } else item = new ItemStack(Material.STONE, 1);

                        meta = item.getItemMeta();

                        if (lores.size() > 0)
                            meta.setLore(lores);
                        if (name != null)
                            meta.setDisplayName(name);

                        meta.setUnbreakable(unbreakable);

                        if (glowing) {
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }

                        if (enchantments != null && enchantmentLevels != null && enchantments.length == enchantmentLevels.length) {
                            for (int i = 0; i < enchantments.length; i++)
                                item.addEnchantment(Enchantment.getByName(enchantments[i]), Integer.parseInt(enchantmentLevels[i]));
                        }

                        //parameter leather stuff soon
                        if (Material.getMaterial(material).equals(Material.LEATHER_BOOTS) || Material.getMaterial(material).equals(Material.LEATHER_CHESTPLATE) || Material.getMaterial(material).equals(Material.LEATHER_LEGGINGS) || Material.getMaterial(material).equals(Material.LEATHER_HELMET)) {
                            LeatherArmorMeta metaL = (LeatherArmorMeta) item.getItemMeta();
                            metaL.setColor(Color.fromBGR(color));
                            item.setItemMeta(metaL);
                        }

                        item.setItemMeta(meta);
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }
}
