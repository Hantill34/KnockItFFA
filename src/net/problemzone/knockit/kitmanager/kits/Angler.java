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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Angler extends Kit {

    public Angler() {
        super(ChatColor.AQUA + "Angler", 0, Material.FISHING_ROD);
    }


    public static ItemStack createGrapplingHook() {
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta rodItemMeta = rod.getItemMeta();
        rodItemMeta.setDisplayName(ChatColor.AQUA + "Grabbling Hook");
        List<String> lore = new ArrayList<>();
        lore.add("Du hast 5 Sekunden Cooldown.");
        rodItemMeta.setLore(lore);
        rodItemMeta.setUnbreakable(true);
        rod.setItemMeta(rodItemMeta);
        return rod;
    }

    public static ItemStack GrapplingHook = createGrapplingHook();

    public static HashMap<Player, Double> cooldowns;

    public static void setupCooldown() {
        cooldowns = new HashMap<>();
    }

    public static void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (seconds * 1000L);
        if (checkCooldown(player)) {
            cooldowns.put(player, delay);
        }
    }

    public static boolean checkCooldown(Player player) {
        return !cooldowns.containsKey(player) || cooldowns.get(player) <= (System.currentTimeMillis());
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Knüppel");
        stock.setItemMeta(stockItemMeta);


        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().addItem(GrapplingHook);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }


}