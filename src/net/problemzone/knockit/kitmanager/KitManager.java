package net.problemzone.knockit.kitmanager;

import net.problemzone.knockit.kitmanager.kits.Angler;
import net.problemzone.knockit.kitmanager.kits.Assassine;
import net.problemzone.knockit.kitmanager.kits.Enderman;
import net.problemzone.knockit.kitmanager.kits.Tank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KitManager {
    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        //NEUES KIT HIER REGISTRIEREN
        kits.add(new Angler());
        kits.add(new Enderman());
        kits.add(new Tank());
        kits.add(new Assassine());
    }

    public void giveKitSelector(Player p){
        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestItemMeta = chest.getItemMeta();

        assert chestItemMeta != null;
        chestItemMeta.setDisplayName(ChatColor.YELLOW + "Kitauswahl");
        chest.setItemMeta(chestItemMeta);

        p.getInventory().clear();
        p.getInventory().addItem(chest);
    }

    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Kitauswahl");

        //füllt das Inventar mit Items
        /*
        inv.setItem(2, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(3, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(4, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(5, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(6, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(7, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(8, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(9, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        */

        for (Kit kit : kits) {
            inv.addItem(kit.getItem());
        }
        p.openInventory(inv);
    }

    public Kit getKitByName(String name) {
        for (Kit kit : kits) {
            if (kit.getName().equalsIgnoreCase(name)) {
                return kit;
            }
        }
        return kits.get(0);
    }

    public List<Kit> getKits() {
        return kits;
    }
}




