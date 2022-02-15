package fr.lightnew.kits;

import com.sun.istack.internal.NotNull;
import fr.lightnew.QuestOfMagician;
import fr.lightnew.tools.ColorLists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesKits {
    private static File folder = new File(QuestOfMagician.instance.getDataFolder() + "/kits");
    private static File fileConfig = new File(QuestOfMagician.instance.getDataFolder() + "/kits", "configKits.yml");
    private static ArrayList<String> list = new ArrayList<>();

    public static void sendDefaultFolder() {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                QuestOfMagician.log(ChatColor.GREEN + folder.getName() + " is loaded");
                defaultFile();
            } else QuestOfMagician.log(ChatColor.RED + folder.getName() + " ERROR");
        }
        if (!fileConfig.exists()) {
            try {
                fileConfig.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);
                list.add("nameKit");
                list.add("apple");
                config.set("register-new-kit.names.", list);
                config.set("register-new-kit-info", "go to -> #how to register a kit? go to the Kits.yml file and name this kits.<name> | copy and paste this name into the list of names");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void defaultFile() {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/kits", "nameKit.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                //set default format for create kit
                config.set("kit.items.sword.material", "IRON_SWORD");
                config.set("kits.items.sword.name", "&eKit 1");
                config.set("kits.items.sword.unbreakable", false);

                config.set("kits.items.apple.material", "APPLE");
                config.set("kits.items.apple.name", "&6Apple");
                config.set("kits.items.apple.unbreakable", false);

                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static YamlConfiguration getKitFile() {
        File file = new File(QuestOfMagician.instance.getDataFolder() + "/kits", "kits.yml");
        if (file.exists())
            return YamlConfiguration.loadConfiguration(file);
        return null;
    }
    private static YamlConfiguration getConfigKit() {
        if (fileConfig.exists()) {
            return YamlConfiguration.loadConfiguration(fileConfig);
        }
        return null;
    }

    public static ItemStack getItemsInFileKits() {
        ItemStack item;
        ItemMeta meta;
        YamlConfiguration config;

        File[] files = folder.listFiles();
        for (File file : files) {
            config = YamlConfiguration.loadConfiguration(file);
            ConfigurationSection section = config.getConfigurationSection("kit.items");
            FileConfiguration fc = config;
            for (String key : section.getKeys(false)) {

                String material = fc.getString("kit.items." + key + ".material");
                int amount = fc.getInt("kit.items." + key + ".amount");
                String name = fc.getString("kit.items." + key + ".name");
                List<String> lores = fc.getStringList("kit.items." + key + ".lores");
                lores = ColorLists.color(lores);

                item = new ItemStack(Material.getMaterial(material), amount);
                meta = item.getItemMeta();

                meta.setLore(lores);
                meta.setDisplayName(name);
            }
        }

        return null;
    }
        /*
        ConfigurationSection section = folder.getConfigurationSection("gui-shop-"+line_config+".items");
        FileConfiguration fc = folder;
        for (String key : section.getKeys(false)) {

            int amount = fc.getInt(line_config_modif + "." + key + ".amount");
            List<Integer> slot = fc.getIntegerList(line_config_modif + "." + key + ".slot");
            int data = fc.getInt(line_config_modif + "." + key + ".data");
            String name = fc.getString(line_config_modif + "." + key + ".name");
            String material = fc.getString(line_config_modif + "." + key + ".material");
            List<String> lores = fc.getStringList(line_config_modif + "." + key + ".lores");
            lores = ColorList.send(lores);

            ItemStack item = new ItemStack(Material.getMaterial(material), amount);
            MaterialData materialData = new MaterialData(data);
            item.setData(materialData);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(name.replace('&', '§'));
            meta.setLore(lores);
            if (fc.getBoolean(line_config_modif + "." + key + ".glowing")) {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }

            item.setItemMeta(meta);*/

    /*
    * Kits:
    *   combatant:
    *       name: "combatant"
    *       items:
    *           sword:
    *               material: IRON_SWORD
    *               name: "&cCombatant"
    *               unbreakable:  false
    *           apple:
    *               material: apple
    *               name: "pomme"
    *               unbreakable: false
    * ...
    * */
}
