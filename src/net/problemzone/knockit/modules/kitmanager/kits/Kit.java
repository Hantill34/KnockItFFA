package net.problemzone.knockit.modules.kitmanager.kits;


import net.problemzone.knockit.util.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Kit implements Listener {

    private final String name;
    private final int price;
    private final Material mat;

    public Kit(String name, int price, Material mat) {
        this.name = name;
        this.price = price;
        this.mat = mat;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ItemStack getPreviewItem() {
        return new ItemStackBuilder(mat, name).getItemStack();
    }

    public abstract void equip(Player p);


}