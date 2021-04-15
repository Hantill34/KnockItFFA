package knockit.listener;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import scoreboard.ScoreboardKnockIT;

public class JoinListener implements Listener{

    private final ScoreboardKnockIT scoreboardknockit;
    public JoinListener(ScoreboardKnockIT scoreboardknockit) {
        this.scoreboardknockit = scoreboardknockit;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ItemStack stock = new ItemStack(Material.STICK, 1);
        ItemMeta knockback = stock.getItemMeta();

        knockback.addEnchant(Enchantment.KNOCKBACK, 3, true);
        knockback.setDisplayName(ChatColor.RED + "Stock");
        stock.setItemMeta(knockback);
        p.getInventory().clear();
        p.getInventory().addItem(stock);

        scoreboardknockit.newPlayerDeath(p);
        scoreboardknockit.setScoreboard(p);

    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        scoreboardknockit.increaseDeathCounter(event.getEntity());
        scoreboardknockit.updateDeath(event.getEntity());
    }

}

