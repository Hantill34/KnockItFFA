package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.modules.kitmanager.KitManager;
import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Enderman extends Kit {

    public Enderman() {
        super(ChatColor.DARK_PURPLE + "Enderman", 0, Material.ENDER_EYE);
    }


    @Override
    public void equip(Player p) {
        p.getInventory().clear();

        ItemStack stock = new ItemStackBuilder(Material.STICK, ChatColor.RED + "Knüppel").addEnchantment(Enchantment.KNOCKBACK, 3).getItemStack();
        ItemStack end = new ItemStackBuilder(Material.ENDER_PEARL, ChatColor.DARK_PURPLE + "Enderpearl", 5).getItemStack();

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().addItem(end);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    public void onKill(Player player){
        ItemStack end = new ItemStackBuilder(Material.ENDER_PEARL, ChatColor.DARK_PURPLE + "Enderpearl", 5).getItemStack();
        player.getInventory().addItem(end);
    }

    // <--- Class Listener --->
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause() == null || event.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        if (event.getEntity().getKiller() == null) return;
        if (!(KitManager.getInstance().getKitByPlayer(event.getEntity().getKiller()) instanceof Enderman)) return;
        onKill(event.getEntity().getKiller());
    }

}