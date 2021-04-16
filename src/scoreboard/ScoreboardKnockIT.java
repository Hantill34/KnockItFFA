package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;


public class ScoreboardKnockIT {

    private Map<Player, Integer> playerDeath = new HashMap<>();

    public void setScoreboard(Player player)
    {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("ServerName", "dummy", ChatColor.GREEN + "» KnockIt «");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);



        Score onlineName = obj.getScore(ChatColor.WHITE + "➵ Online");
        onlineName.setScore(15);
        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        if (Bukkit.getOnlinePlayers().size() == 0) {
            onlineCounter.setPrefix(ChatColor.DARK_RED + "0" + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        } else {
            onlineCounter.setPrefix("" + ChatColor.GOLD + Bukkit.getOnlinePlayers().size() + ChatColor.GOLD + " / " + ChatColor.GOLD + Bukkit.getMaxPlayers());
        }
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);


        Score leer = obj.getScore(ChatColor.WHITE + "");
        leer.setScore(13);
        Team leerZeile = board.registerNewTeam("leerZeile");
        leerZeile.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        leerZeile.setPrefix(ChatColor.RED + "");
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(12);



        Score death = obj.getScore(ChatColor.WHITE + "➵ Tode");
        death.setScore(11);
        Team deathCounter = board.registerNewTeam("deathCounter");
        deathCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        deathCounter.setPrefix(ChatColor.RED + "" + playerDeath.get(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(10);



        player.setScoreboard(board);

    }

    public void updateDeath(Player player){
        Scoreboard board = player.getScoreboard();
        board.getTeam("deathCounter").setPrefix(ChatColor.RED + "" + playerDeath.get(player));


    }

    public void increaseDeathCounter(Player player)
    {
        playerDeath.put(player, playerDeath.get(player) +1);
    }

    public void newPlayerDeath(Player player)
    {
        playerDeath.put(player, 0);
    }

}
