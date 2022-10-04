package net.problemzone.knockit.modules.scoreboard;

import net.problemzone.knockit.util.Language;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ScoreboardManager.getInstance().initPlayer(p);
        ScoreboardManager.getInstance().setScoreboard(p);
        ScoreboardManager.getInstance().updateScoreboard();
    }

    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                e.setCancelled(true);
                player.setHealth(0);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        event.setDeathMessage(String.format(Language.PLAYER_DEATH.getFormattedText(), event.getEntity().getName()));

        if(event.getEntity().getLastDamageCause() == null || event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (event.getEntity().getKiller() != null) {
                ScoreboardManager.getInstance().increaseKillCounter(event.getEntity().getKiller());
                event.setDeathMessage(String.format(Language.PLAYER_DEATH_BY_PLAYER.getFormattedText(), event.getEntity().getName(), event.getEntity().getKiller().getName()));
            }

            ScoreboardManager.getInstance().increaseDeathCounter(event.getEntity());
            ScoreboardManager.getInstance().updateScoreboard();
        }
    }
}
