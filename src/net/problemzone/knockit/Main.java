package net.problemzone.knockit;

import net.problemzone.knockit.kitmanager.kit;
import net.problemzone.knockit.kitmanager.kitmanager;
import net.problemzone.knockit.scoreboard.KnockItScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public HashMap<Player, kit> kits;
    public kitmanager km;

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new KnockItListener(new KnockItScoreboard()), this);
        kits = new HashMap<Player, kit>();
        loadKits();
    }

    private void loadKits()
    {
        PluginManager pm = Bukkit.getPluginManager();
        km = new kitmanager(this);
        pm.registerEvents(km, this);
        ArrayList<Listener> listeners = new ArrayList<>();
        for(int i = 0; i < km.kits.size(); i++)
        {
            listeners.add(km.kits.get(i));
        }
        for (Listener l : listeners)
        {
            pm.registerEvents(l, this);
        }

    }

}
