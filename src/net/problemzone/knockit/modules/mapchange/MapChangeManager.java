package net.problemzone.knockit.modules.mapchange;

import net.problemzone.knockit.Main;
import net.problemzone.knockit.util.Language;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class MapChangeManager {

    public static ArrayList<String> maps = new ArrayList<String>();

    public static boolean forcemap = false;
    public static int map = 0;
    public static int i = 0;
    public static int countdown = 601;
    public static String winner = "";
    int spawn = 1;

    public static void mapChange(){

    }

}
