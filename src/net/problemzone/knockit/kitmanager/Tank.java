package net.problemzone.knockit.kitmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tank extends kit
{

    public Tank()
    {
        super(ChatColor.BLACK + "Tank", 0, Material.NETHERITE_CHESTPLATE);
    }


    @Override
    public void equip(Player p)
    {
        p.getInventory().clear();

        ItemStack sword = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemMeta swordItemMeta = sword.getItemMeta();
        swordItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        swordItemMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        swordItemMeta.setDisplayName(ChatColor.RED + "Schwert");
        sword.setItemMeta(swordItemMeta);

        ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE,1);
        ItemMeta chestItemMeta = chest.getItemMeta();
        chestItemMeta.addEnchant(Enchantment.DURABILITY, 5,true);
        chestItemMeta.setDisplayName(ChatColor.BLACK + "Panzer");
        chest.setItemMeta(chestItemMeta);

        p.getInventory().clear();
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2));
        p.getInventory().addItem(sword);
        p.getInventory().setChestplate(chest);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }


}