package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.Angler;
import net.problemzone.knockit.modules.kitmanager.kits.Assassine;
import net.problemzone.knockit.util.Language;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

   /* @EventHandler
    public void hasKitSelector(PlayerInteractEvent event){
        Player player = event.getPlayer();


        for (ItemStack item : player.getInventory().getContents()) {
            int amount = item.getAmount();

            if (item.getType() == Material.CHEST) {
            event.getItem().setAmount(1);
            }
        }
    }
    */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        event.getPlayer().sendMessage(Language.JOIN_MESSAGE.getFormattedText());
        kitManager.giveKitSelector(event.getPlayer());                                                                  //Kiste
        event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
        event.setJoinMessage(String.format(Language.JOIN.getText(), event.getPlayer().getDisplayName()));
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

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(String.format(Language.QUIT.getText(), event.getPlayer().getDisplayName()));
        player.getInventory().clear();
    }

    @EventHandler
    public void onFish(PlayerFishEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String name = meta.getDisplayName();
        if(name.equals(ChatColor.AQUA + "Grabbling Hook"))
        {
            if(event.getState().equals(PlayerFishEvent.State.REEL_IN))
            {
                if(Angler.checkCooldown(event.getPlayer()))
                {
                    Location playerLocation = player.getLocation();
                    Location grapplingLocation = event.getHook().getLocation();
                    Location change = grapplingLocation.subtract(playerLocation);
                    Vector vector = change.toVector();
                    vector.setY(vector.getY() * 0.2);
                    player.setVelocity(vector.multiply(0.5));
                    Angler.setCooldown(event.getPlayer(), 5);
                }
                else {
                    player.sendMessage(Language.GRAPPLER_COOLDOWN.getFormattedText());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getLocation().getY() < 125){
            player.setHealth(0);
        }
    }


}
