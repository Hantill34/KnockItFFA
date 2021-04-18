package net.problemzone.knockit.scoreboard;

import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardListener implements Listener {

    private final ScoreboardHandler scoreboardHandler;

    public ScoreboardListener(ScoreboardHandler scoreboardHandler) {
        this.scoreboardHandler = scoreboardHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        scoreboardHandler.newPlayerDeath(p);
        scoreboardHandler.newPlayerKill(p);

        scoreboardHandler.setScoreboard(p);
        scoreboardHandler.updateScoreboard();
    }

    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent cause = (EntityDamageByEntityEvent) player.getLastDamageCause();
                    player.setHealth(0);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (event.getEntity().getKiller() != null) {
            scoreboardHandler.increaseKillCounter(event.getEntity().getKiller());
            Bukkit.broadcastMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.PLAYER_DEATH), event.getEntity().getKiller().getName(), event.getEntity().getName()));
        }

        scoreboardHandler.increaseDeathCounter(event.getEntity());
        scoreboardHandler.updateScoreboard();

    }

}
