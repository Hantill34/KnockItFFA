package net.problemzone.knockit.kitmanager;

import net.problemzone.knockit.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import java.util.ArrayList;

public class kitmanager implements Listener
{
    private final Main main;

    public ArrayList<kit> kits;

    public kitmanager(Main main)
    {
        kits = new ArrayList<kit>();
        kits.add(new Angler());
        this.main = main;
    }

    @EventHandler
    public void onKitChestOpen(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
        {
            if(e.getItem().getType() == Material.CHEST)
            {
                e.setCancelled(true);
                openInventory(e.getPlayer());
            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getInventory() != null && e.getCurrentItem() != null)
        {
            if(e.getView().getTitle().equals("&eKitauswahl"))
            {
                Player p = (Player)e.getWhoClicked();
                kit kit = getKitByName(e.getCurrentItem().getItemMeta().getDisplayName());
                main.kits.put(p, kit);
                kit.equip(p);
            }
        }
    }



    public void openInventory(Player p)
    {
        Inventory inv = Bukkit.createInventory(null, 9, "&eKitauswahl");
        for(kit kit : kits )
        {
            inv.addItem(kit.getItem());
        }
        p.openInventory(inv);
    }

    public kit getKitByName(String name)
    {
        for (kit kit : kits)
        {
            if(kit.getName().equalsIgnoreCase(name))
            {
                return kit;
            }
        }
        return kits.get(0);



    }



}




