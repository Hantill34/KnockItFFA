package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.Enderman;
import net.problemzone.knockit.util.config.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RespawnListener implements Listener {

    public final HashMap<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();



    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        List<ItemStack[]> newInventory = new ArrayList<>();
        newInventory.add(player.getInventory().getArmorContents());
        newInventory.add(player.getInventory().getContents());

        ItemStack[] newStack =  newInventory.toArray(new ItemStack[newInventory.size()]);
        inv.put(player.getName(), newStack);
        e.getDrops().removeAll(newInventory);

        player.getInventory().clear();
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        MapManager.MapSpawnpoint spawn = MapManager.getInstance().getCurrentMap();
        Location spawnpoint = spawn.getCoordinates().toLocation(Bukkit.getWorld(spawn.getWorld()), (float) spawn.getYaw(), (float) spawn.getPitch());
        event.setRespawnLocation(spawnpoint);


        ItemStack[]  newInventory = inv.get(event.getPlayer().getName());
        if (inv.containsKey(event.getPlayer().getName()))
            event.getPlayer().getInventory().setArmorContents(newInventory);
        inv.remove(event.getPlayer());

        KitManager.giveKitSelector(event.getPlayer());
    }

}
