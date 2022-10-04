package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Knight extends Kit {

    public Knight() {
        super(ChatColor.DARK_GREEN + "Ritter", 0, Material.SHIELD);
    }

    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStackBuilder(Material.STICK, ChatColor.RED + "Knüppel").addEnchantment(Enchantment.KNOCKBACK, 4).getItemStack();
        ItemStack shield = new ItemStackBuilder(Material.SHIELD, ChatColor.DARK_GRAY + "Schild").setUnbreakable(true).getItemStack();

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().setItemInOffHand(shield);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

}
