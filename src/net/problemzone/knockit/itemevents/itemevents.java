package net.problemzone.knockit.itemevents;

import net.problemzone.knockit.kitmanager.kits.Angler;
import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;


public class itemevents implements Listener
{
    private HashMap <UUID, Double> cooldowns = new HashMap <> ();

    @EventHandler
    public void onFish(PlayerFishEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String name = meta.getDisplayName();

        if(name.equals(ChatColor.AQUA + "Grabbling Hook"))
        {
            if(event.getState().equals(PlayerFishEvent.State.REEL_IN))
            {
                    Location playerLocation = player.getLocation();
                    Location hookLocation = event.getHook().getLocation();
                    Location change = hookLocation.subtract(playerLocation);
                    player.setVelocity(change.toVector().multiply(0.3));
                    //cooldown.setCooldown(event.getPlayer(), 5);
            }

        }


    }

    /**public void setupCooldown()
    {
        HashMap cooldowns = new HashMap <> ();
    }

    public void setCooldown(Player player, int seconds)
    {
        Long delay = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(player.getUniqueId(), delay);
    }

    public boolean checkCooldown(Player player)
    {
        if(!cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis())
        {
            return true;
        }
        return false;
    }**/
}