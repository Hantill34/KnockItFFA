package net.problemzone.knockit.killstreak;

import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class playerDeathListener implements Listener {
    private static HashMap<String, Integer> killstreak = new HashMap<>();
    private final Map<Player, Entity> lastDamager = new HashMap<>();
    private static final int[] toBroadcast = new int[]{5, 10, 15, 20};    //Wenn der Spieler diese Anzahl an Kills erreicht, passiert etwas

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent) {
            EntityDamageByBlockEvent damageEvent = (EntityDamageByBlockEvent) event.getEntity().getLastDamageCause();
            if (damageEvent.getCause() == EntityDamageEvent.DamageCause.VOID) {
                Entity killer = lastDamager.get(event.getEntity());
                if (killer != null && player != killer) {
                    if (killstreak.containsKey(killer.getName())) {
                        killstreak.put(killer.getName(), killstreak.get(killer.getName()) + 1);

                    } else {
                        killstreak.put(killer.getName(), 1);
                    }

                    int kills = killstreak.get(killer.getName());
                    for (int index : toBroadcast) {
                        if (kills == index) {
                            killer.sendMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.KILL_STREAK), index));
                            Bukkit.getOnlinePlayers().forEach(current ->
                            {
                                current.sendMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.KILL_STREAK), killer.getName(), index));
                            });
                            break;

                        }
                    }

                    assert killstreak.containsKey(player.getName());
                    killstreak.remove(player.getName());
                }

            }

        }

    }

}