package net.problemzone.knockit.kitmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enderman extends kit {

    public Enderman() {
        super(ChatColor.DARK_PURPLE + "Enderman", 0, Material.ENDER_EYE);
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        assert stockItemMeta != null;
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);

        ItemStack rod = new ItemStack(Material.ENDER_PEARL, 2);
        ItemMeta rodItemMeta = rod.getItemMeta();
        rodItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Enderpearl");

        p.getInventory().clear();
        p.getInventory().addItem(stock);
        p.getInventory().addItem(rod);
    }

}