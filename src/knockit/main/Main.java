package knockit.main;

import knockit.kitmanager.kit;
import knockit.listener.JoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import scoreboard.ScoreboardKnockIT;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public HashMap<Player, kit> kits;





    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new JoinListener(new ScoreboardKnockIT()), this);
        kits = new HashMap<Player, kit>();
    }

}
