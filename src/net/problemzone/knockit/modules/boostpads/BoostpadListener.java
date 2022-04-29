package net.problemzone.knockit.modules.boostpads;


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class BoostpadListener implements Listener {

    private final static double BOOST_PAD_STRENGTH = 1.4;

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.getLocation().getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE){
            if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.REDSTONE_BLOCK) {
                Vector v = player.getLocation().getDirection().multiply(3D).setY(BOOST_PAD_STRENGTH);
                player.setVelocity(v);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
            }
        }
    }

}
