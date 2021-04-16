package net.problemzone.knockit.kitmanager;

import net.problemzone.knockit.util.Language;
import net.problemzone.knockit.util.LanguageKeyword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Angler extends kit
{

    public Angler()
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
        e.getPlayer().sendMessage(String.format(Language.getStringFromKeyword(LanguageKeyword.KIT_SELECTED), ChatColor.AQUA + "Angler"));

    }

}