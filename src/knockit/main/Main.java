package knockit.main;

import knockit.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import scoreboard.ScoreboardKnockIT;

public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new JoinListener(new ScoreboardKnockIT()), this);

    }

}
