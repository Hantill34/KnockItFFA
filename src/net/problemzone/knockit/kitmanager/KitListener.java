package net.problemzone.knockit.kitmanager;

import net.problemzone.knockit.kitmanager.kits.Assassine;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class KitListener implements Listener {

    private final KitManager kitManager;

    public KitListener(KitManager kitManager) {
        this.kitManager = kitManager;
    }

    @EventHandler
    public void onKitChestOpen(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem().getType() == Material.CHEST) {
                e.setCancelled(true);
                kitManager.openInventory(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            if (e.getView().getTitle().equals(ChatColor.YELLOW + "Kitauswahl")) {
                Player p = (Player) e.getWhoClicked();
                Kit kit = kitManager.getKitByName(e.getCurrentItem().getItemMeta().getDisplayName());
                kit.equip(p);
                kitManager.putPlayerInMap(p, kit);
            }
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        kitManager.giveKitSelector(event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Language.getStringFromKeyword(LanguageKeyword.JOIN_MESSAGE));
        kitManager.giveKitSelector(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (event.getEntity().getKiller() != null) {
                if (kitManager.getKitByPlayer(event.getEntity().getKiller()) instanceof Assassine) {
                    Assassine.onKill(event.getEntity().getKiller());
                }
            }
        }
    }

    @EventHandler
    public void onPLayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (kitManager.getKitByPlayer(player) instanceof Assassine) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }
    }
}
