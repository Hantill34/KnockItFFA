package net.problemzone.knockit.scoreboard;

import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class ScoreboardListener implements Listener {

    private final ScoreboardHandler scoreboardHandler;

    public ScoreboardListener(ScoreboardHandler scoreboardHandler) {
        this.scoreboardHandler = scoreboardHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        scoreboardHandler.initPlayer(p);
        scoreboardHandler.setScoreboard(p);
        scoreboardHandler.updateScoreboard();
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

        event.setDeathMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.PLAYER_DEATH), event.getEntity().getName()));

        if(Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (event.getEntity().getKiller() != null) {
                scoreboardHandler.increaseKillCounter(event.getEntity().getKiller());
                event.setDeathMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.PLAYER_DEATH_BY_PLAYER), event.getEntity().getName(), event.getEntity().getKiller().getName()));
            }

            scoreboardHandler.increaseDeathCounter(event.getEntity());
            scoreboardHandler.updateScoreboard();
        }
    }
}
