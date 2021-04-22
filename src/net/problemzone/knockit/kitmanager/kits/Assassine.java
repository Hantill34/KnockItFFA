package net.problemzone.knockit.kitmanager.kits;

import net.problemzone.knockit.kitmanager.Kit;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class Assassine extends Kit {

    public Assassine() {
        super(ChatColor.DARK_RED + "Noch nicht verfügbar", 0, Material.BARRIER);
    }

    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta stockItemMeta = rod.getItemMeta();
        stockItemMeta.setUnbreakable(true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Stock");
        rod.setItemMeta(stockItemMeta);


        p.getInventory().clear();
        p.removePotionEffect(PotionEffectType.SLOW);
        p.getInventory().addItem(rod);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    public static void onKill(Player p)
    {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5, 0));
    }

}