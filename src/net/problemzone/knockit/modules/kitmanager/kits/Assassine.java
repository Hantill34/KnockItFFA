package net.problemzone.knockit.modules.kitmanager.kits;

import net.problemzone.knockit.modules.kitmanager.KitManager;
import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;


public class Assassine extends Kit {

    public Assassine() {
        super(ChatColor.DARK_RED + "Assassine", 0, Material.IRON_SWORD);
    }

    @Override
    public void equip(Player p) {
        ItemStack stock = new ItemStackBuilder(Material.STICK, ChatColor.RED + "Knüppel").addEnchantment(Enchantment.KNOCKBACK, 3).getItemStack();

        p.getInventory().clear();
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    public void onKill(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10 * 20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 1));
    }

    // <--- Class Listener --->
    @EventHandler
    public void onPLayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (KitManager.getInstance().getKitByPlayer(player) instanceof Assassine) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        if (event.getEntity().getKiller() == null) return;
        if (!(KitManager.getInstance().getKitByPlayer(event.getEntity().getKiller()) instanceof Assassine)) return;
        onKill(event.getEntity().getKiller());
    }

}