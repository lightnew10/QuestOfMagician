package fr.lightnew.game;

import fr.lightnew.QuestOfMagician;
import fr.lightnew.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GameSettings {

    private static FileConfiguration config = QuestOfMagician.instance.getConfig();

    //GUI CHOOSE TEAM
    public static ItemStack ITEM_ONE = ItemBuilder.create(Material.getMaterial(config.getString("Teams-settings.Teams-settings.item-in-gui-teams.one.material")), config.getInt("Teams-settings.item-in-gui-teams.one.amount"), config.getString("Teams-settings.item-in-gui-teams.one.name").replace('&', '§'));
    private static int slot_one = config.getInt("Teams-settings.item-in-gui-teams.one.slot");
    public static ItemStack ITEM_TWO = ItemBuilder.create(Material.getMaterial(config.getString("Teams-settings.item-in-gui-teams.two.material")), config.getInt("Teams-settings.item-in-gui-teams.two.amount"), config.getString("Teams-settings.item-in-gui-teams.two.name").replace('&', '§'));
    private static int slot_two = config.getInt("Teams-settings.item-in-gui-teams.two.slot");
    public static ItemStack ITEM_THREE = ItemBuilder.create(Material.getMaterial(config.getString("Teams-settings.item-in-gui-teams.three.material")), config.getInt("Teams-settings.item-in-gui-teams.three.amount"), config.getString("Teams-settings.item-in-gui-teams.three.name").replace('&', '§'));
    private static int slot_three = config.getInt("Teams-settings.item-in-gui-teams.three.slot");
    public static ItemStack ITEM_FOUR = ItemBuilder.create(Material.getMaterial(config.getString("Teams-settings.item-in-gui-teams.four.material")), config.getInt("Teams-settings.item-in-gui-teams.four.amount"), config.getString("Teams-settings.item-in-gui-teams.four.name").replace('&', '§'));
    private static int slot_four = config.getInt("Teams-settings.item-in-gui-teams.four.slot");
    //INV PLAYER BASE
    public static ItemStack ITEM_CHOOSE_TEAM = ItemBuilder.create(Material.getMaterial(config.getString("GameSettings.item-choose-team.material")), config.getInt("GameSettings.item-choose-team.amount"), config.getString("GameSettings.item-choose-team.name").replace('&', '§'));
    private static int slot_choose_team = config.getInt("GameSettings.item-choose-team.slot");
    public static ItemStack ITEM_KITS = ItemBuilder.create(Material.getMaterial(config.getString("GameSettings.item-choose-kit.material")), config.getInt("GameSettings.item-choose-kit.amount"), config.getString("GameSettings.item-choose-kit.name").replace('&', '§'));
    private static int slot_choose_kit = config.getInt("GameSettings.item-choose-kit.slot");

    public static void sendInvChooseTeams(Player player) {
        Inventory inv;
        if (config.getInt("Teams-settings.item-in-gui-teams.inventory.size") <=6)
            inv = Bukkit.createInventory(player, config.getInt("Teams-settings.item-in-gui-teams.inventory.size")*9, config.getString("Teams-settings.item-in-gui-teams.inventory.size").replace('&', '§'));
        else inv = Bukkit.createInventory(player, 3*9, config.getString("Teams-settings.item-in-gui-teams.inventory.size").replace('&', '§'));
        inv.clear();
        inv.setItem(slot_one, ITEM_ONE);
        inv.setItem(slot_two, ITEM_TWO);
        inv.setItem(slot_three, ITEM_THREE);
        inv.setItem(slot_four, ITEM_FOUR);
        player.openInventory(inv);
    }
    public static void sendInvChooseKits(Player player) {
        Inventory inv = Bukkit.createInventory(player, 3*9, "kit");
    }
    public static void sendBaseLobby(Player player) {
        player.getInventory().setItem(slot_choose_team, ITEM_CHOOSE_TEAM);
        player.getInventory().setItem(slot_choose_kit, ITEM_KITS);
    }
}
