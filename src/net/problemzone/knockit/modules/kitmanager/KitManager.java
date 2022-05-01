package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitManager {
    private final List<Kit> kits = new ArrayList<>();
    private final Map<Player, Kit> playerKitMap = new HashMap<>();


    public KitManager() {
        //NEUES KIT HIER REGISTRIEREN
        kits.add(new Angler());
        kits.add(new Enderman());
        kits.add(new Tank());
        kits.add(new Assassine());
        kits.add(new Knight());
    }

    public static void giveKitSelector(Player p) {

        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestItemMeta = chest.getItemMeta();

        assert chestItemMeta != null;
        chestItemMeta.setDisplayName(ChatColor.YELLOW + "Kitauswahl");
        chest.setItemMeta(chestItemMeta);

        p.getInventory().addItem(chest);

    }



    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Kitauswahl");

        //füllt das Inventar mit Items

        inv.setItem(0, kits.get(0).getItem());
        inv.setItem(1, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(2, kits.get(1).getItem());
        inv.setItem(3, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(4, kits.get(2).getItem());
        inv.setItem(5, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(6, kits.get(3).getItem());
        inv.setItem(7, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(8, kits.get(4).getItem());

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

    public void putPlayerInMap(Player player, Kit kit) {
        playerKitMap.put(player, kit);
    }

    public Kit getKitByPlayer(Player player)
    {
        return playerKitMap.get(player);
    }


}




