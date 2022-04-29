package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.modules.kitmanager.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class Assassine extends Kit {

    public Assassine() {
        super(ChatColor.DARK_RED + "Assassine", 0, Material.IRON_SWORD);
    }

    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stick = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stick.getItemMeta();
        stockItemMeta.setUnbreakable(true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Knüppel");
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stick.setItemMeta(stockItemMeta);


        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stick);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    public static void onKill(Player p)
    {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10*20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*20, 1));
    }

}