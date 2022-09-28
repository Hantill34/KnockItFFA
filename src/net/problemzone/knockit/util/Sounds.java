package net.problemzone.knockit.util;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public enum Sounds {
    CLICK_TIMER(Sound.BLOCK_NOTE_BLOCK_HAT, 1),
    CLICK_TIMER_LAST_3(Sound.BLOCK_NOTE_BLOCK_BASS, 1),
    CLICK_TIMER_END(Sound.BLOCK_NOTE_BLOCK_HARP, 2);

    private final Sound sound;
    private final float pitch;

    Sounds(Sound sound, float pitch) {
        this.sound = sound;
        this.pitch = pitch;
    }

    public void playSoundForPlayer(Player player){
        player.playSound(player.getLocation(), sound, SoundCategory.AMBIENT, 1, pitch);
    }
}
