package net.problemzone.knockit;

import net.problemzone.knockit.boostpads.moveListener;
import net.problemzone.knockit.kitmanager.Kit;
import net.problemzone.knockit.kitmanager.KitListener;
import net.problemzone.knockit.kitmanager.KitManager;
import net.problemzone.knockit.kitmanager.kits.Angler;
import net.problemzone.knockit.scoreboard.ScoreboardHandler;
import net.problemzone.knockit.scoreboard.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public KitManager kitManager = new KitManager();
    public ScoreboardHandler scoreboardHandler = new ScoreboardHandler();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ScoreboardListener(scoreboardHandler), this);
        getServer().getPluginManager().registerEvents(new KitListener(kitManager), this);
        loadKits();
        Angler.setupCooldown();
        getServer().getPluginManager().registerEvents(new moveListener(), this);

        System.out.println(ChatColor.GREEN + "Das Plugin wurde erfolgreich geladen!");
    }

    private void loadKits() {
        for (Kit kit : kitManager.getKits()) {
            Bukkit.getPluginManager().registerEvents(kit, this);
        }
    }
}
