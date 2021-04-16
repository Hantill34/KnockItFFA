package net.problemzone.knockit;

import net.problemzone.knockit.kitmanager.kit;
import net.problemzone.knockit.scoreboard.KnockItScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public HashMap<Player, kit> kits;

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new KnockItListener(new KnockItScoreboard()), this);
        kits = new HashMap<Player, kit>();
    }

}
