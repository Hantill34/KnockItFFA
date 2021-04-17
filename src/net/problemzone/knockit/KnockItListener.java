package net.problemzone.knockit;

import net.problemzone.knockit.scoreboard.KnockItScoreboard;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class KnockItListener implements Listener{

    private final KnockItScoreboard knockItScoreboard;
    private final Map<Player, Entity> lastDamager = new HashMap<>();

    public KnockItListener(KnockItScoreboard knockItScoreboard) {
        this.knockItScoreboard = knockItScoreboard;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestItemMeta = chest.getItemMeta();

        assert chestItemMeta != null;
        chestItemMeta.setDisplayName(ChatColor.YELLOW + "Kitauswahl");
        chest.setItemMeta(chestItemMeta);

        p.getInventory().clear();
        p.getInventory().addItem(chest);
        p.sendMessage(Language.format(Language.getStringFromKeyword(LanguageKeyword.JOIN_MESSAGE)));

        knockItScoreboard.newPlayerDeath(p);
        knockItScoreboard.newPlayerKill(p);

        knockItScoreboard.setScoreboard(p);
        knockItScoreboard.updateScoreboard();
    }

    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                if (e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent cause = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
                    lastDamager.put((Player)e.getEntity(), cause.getDamager());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if(event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent){
            EntityDamageByBlockEvent damageEvent = (EntityDamageByBlockEvent) event.getEntity().getLastDamageCause();
            if(damageEvent.getCause() == EntityDamageEvent.DamageCause.VOID){
                Entity entity = lastDamager.get(event.getEntity());
                if(entity instanceof Player){
                    knockItScoreboard.increaseKillCounter((Player) entity);
                }
            }
        } else if (event.getEntity().getKiller() != null){
            knockItScoreboard.increaseKillCounter(event.getEntity().getKiller());
        }

        knockItScoreboard.increaseDeathCounter(event.getEntity());
        knockItScoreboard.updateScoreboard();

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        Player p = event.getPlayer();

        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestItemMeta = chest.getItemMeta();

        assert chestItemMeta != null;
        chestItemMeta.setDisplayName(ChatColor.YELLOW + "Kitauswahl");
        chest.setItemMeta(chestItemMeta);


        p.getInventory().clear();
        p.getInventory().addItem(chest);
    }


}

