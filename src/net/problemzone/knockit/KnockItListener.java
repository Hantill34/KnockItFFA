package net.problemzone.knockit;

import com.mysql.jdbc.ServerPreparedStatement;
import net.problemzone.knockit.scoreboard.KnockItScoreboard;
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


        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();

        assert stockItemMeta != null;
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);
        p.getInventory().clear();
        p.getInventory().addItem(stock);
        p.getInventory().addItem(chest);

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


        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();

        assert stockItemMeta != null;
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);
        p.getInventory().clear();
        p.getInventory().addItem(stock);
        p.getInventory().addItem(chest);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player p = (Player) event.getPlayer();

    }


}

