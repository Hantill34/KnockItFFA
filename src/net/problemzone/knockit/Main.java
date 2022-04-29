package net.problemzone.knockit;

import net.problemzone.knockit.modules.WorldProtectionListener;
import net.problemzone.knockit.modules.boostpads.BoostpadListener;
import net.problemzone.knockit.modules.kitmanager.Kit;
import net.problemzone.knockit.modules.kitmanager.KitListener;
import net.problemzone.knockit.modules.kitmanager.KitManager;
import net.problemzone.knockit.modules.kitmanager.kits.Angler;
import net.problemzone.knockit.modules.scoreboard.ScoreboardHandler;
import net.problemzone.knockit.modules.scoreboard.ScoreboardListener;
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
        getServer().getPluginManager().registerEvents(new WorldProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new BoostpadListener(), this);

        loadKits();
        Angler.setupCooldown();

        System.out.println(ChatColor.GREEN + "Das Plugin wurde erfolgreich geladen!");
    }

    private void loadKits() {
        for (Kit kit : kitManager.getKits()) {
            Bukkit.getPluginManager().registerEvents(kit, this);
        }
    }
}
