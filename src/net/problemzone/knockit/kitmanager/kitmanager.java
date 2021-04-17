package net.problemzone.knockit.kitmanager;

import net.problemzone.knockit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class kitmanager implements Listener
{
    private final Main main;

    public ArrayList<kit> kits;

    public kitmanager(Main main)
    {
        kits = new ArrayList<kit>();

        //NEUES KIT HIER REGISTRIEREN
        kits.add(new Angler());
        kits.add(new Enderman());
        kits.add(new Tank());


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
            if(e.getView().getTitle().equals(ChatColor.YELLOW + "Kitauswahl"))
            {
                Player p = (Player)e.getWhoClicked();
                kit kit = getKitByName(e.getCurrentItem().getItemMeta().getDisplayName());
                main.kits.put(p, kit);
                kit.equip(p);
            }
        }
        e.setCancelled(true);

    }



    public void openInventory(Player p)
    {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Kitauswahl");

        //füllt das Inventar mit Items
        /**inv.setItem(2, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(3, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(4, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(5, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(6, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(7, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(8, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(9, new ItemStack(Material.RED_STAINED_GLASS_PANE));**/

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




