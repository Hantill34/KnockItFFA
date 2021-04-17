package net.problemzone.knockit.killstreak;

import net.problemzone.knockit.scoreboard.KnockItScoreboard;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class playerDeathListener implements Listener {
    private static HashMap<String, Integer> killstreak = new HashMap<>();
    private final Map<Player, Entity> lastDamager = new HashMap<>();
    private static final int [] toBroadcast = new int[] {5, 10, 15, 20};    //Wenn der Spieler diese Anzahl an Kills erreicht, passiert etwas

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
       Player player = event.getEntity();

        if(event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent){
            EntityDamageByBlockEvent damageEvent = (EntityDamageByBlockEvent) event.getEntity().getLastDamageCause();
            if(damageEvent.getCause() == EntityDamageEvent.DamageCause.VOID){
                Entity killer = lastDamager.get(event.getEntity());
                if(killer != null && player != killer)
                {
                    if(killstreak.containsKey(killer.getName()))
                    {
                        killstreak.put(killer.getName(), killstreak.get(killer.getName()) + 1);

                    }else
                    {
                       killstreak.put(killer.getName(), 1);
                    }

                    int kills = killstreak.get(killer.getName());
                    for(int index : toBroadcast)
                    {
                        if (kills == index)
                        {
                            killer.sendMessage(Language.format(Language.getStringFromKeyword(LanguageKeyword.KILL_STREAK)));
                            Bukkit.getOnlinePlayers().forEach(current ->
                            {
                                current.sendMessage(Language.format(Language.getStringFromKeyword(LanguageKeyword.GLOBAL_KILLSTREAK)));
                            });
                        }
                    }

                    assert killstreak.containsKey(player.getName());
                    killstreak.remove(player.getName());
                }

            }

        }

    }

}