package net.problemzone.knockit.kitmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Angler extends kit
{

    public Angler()
    {
        super(ChatColor.AQUA + "Angler", 0, Material.FISHING_ROD);
    }


    @Override
    public void equip(Player p)
    {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        assert stockItemMeta != null;
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);

        ItemStack rod = new ItemStack(Material.FISHING_ROD,1);
        ItemMeta rodItemMeta = rod.getItemMeta();
        assert rodItemMeta != null;
        rodItemMeta.addEnchant(Enchantment.DURABILITY, 30,true);
        rodItemMeta.setDisplayName(ChatColor.AQUA + "Grabbling Hook");

        p.getInventory().clear();
        p.getInventory().addItem(stock);
        p.getInventory().addItem(rod);
    }


}