package net.problemzone.knockit.util;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private static final String SYSTEMPREFIX = ChatColor.GOLD + "KnockIt " + ChatColor.DARK_GRAY + "» ";

    private static final Map<LanguageKeyword, String> lang = new HashMap<>();

    static {
        lang.put(LanguageKeyword.KIT_SELECTED, "Du hast Kit %s ausgewählt");
    }

    public static String getStringFromKeyword(LanguageKeyword keyword){
        return format(lang.get(keyword));
    }

    public static String format(String unformattedString){
        return SYSTEMPREFIX + ChatColor.GRAY + unformattedString;
    }

}
