package net.problemzone.knockit;

import net.problemzone.knockit.modules.WorldProtectionListener;
import net.problemzone.knockit.modules.boostpads.BoostpadListener;
import net.problemzone.knockit.modules.kitmanager.GameListener;
import net.problemzone.knockit.modules.kitmanager.KitListener;
import net.problemzone.knockit.modules.kitmanager.KitManager;
import net.problemzone.knockit.modules.kitmanager.kits.Kit;
import net.problemzone.knockit.modules.maps.MapManager;
import net.problemzone.knockit.modules.scoreboard.ScoreboardListener;
import net.problemzone.knockit.util.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        getLogger().info("Loading KnockIt Plugin.");
        initiatePlugin();

        getLogger().info("Load KnockIt Worlds.");
        loadWorlds();

        getLogger().info("Loading KnockIt Kits.");
        loadKits();

        getLogger().info("Loading KnockIt Listeners.");
        registerListeners();

        getLogger().info("KnockIt primed and ready.");
    }

    private void initiatePlugin() {
        instance = this;
        ConfigManager.getInstance().setupConfig();
    }

    private void loadWorlds() {
        MapManager.getInstance().addGameMaps();
        MapManager.getInstance().startMapRotation();
    }
    
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
        getServer().getPluginManager().registerEvents(new KitListener(), this);
        getServer().getPluginManager().registerEvents(new WorldProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new BoostpadListener(), this);
        getServer().getPluginManager().registerEvents(new GameListener(), this);
    }

    //Kits werden geladen
    private void loadKits() {
        for (Kit kit : KitManager.getInstance().getKits()) {
            Bukkit.getPluginManager().registerEvents(kit, this);
        }
    }

    public static Main getInstance() {
        return instance;
    }

}
