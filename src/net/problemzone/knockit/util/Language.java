package net.problemzone.knockit.util;

import org.bukkit.ChatColor;

public enum Language {

    KIT_SELECTED("Du hast Kit %s ausgewählt"),
    MISSING_RIGHTS("Du hast nicht die erforderlichen Rechte!"),
    JOIN_MESSAGE(ChatColor.GRAY + "Spielmodus KnockIt erfolgreich beigetreten!"),
    KILL_STREAK(ChatColor.GREEN + "Du hast eine Killstreak von " + ChatColor.GOLD + "%s" + ChatColor.GREEN + " erreicht!"),
    GLOBAL_KILLSTREAK(ChatColor.GREEN + "Der Spieler " + ChatColor.GOLD + "%s" + ChatColor.GREEN + " hat eine Killstreak von " + ChatColor.GOLD + "%d " + ChatColor.GREEN + "erreicht!"),
    PLAYER_DEATH(ChatColor.WHITE + "%s" + ChatColor.GRAY +  " fand einen anderen Ausweg"),
    PLAYER_DEATH_BY_PLAYER(ChatColor.WHITE + "%s" + ChatColor.GRAY +  " wurde von " + ChatColor.WHITE + "%s" + ChatColor.GRAY + " runtergeknüppelt"),
    GRAPPLER_COOLDOWN (ChatColor.GRAY + "Du hast 5 Sekunden Cooldown!"),
    JOIN(ChatColor.GREEN + "» " + ChatColor.WHITE + "%s"),
    QUIT(ChatColor.RED + "" + ChatColor.GRAY + "%s");

    private static final String SYSTEMPREFIX = ChatColor.GREEN + "KnockIt " + ChatColor.DARK_GRAY + "» ";

    private final String text;

    Language(String text) {
        this.text = text;
    }

    public String getFormattedText(){
        return SYSTEMPREFIX + ChatColor.GRAY + text;
    }

    public String getText(){
        return text;
    }

}
