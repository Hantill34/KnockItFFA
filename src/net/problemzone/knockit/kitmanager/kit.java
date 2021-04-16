package net.problemzone.knockit.kitmanager;


import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class kit implements Listener {

    private String name;
    private int preis;
    private Material mat;

    public kit(String name, int price, Material mat) {

        this.name = name;
        this.preis = preis;
        this.mat = mat;

    }

    public String getName()
    {
        return name;
    }

    public int getPreis()
    {
        return preis;
    }

    public ItemStack getItem()
    {
        ItemStack it = new ItemStack(mat);
        ItemMeta m =  it.getItemMeta();
        m.setDisplayName(name);
        it.setItemMeta(m);
        return it;
    }


}