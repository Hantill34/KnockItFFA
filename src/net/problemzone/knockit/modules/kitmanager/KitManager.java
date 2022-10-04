package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.Angler;
import net.problemzone.knockit.modules.kitmanager.kits.Assassine;
import net.problemzone.knockit.modules.kitmanager.kits.Enderman;
import net.problemzone.knockit.modules.kitmanager.kits.Kit;
import net.problemzone.knockit.modules.kitmanager.kits.Knight;
import net.problemzone.knockit.modules.kitmanager.kits.Tank;
import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitManager {

    private static KitManager instance;
    private final List<Kit> kits = new ArrayList<>();
    private final Map<Player, Kit> playerKitMap = new HashMap<>();


    private KitManager() {
        //NEUES KIT HIER REGISTRIEREN
        kits.add(new Angler());
        kits.add(new Enderman());
        kits.add(new Tank());
        kits.add(new Assassine());
        kits.add(new Knight());
    }

    public void giveKitSelector(Player p) {
        ItemStack chest = new ItemStackBuilder(Material.CHEST, ChatColor.YELLOW + "Kitauswahl").getItemStack();
        p.getInventory().addItem(chest);
    }


    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Kitauswahl");

        //füllt das Inventar mit Items

        inv.setItem(0, kits.get(0).getPreviewItem());
        inv.setItem(1, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(2, kits.get(1).getPreviewItem());
        inv.setItem(3, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(4, kits.get(2).getPreviewItem());
        inv.setItem(5, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(6, kits.get(3).getPreviewItem());
        inv.setItem(7, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inv.setItem(8, kits.get(4).getPreviewItem());

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

    public static KitManager getInstance() {
        if (instance == null) instance = new KitManager();
        return instance;
    }
}




