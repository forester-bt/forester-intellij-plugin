package com.github.besok.foresterintellijplugin.parser.nodes;

import com.github.besok.foresterintellijplugin.FTreeLangFileType;
import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class FTreeFile extends PsiFileBase {
    public FTreeFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, FTreeLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return FTreeLangFileType.INSTANCE;
    }

}
