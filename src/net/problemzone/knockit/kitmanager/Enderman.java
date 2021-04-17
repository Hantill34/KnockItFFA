package net.problemzone.knockit.kitmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public class Enderman extends kit {

    public Enderman() {
        super(ChatColor.DARK_PURPLE + "Enderman", 0, Material.ENDER_EYE);
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(stockItemMeta);

        ItemStack end = new ItemStack(Material.ENDER_PEARL, 2);
        ItemMeta endItemMeta = end.getItemMeta();
        endItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Enderpearl");
        end.setItemMeta(endItemMeta);

        p.getInventory().clear();
        p.removePotionEffect(PotionEffectType.SLOW);
        p.getInventory().addItem(stock);
        p.getInventory().addItem(end);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

}