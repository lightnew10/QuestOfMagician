package fr.lightnew.tools;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public abstract class ItemBuilder {
    ItemBuilder items;
    public ItemBuilder(ItemBuilder itemBuilder) {
        this.items=itemBuilder;
    }

    public ItemStack ItemBuilder(Material material, int amount, Enchantment[] enchantments,
                                 int[] enchantmentLevels, String name, String... lores) {
        return create(material, amount, enchantments, enchantmentLevels, name, lores);
    }
    public static ItemStack skull(Material material, int amount, short damage, String name, String... lores){
        ItemStack itemStack;
        ItemMeta itemMeta;
        if (material == null || name == null || lores == null) return null;

        itemStack = new ItemStack(material, amount, damage);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lores));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack create(Material material, int amount, Enchantment[] enchantments,
                                   int[] enchantmentLevels, String name, String... lores) {
        ItemStack itemStack;
        ItemMeta itemMeta;

        if (material == null)
            return null;
        itemStack = new ItemStack(material, amount);
        if (enchantments != null && enchantmentLevels != null && enchantments.length == enchantmentLevels.length) {
            for (int i = 0; i < enchantments.length; i++)
                itemStack.addEnchantment(enchantments[i], enchantmentLevels[i]);
        }
        if (name == null && (lores == null || lores.length < 1))
            return itemStack;
        itemMeta = itemStack.getItemMeta();
        if (name != null)
            itemMeta.setDisplayName(name);
        if (lores != null)
            itemMeta.setLore(Arrays.asList(lores));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack create(Material material, int amount, String name, String... lores) {
        return create(material, amount, null, null, name, lores);
    }

    public static ItemStack create(Material material, int amount) {
        return create(material, amount, null, null, null, null);
    }

    public static ItemStack createLeatherStuff(Material material, int amount, Color color, String name) {
        ItemStack item;
        LeatherArmorMeta meta;

        if (material == null)
            return null;

        item = new ItemStack(material, amount);

        meta = (LeatherArmorMeta) item.getItemMeta();

        if (name != null)
            meta.setDisplayName(name);
        if (color != null)
            meta.setColor(color);

        item.setItemMeta(meta);

        return item;
    }
}
