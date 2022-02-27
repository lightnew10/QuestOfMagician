package fr.lightnew.events;

import fr.lightnew.game.GameStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelledEvents implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (GameStats.isState(GameStats.LOBBY) || GameStats.isState(GameStats.END))
            event.setCancelled(true);
        //modify in current development
        if (GameStats.isState(GameStats.GAME))
            event.setCancelled(false);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (GameStats.isState(GameStats.LOBBY) || GameStats.isState(GameStats.END))
            event.setCancelled(true);
        //modify in current development
        if (GameStats.isState(GameStats.GAME))
            event.setCancelled(false);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (GameStats.isState(GameStats.LOBBY) || GameStats.isState(GameStats.END))
            event.setCancelled(true);
        if (GameStats.isState(GameStats.GAME))
            event.setCancelled(false);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (GameStats.isState(GameStats.LOBBY) || GameStats.isState(GameStats.END))
            event.setCancelled(true);
        if (GameStats.isState(GameStats.GAME))
            event.setCancelled(false);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (GameStats.isState(GameStats.LOBBY) || GameStats.isState(GameStats.END))
            event.setCancelled(true);
        if (GameStats.isState(GameStats.GAME)) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void Food(FoodLevelChangeEvent event) { event.setCancelled(true);}

    @EventHandler
    public void WeatherChange(WeatherChangeEvent event) {
        if (!event.toWeatherState())
            return;
        event.setCancelled(true);
        event.getWorld().setWeatherDuration(0);
        event.getWorld().setThundering(false);
    }

    @EventHandler
    public void onMoveInventoryItems(InventoryClickEvent event) {
        int slot = event.getSlot();
        if (slot == 37 || slot == 38 || slot == 39 || slot == 40)
            event.setCancelled(true);
    }
}
