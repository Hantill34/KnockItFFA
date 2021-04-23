package net.problemzone.knockit.kitmanager.kits;


import net.problemzone.knockit.kitmanager.Kit;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Angler extends Kit
{

    public Angler()
    {
        super(ChatColor.AQUA + "Angler", 0, Material.TROPICAL_FISH);
    }

    public static ItemStack GrapplingHook;

    /**public static void createGrapplingHook()
    {
        ItemStack rod = new ItemStack(Material.FISHING_ROD,1);
        ItemMeta rodItemMeta = rod.getItemMeta();
        rodItemMeta.setUnbreakable(true);
        rodItemMeta.setDisplayName(ChatColor.AQUA + "Grabbling Hook");
        rod.setItemMeta(rodItemMeta);


    }**/

    @Override
    public void equip(Player p)
    {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Knüppel");
        stock.setItemMeta(stockItemMeta);

        ItemStack rod = new ItemStack(Material.FISHING_ROD,1);
        ItemMeta rodItemMeta = rod.getItemMeta();
        rodItemMeta.setUnbreakable(true);
        rodItemMeta.setDisplayName(ChatColor.AQUA + "Grabbling Hook");
        rod.setItemMeta(rodItemMeta);


        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().addItem(rod);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }


}