package knockit.kitmanager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class angler extends kit
{

    public angler(String name, int price, Material mat)
    {
        super(ChatColor.AQUA + "Angler", 0, Material.FISHING_ROD);
    }

    @Override
    public void equip(Player p)
    {
        p.getInventory().clear();
        p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        e.getPlayer().sendMessage();
    }

}
