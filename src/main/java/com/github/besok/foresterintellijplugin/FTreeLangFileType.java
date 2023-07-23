package com.github.besok.foresterintellijplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FTreeLangFileType extends LanguageFileType {
    public static final FTreeLangFileType INSTANCE = new FTreeLangFileType();

    protected FTreeLangFileType() {
        super(FTreeLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "FTree";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "FTree language";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "tree";
    }

    @Override
    public Icon getIcon() {
        if (UIUtil.isUnderDarcula()) return FTreeLanguage.ICON_DARK;
        else return FTreeLanguage.ICON_LIGHT;
    }
}
