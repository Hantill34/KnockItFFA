package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.Angler;
import net.problemzone.knockit.modules.kitmanager.kits.Assassine;
import net.problemzone.knockit.modules.kitmanager.kits.Enderman;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.config.MapManager;
import org.bukkit.Bukkit;
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

    @EventHandler
    public void hasKitSelector(PlayerInteractEvent event){
        Player player = event.getPlayer();

        for (ItemStack item : player.getInventory()){
            if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Kitauswahl")){
                if(item.getAmount() > 1){
                    item.setAmount(item.getAmount() - 1);
                    player.updateInventory();
                }
            }
        }
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        event.getPlayer().sendMessage(Language.JOIN_MESSAGE.getFormattedText());
        kitManager.giveKitSelector(event.getPlayer());
        MapManager.MapSpawnpoint spawn = MapManager.getInstance().getCurrentMap();
        Location spawnpoint = spawn.getCoordinates().toLocation(Bukkit.getWorld(spawn.getWorld()), (float) spawn.getYaw(), (float) spawn.getPitch());

        event.getPlayer().teleport(spawnpoint);
        event.setJoinMessage(String.format(Language.JOIN.getText(), event.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();

        if (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (event.getEntity().getKiller() != null) {
                if (kitManager.getKitByPlayer(event.getEntity().getKiller()) instanceof Assassine) {
                    Assassine.onKill(event.getEntity().getKiller());
                }
                if(kitManager.getKitByPlayer(event.getEntity().getKiller()) instanceof Enderman){
                    Enderman.onKill(event.getEntity().getKiller());
                }
            }
        }

        player.spigot().respawn();
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        if(kitManager.getKitByPlayer(event.getPlayer()) instanceof Enderman){

            Enderman.hasPearl(event.getPlayer());
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
                    vector.setY(vector.getY() * 0.16);
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


        if(player.getLocation().getY() < MapManager.getInstance().getCurrentMap().getDeathheight()){
            player.setHealth(0);
        }

        if(player.getLocation().getY() < MapManager.getInstance().getCurrentMap().getFightheight()){
            for (ItemStack item : event.getPlayer().getInventory()){
                if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Kitauswahl")) {

                    item.setAmount(0);
                }
            }
        }

    }

}
