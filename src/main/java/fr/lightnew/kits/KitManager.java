package fr.lightnew.kits;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.tools.ColorLists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

public class KitManager {
    public static WeakHashMap<Player, String> listKits = new WeakHashMap<>();

    public static String getKitInList(Player player) {
        return listKits.get(player);
    }

    public static void sendKitInv(Player player){
        Inventory inv = Bukkit.createInventory(player, 6*9, ChatColor.YELLOW + "Choisi ton kit");

        //get items in files
        ItemStack item;
        ItemMeta meta;
        YamlConfiguration config;

        File[] files = FilesKits.folder.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                //parameter file
                config = YamlConfiguration.loadConfiguration(file);
                ConfigurationSection section = config.getConfigurationSection("kit.items");
                FileConfiguration fc = config;
                //verify
                if (section != null) {
                    //search in file

                    //create parameter for create item
                    String material = fc.getString("kit.gui.item.material");
                    int slot = fc.getInt("kit.gui.item.slot");
                    String name = fc.getString("kit.gui.item.name");
                    List<String> lore = fc.getStringList("kit.gui.item.lore");

                    item = new ItemStack(Material.getMaterial(material), 1);
                    meta = item.getItemMeta();
                    meta.setDisplayName(name.replace('&', '§'));
                    meta.setLore(ColorLists.color(lore));
                    item.setItemMeta(meta);
                    //set items
                    inv.setItem(slot, item);
                }
            }
        }

        player.openInventory(inv);
    }

    private static File getFile(String s) {
        File result = null;
        for (File file : FilesKits.folder.listFiles()) {
            if (file.getName().equals(s)) {
                result =  new File(QuestOfMagician.instance.getDataFolder() + "/kits", file.getName());
            }
        }
        return result;
    }

    public static void sendKit(Player player) {
        if (getFile(getKitInList(player)).exists() || listKits.containsKey(player)) {
            for (int i = 0; i < 40; i++)
                player.getInventory().clear(i);
            File file = getFile(getKitInList(player));
            ItemStack item;
            ItemMeta meta;
            YamlConfiguration config;

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
                    String name = fc.getString("kit.items." + key + ".name").replace('&', '§');
                    List<String> lores = fc.getStringList("kit.items." + key + ".lores");
                    String[] enchantments = fc.getStringList("kit.items" + key + ".enchantments").toArray(new String[0]);
                    String[] enchantmentLevels = fc.getStringList("kit.items" + key + ".enchantmentLevels").toArray(new String[0]);
                    int color = fc.getInt("kit.items" + key + ".colorLeather");
                    boolean unbreakable = fc.getBoolean("kit.items" + key + ".unbreakable");
                    boolean glowing = fc.getBoolean("kit.items" + key + ".glowing");
                    List<String> effect = fc.getStringList("kit.items" + key + ".effect");
                    List<Integer> duration = fc.getIntegerList("kit.items" + key + ".duration");
                    List<Integer> amplifier= fc.getIntegerList("kit.items" + key + ".amplifier");
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
                        metaL.setUnbreakable(unbreakable);
                        metaL.setLore(lores);
                        if (glowing) {
                            metaL.addEnchant(Enchantment.BINDING_CURSE, 1, false);
                            metaL.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }
                        item.setItemMeta(metaL);
                    }

                    item.setItemMeta(meta);

                    player.getInventory().addItem(item);
                    if (effect != null && duration != null && amplifier != null) {
                        for (String s : effect) {
                            for (int i : duration) {
                                for (int ii : amplifier) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(s), i, ii));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setDefautlKit(Player player) {
        listKits.put(player, QuestOfMagician.instance.getConfig().getString("default-kit"));
    }
}
