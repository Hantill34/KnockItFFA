package net.problemzone.knockit.modules.kitmanager.kits;


import net.problemzone.knockit.util.ItemStackBuilder;
import net.problemzone.knockit.util.Language;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Angler extends Kit {

    public Angler() {
        super(ChatColor.AQUA + "Angler", 0, Material.FISHING_ROD);
    }

    public static ItemStack GrapplingHook = createGrapplingHook();

    public static HashMap<Player, Double> cooldowns = new HashMap<>();

    public static ItemStack createGrapplingHook() {
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta rodItemMeta = rod.getItemMeta();
        Objects.requireNonNull(rodItemMeta).setDisplayName(ChatColor.AQUA + "Grappling Hook");
        List<String> lore = new ArrayList<>();
        lore.add("Du hast 5 Sekunden Cooldown.");
        rodItemMeta.setLore(lore);
        rodItemMeta.setUnbreakable(true);
        rod.setItemMeta(rodItemMeta);
        return rod;
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
        ItemStack stock = new ItemStackBuilder(Material.STICK, ChatColor.RED + "Knüppel").addEnchantment(Enchantment.KNOCKBACK, 3).getItemStack();

        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().addItem(stock);
        p.getInventory().addItem(GrapplingHook);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
    }

    // <--- Class Listener --->
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String name = Objects.requireNonNull(meta).getDisplayName();
        if (!name.equals(ChatColor.AQUA + "Grappling Hook")) return;

        if (!event.getState().equals(PlayerFishEvent.State.REEL_IN)) return;

        if (!Angler.checkCooldown(event.getPlayer())) {
            player.sendMessage(Language.GRAPPLER_COOLDOWN.getFormattedText());
            return;
        }

        Location playerLocation = player.getLocation();
        Location grapplingLocation = event.getHook().getLocation();
        Location change = grapplingLocation.subtract(playerLocation);
        Vector vector = change.toVector();
        vector.setY(vector.getY() * 0.16);
        player.setVelocity(vector.multiply(0.5));
        Angler.setCooldown(event.getPlayer(), 5);
    }


}