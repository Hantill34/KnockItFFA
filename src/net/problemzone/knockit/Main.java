package net.problemzone.knockit;

import net.problemzone.knockit.killstreak.playerDeathListener;
import net.problemzone.knockit.kitmanager.Kit;
import net.problemzone.knockit.kitmanager.KitListener;
import net.problemzone.knockit.kitmanager.KitManager;
import net.problemzone.knockit.scoreboard.ScoreboardHandler;
import net.problemzone.knockit.scoreboard.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public KitManager kitManager = new KitManager();
    public ScoreboardHandler scoreboardHandler = new ScoreboardHandler();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ScoreboardListener(scoreboardHandler), this);
        getServer().getPluginManager().registerEvents(new KitListener(kitManager), this);
        //getServer().getPluginManager().registerEvents(new playerDeathListener(), this);

        loadKits();
    }

    private void loadKits() {
        for (Kit kit : kitManager.getKits()) {
            Bukkit.getPluginManager().registerEvents(kit, this);
        }
    }
}
