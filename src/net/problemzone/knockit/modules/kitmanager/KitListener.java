package net.problemzone.knockit.modules.kitmanager;

import net.problemzone.knockit.modules.kitmanager.kits.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class KitListener implements Listener {

    @EventHandler
    public void onKitChestOpen(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem() == null || e.getItem().getType() != Material.CHEST) return;
            e.setCancelled(true);
            KitManager.getInstance().openInventory(e.getPlayer());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            if (e.getView().getTitle().equals(ChatColor.YELLOW + "Kitauswahl")) {
                Player p = (Player) e.getWhoClicked();
                Kit kit = KitManager.getInstance().getKitByName(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName());
                kit.equip(p);
                KitManager.getInstance().putPlayerInMap(p, kit);
            }
        }
        e.setCancelled(true);
    }

}
