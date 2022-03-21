package fr.lightnew.events;

import fr.lightnew.game.GameSettings;
import fr.lightnew.kits.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Interaction implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (Objects.equals(item, GameSettings.ITEM_CHOOSE_TEAM)) {
                GameSettings.sendInvChooseTeams(player);
            }
            if (Objects.equals(item, GameSettings.ITEM_KITS)) {
                GameSettings.sendInvChooseKits(player);
            }
        }
    }
}
