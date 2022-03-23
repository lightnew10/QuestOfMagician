package fr.lightnew.game;

import fr.lightnew.QuestOfMagician;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TimerGameSettings {

    public static void timerLobby() {
        int min_player = 5;
        int min_player_set_timer_10 = 6;
        BukkitTask task = new BukkitRunnable() {
            int i = 30;
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() >= min_player) {
                    if (Bukkit.getOnlinePlayers().size() >= min_player_set_timer_10)
                        if (i > 10)
                            i = 10;


                    if (i < 6)
                        Bukkit.broadcastMessage(ChatColor.RED + "La partie commence dans " + ChatColor.RESET + i);

                    if (i == 1) {
                        cancel();
                        GameSettings.launchGame();
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Lancement de la partie !");
                    }

                    i--;
                }
            }
        }.runTaskTimerAsynchronously(QuestOfMagician.instance, 0, 20);
    }

    public static void timerGame() {
        BukkitTask task = new BukkitRunnable() {
            int i = 60*60;
            @Override
            public void run() {
                if (i == 0) {
                    timerEnd();
                    cancel();
                }
                Bukkit.broadcastMessage("timer : " + i);
                i++;
            }
        }.runTaskTimerAsynchronously(QuestOfMagician.instance, 0, 20);
    }

    public static void timerEnd() {
        BukkitTask task = new BukkitRunnable() {
            int i = 30;
            @Override
            public void run() {
                if (i == 0){
                    cancel();
                    //QuestOfMagician.instance.getServer().shutdown();
                }
            }
        }.runTaskTimerAsynchronously(QuestOfMagician.instance, 0, 20);
    }
}
