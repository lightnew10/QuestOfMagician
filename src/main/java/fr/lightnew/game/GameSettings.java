package fr.lightnew.game;

import fr.lightnew.teams.TeamManager;
import fr.lightnew.teams.TeamTempManager;
import fr.lightnew.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GameSettings {

    public static ItemStack TEAMS = ItemBuilder.create(Material.WHITE_WOOL, 1, ChatColor.YELLOW + "Choisi ton équipe");
    public static ItemStack KITS = ItemBuilder.create(Material.CHEST, 1, ChatColor.YELLOW + "Choisi ton kit");

    public static void getInvTeamGUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 3 * 9, ChatColor.YELLOW + "Choix de l'équipe");

        ItemStack TEAM_AQUA = ItemBuilder.create(Material.CYAN_WOOL, 1, ChatColor.AQUA + "Cyan");
        ItemStack TEAM_RED = ItemBuilder.create(Material.RED_WOOL, 1, ChatColor.RED + "Rouge");

        List<String> loreAqua = new ArrayList<>();
        List<String> loreRed = new ArrayList<>();

        ItemMeta metaAQUA = TEAM_AQUA.getItemMeta();
        ItemMeta metaRED = TEAM_RED.getItemMeta();

        if(TeamTempManager.list_one_team.contains(player)){
            metaAQUA.addEnchant(Enchantment.DURABILITY ,1, true);
            metaRED.removeEnchant(Enchantment.DURABILITY);
            metaAQUA.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else if(TeamTempManager.list_two_team.contains(player)){
            metaRED.addEnchant(Enchantment.DURABILITY ,1, true);
            metaAQUA.removeEnchant(Enchantment.DURABILITY);
            metaRED.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        loreAqua.clear();
        loreRed.clear();

        loreAqua.add(ChatColor.YELLOW + "Equipe:");
        loreAqua.add(ChatColor.LIGHT_PURPLE + "");
        loreRed.add(ChatColor.YELLOW + "Equipe:");
        loreRed.add(ChatColor.LIGHT_PURPLE + "");

        if(TeamTempManager.list_one_team.size() == 0)
            loreAqua.add(ChatColor.GRAY + "- Vide");
        if(TeamTempManager.list_two_team.size() == 0)
            loreRed.add(ChatColor.GRAY + "- Vide");

        for(Player players : TeamTempManager.list_one_team)
            loreAqua.add(ChatColor.GRAY + "- " + ChatColor.AQUA + "AQUA " + ChatColor.WHITE + players.getName());
        for(Player players : TeamTempManager.list_two_team)
            loreRed.add(ChatColor.GRAY + "- " + ChatColor.RED + "RED " + ChatColor.WHITE + players.getName());

        loreRed.add(ChatColor.DARK_GREEN + "");
        loreAqua.add(ChatColor.DARK_GREEN + "");

        metaAQUA.setLore(loreAqua);
        metaRED.setLore(loreRed);

        TEAM_AQUA.setItemMeta(metaAQUA);
        TEAM_RED.setItemMeta(metaRED);

        inv.setItem(12, TEAM_AQUA);
        inv.setItem(14, TEAM_RED);
        player.openInventory(inv);

    }

    /*GameConfig*/
    public static void launchGame() {
        TimerGameSettings.timerGame();
        for (Player player : Bukkit.getOnlinePlayers())
            TeamManager.setPlayerInTeams(player);
        //tp players
        //send scoreboard
        //start random quest
    }

    public static void launchEnd() {
        TimerGameSettings.timerEnd();
        //tp player
        //send scoreboard
        //prepare stop server
    }

}
