package net.problemzone.knockit.util;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private static final String SYSTEMPREFIX = ChatColor.GREEN + "KnockIt " + ChatColor.DARK_GRAY + "» ";

    private static final Map<LanguageKeyword, String> lang = new HashMap<>();

    static {
        lang.put(LanguageKeyword.KIT_SELECTED, "Du hast Kit %s ausgewählt");
        lang.put(LanguageKeyword.MISSING_RIGHTS, "Du hast nicht die erforderlichen Rechte!");
        lang.put(LanguageKeyword.JOIN_MESSAGE, ChatColor.GRAY + "Spielmodus KnockIt erfolgreich beigetreten!");
        lang.put(LanguageKeyword.KILL_STREAK, ChatColor.GREEN + "Du hast eine Killstreak von " + ChatColor.GOLD + "%s" + ChatColor.GREEN + " erreicht!");
        lang.put(LanguageKeyword.GLOBAL_KILLSTREAK, ChatColor.GREEN + "Der Spieler " + ChatColor.GOLD + "%s" + ChatColor.GREEN + " hat eine Killstreak von " + ChatColor.GOLD + "%d " + ChatColor.GREEN + "erreicht!");
        lang.put(LanguageKeyword.PLAYER_DEATH_BY_PLAYER, ChatColor.WHITE + "%s" + ChatColor.GRAY +  " wurde von " + ChatColor.WHITE + "%s" + ChatColor.GRAY + " runtergeknüppelt");
        lang.put(LanguageKeyword.PLAYER_DEATH, ChatColor.WHITE + "%s" + ChatColor.GRAY +  " fand einen anderen Ausweg");
    }

    public static String getStringFromKeyword(LanguageKeyword keyword){
        return format(lang.get(keyword));
    }

    private static String format(String unformattedString){
        return SYSTEMPREFIX + ChatColor.GRAY + unformattedString;
    }

}
