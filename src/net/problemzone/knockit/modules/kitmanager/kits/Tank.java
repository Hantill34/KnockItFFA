package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tank extends Kit {

    public Tank() {
        super(ChatColor.DARK_GRAY + "Tank", 0, Material.DIAMOND_CHESTPLATE);
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack sword = new ItemStackBuilder(Material.WOODEN_SWORD, ChatColor.RED + "Schwert").addEnchantment(Enchantment.DAMAGE_ALL, 3).addEnchantment(Enchantment.KNOCKBACK, 1).setUnbreakable(true).getItemStack();

        ItemStack chest = new ItemStackBuilder(Material.NETHERITE_CHESTPLATE, ChatColor.YELLOW + "Panzer").setUnbreakable(true).getItemStack();
        ItemStack leggings = new ItemStackBuilder(Material.NETHERITE_LEGGINGS, ChatColor.YELLOW + "Hose").setUnbreakable(true).getItemStack();
        ItemStack boots = new ItemStackBuilder(Material.NETHERITE_BOOTS, ChatColor.YELLOW + "Schuhe").setUnbreakable(true).getItemStack();

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
        p.getInventory().addItem(sword);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setBoots(boots);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }


}