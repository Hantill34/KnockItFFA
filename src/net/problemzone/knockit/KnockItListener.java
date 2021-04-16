package net.problemzone.knockit;

import net.problemzone.knockit.scoreboard.KnockItScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KnockItListener implements Listener{

    private final KnockItScoreboard knockItScoreboard;
    public KnockItListener(KnockItScoreboard knockItScoreboard) {
        this.knockItScoreboard = knockItScoreboard;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();

        assert stockItemMeta != null;
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);
        p.getInventory().clear();
        p.getInventory().addItem(stock);

        knockItScoreboard.newPlayerDeath(p);
        knockItScoreboard.setScoreboard(p);

    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        knockItScoreboard.increaseDeathCounter(event.getEntity());
        knockItScoreboard.updateDeath(event.getEntity());
    }

}

