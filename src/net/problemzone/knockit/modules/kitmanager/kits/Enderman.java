package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.modules.kitmanager.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Enderman extends Kit {

    public Enderman() {
        super(ChatColor.DARK_PURPLE + "Enderman", 0, Material.ENDER_EYE);
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta stockItemMeta = stock.getItemMeta();
        stockItemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        stockItemMeta.setDisplayName(ChatColor.RED + "Knüppel");
        stock.setItemMeta(stockItemMeta);

        ItemStack end = new ItemStack(Material.ENDER_PEARL, 5);
        ItemMeta endItemMeta = end.getItemMeta();
        endItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Enderpearl");
        end.setItemMeta(endItemMeta);

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().addItem(end);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    public static void hasPearl(Player player){

        ItemStack end = new ItemStack(Material.ENDER_PEARL, 5);
        ItemMeta endItemMeta = end.getItemMeta();
        endItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Enderpearl");
        end.setItemMeta(endItemMeta);

        player.getInventory().addItem(end);

        for(ItemStack item : player.getInventory()){
            if (item.getType() == Material.ENDER_PEARL){
                if(item.getAmount() > 5){
                    item.setAmount(5);
                }
            }
        }
    }

}