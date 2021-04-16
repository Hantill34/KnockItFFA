package net.problemzone.knockit.kitmanager;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import java.util.ArrayList;

public class kitmanager implements Listener
{
    public ArrayList<kit> kits;

    public kitmanager(String angler, int i, Material paper)
    {
        kits = new ArrayList<kit>();
    }

}
