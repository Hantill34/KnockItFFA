package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.maps.MapManager;
import net.problemzone.knockit.util.Language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class GameListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        event.getPlayer().sendMessage(Language.JOIN_MESSAGE.getFormattedText());
        KitManager.getInstance().giveKitSelector(event.getPlayer());
        MapManager.MapSpawnpoint spawn = MapManager.getInstance().getCurrentMap();
        Location spawnpoint = spawn.getCoordinates().toLocation(Objects.requireNonNull(Bukkit.getWorld(spawn.getWorld())), (float) spawn.getYaw(), (float) spawn.getPitch());

        event.getPlayer().teleport(spawnpoint);
        event.setJoinMessage(String.format(Language.JOIN.getText(), event.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(String.format(Language.QUIT.getText(), event.getPlayer().getDisplayName()));
        player.getInventory().clear();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
        e.getEntity().getInventory().clear();
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        MapManager.MapSpawnpoint spawn = MapManager.getInstance().getCurrentMap();
        Location spawnpoint = spawn.getCoordinates().toLocation(Objects.requireNonNull(Bukkit.getWorld(spawn.getWorld())), (float) spawn.getYaw(), (float) spawn.getPitch());
        event.setRespawnLocation(spawnpoint);

        KitManager.getInstance().getKitByPlayer(event.getPlayer()).equip(event.getPlayer());
        KitManager.getInstance().giveKitSelector(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getLocation().getY() < MapManager.getInstance().getCurrentMap().getDeathheight()){
            player.setHealth(0);
        }

        if(player.getLocation().getY() < MapManager.getInstance().getCurrentMap().getFightheight()) {
            player.getInventory().remove(Material.CHEST);
        }
    }

}
