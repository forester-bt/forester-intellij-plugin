package com.github.besok.foresterintellijplugin;

import com.intellij.lang.Language;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class FTreeLanguage extends Language {
    public static Icon ICON_LIGHT = IconLoader.getIcon("/META-INF/pluginIcon.svg", FTreeLanguage.class);
    public static Icon ICON_DARK = IconLoader.getIcon("/META-INF/pluginIcon_dark.svg", FTreeLanguage.class);
    public static Language INSTANCE = new FTreeLanguage();



    protected FTreeLanguage() {
        super(("FTree"));
    }
}
