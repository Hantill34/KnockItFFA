package net.problemzone.knockit.modules.kitmanager;

import org.bukkit.Material;
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
        ItemStack[]  newInventory= inv.get(event.getPlayer().getName());
        if (inv.containsKey(event.getPlayer().getName()))
            event.getPlayer().getInventory().setArmorContents(newInventory);
        inv.remove(event.getPlayer());

        KitManager.giveKitSelector(event.getPlayer());
    }
}
