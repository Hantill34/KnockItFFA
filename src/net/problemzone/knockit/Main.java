package net.problemzone.knockit;

import net.problemzone.knockit.modules.WorldProtectionListener;
import net.problemzone.knockit.modules.boostpads.BoostpadListener;
import net.problemzone.knockit.modules.kitmanager.Kit;
import net.problemzone.knockit.modules.kitmanager.KitListener;
import net.problemzone.knockit.modules.kitmanager.KitManager;
import net.problemzone.knockit.modules.kitmanager.RespawnListener;
import net.problemzone.knockit.modules.kitmanager.kits.Angler;
import net.problemzone.knockit.modules.scoreboard.ScoreboardHandler;
import net.problemzone.knockit.modules.scoreboard.ScoreboardListener;
import net.problemzone.knockit.util.config.ConfigManager;
import net.problemzone.knockit.util.config.MapManager;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    private static JavaPlugin javaPlugin;
    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public KitManager kitManager = new KitManager();
    public ScoreboardHandler scoreboardHandler = new ScoreboardHandler();

    @Override
    public void onEnable() {
        initiatePlugin();
        ConfigManager.getInstance().setupConfig();


        MapManager.getInstance().addGameMaps();
        MapManager.getInstance().startMapRotation();

        getServer().getPluginManager().registerEvents(new ScoreboardListener(scoreboardHandler), this);
        getServer().getPluginManager().registerEvents(new KitListener(kitManager), this);
        getServer().getPluginManager().registerEvents(new WorldProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new BoostpadListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);

        loadKits();
        Angler.setupCooldown();  //Cooldown der Angel

        System.out.println(ChatColor.GREEN + "Das Plugin wurde erfolgreich geladen!");
    }

    //Kits werden geladen
    private void loadKits() {
        for (Kit kit : kitManager.getKits()) {
            Bukkit.getPluginManager().registerEvents(kit, this);
        }
    }

    private void initiatePlugin() {
        javaPlugin = this;
    }

}
