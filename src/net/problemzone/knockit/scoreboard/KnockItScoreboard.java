package net.problemzone.knockit.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class KnockItScoreboard {

    private final Map<Player, Integer> playerDeaths = new HashMap<>();
    private final Map<Player, Integer> playerKills = new HashMap<>();

    public void setScoreboard(Player player)
    {
        Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective obj = board.registerNewObjective("Infos", "dummy", ChatColor.GREEN + "» KNOCKIT «");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("").setScore(12);

        Score onlineName = obj.getScore(ChatColor.WHITE + "Spieler:");
        onlineName.setScore(11);
        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE);
        onlineCounter.setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size());
        obj.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(10);

        obj.getScore("").setScore(9);

        Score kills = obj.getScore(ChatColor.WHITE + "Kills:");
        kills.setScore(8);
        Team killCounter = board.registerNewTeam("killCounter");
        killCounter.addEntry(ChatColor.GREEN + "" + ChatColor.WHITE);
        killCounter.setPrefix(ChatColor.GOLD + "" + playerKills.get(player));
        obj.getScore(ChatColor.GREEN + "" + ChatColor.WHITE).setScore(7);

        obj.getScore("").setScore(6);

        Score death = obj.getScore(ChatColor.WHITE + "Tode:");
        death.setScore(5);
        Team deathCounter = board.registerNewTeam("deathCounter");
        deathCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        deathCounter.setPrefix(ChatColor.GOLD + "" + playerDeaths.get(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(4);

        obj.getScore("").setScore(3);

        Score kd = obj.getScore(ChatColor.WHITE + "KD:");
        kd.setScore(2);
        Team kdCounter = board.registerNewTeam("kdCounter");
        kdCounter.addEntry(ChatColor.GOLD + "" + ChatColor.WHITE);
        kdCounter.setPrefix(ChatColor.GOLD + "" + (playerDeaths.get(player) == 0 ? "GOD" : playerKills.get(player) / playerDeaths.get(player)));
        obj.getScore(ChatColor.GOLD + "" + ChatColor.WHITE).setScore(1);

        player.setScoreboard(board);
    }

    private void updatePlayer(Player player){
        Scoreboard board = player.getScoreboard();
        Objects.requireNonNull(board.getTeam("playerCounter")).setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size());
    }

    private void updateDeath(Player player){
        Scoreboard board = player.getScoreboard();
        Objects.requireNonNull(board.getTeam("deathCounter")).setPrefix(ChatColor.GOLD + "" + playerDeaths.get(player));
    }

    private void updateKill(Player player){
        Scoreboard board = player.getScoreboard();
        Objects.requireNonNull(board.getTeam("killCounter")).setPrefix(ChatColor.GOLD + "" + playerDeaths.get(player));
    }

    private void updateKD(Player player){
        Scoreboard board = player.getScoreboard();
        Objects.requireNonNull(board.getTeam("kdCounter")).setPrefix(ChatColor.GOLD + "" + (playerDeaths.get(player) == 0 ? "GOD" : playerKills.get(player) / playerDeaths.get(player)));
    }

    public void updateScoreboard(){
        for(Player player : Bukkit.getOnlinePlayers()){
            updatePlayer(player);
            updateDeath(player);
            updateKill(player);
            updateKD(player);
        }
    }

    public void increaseKillCounter(Player player)
    {
        playerKills.put(player, playerKills.get(player) +1);
    }

    public void increaseDeathCounter(Player player)
    {
        playerDeaths.put(player, playerDeaths.get(player) +1);
    }

    public void newPlayerKill(Player player)
    {
        playerKills.put(player, 0);
    }

    public void newPlayerDeath(Player player)
    {
        playerDeaths.put(player, 0);
    }

}
