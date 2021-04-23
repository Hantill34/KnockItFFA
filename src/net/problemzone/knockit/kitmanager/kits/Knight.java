package net.problemzone.knockit.kitmanager.kits;

import net.problemzone.knockit.kitmanager.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Knight extends Kit {

    public Knight() {
        super(ChatColor.DARK_GREEN + "Knight", 0, Material.IRON_SWORD);
    }

    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stick = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stick.getItemMeta();
        stockItemMeta.setUnbreakable(true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Knüppel");
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        stick.setItemMeta(stockItemMeta);

        ItemStack shield = new ItemStack(Material.SHIELD, 1);
        ItemMeta shieldItemMeta = shield.getItemMeta();
        shieldItemMeta.setUnbreakable(true);
        shieldItemMeta.setDisplayName(ChatColor.DARK_GRAY + "Schild");

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stick);
        p.getInventory().setItemInOffHand(shield);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }







}
