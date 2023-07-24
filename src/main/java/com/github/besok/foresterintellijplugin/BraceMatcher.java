package com.github.besok.foresterintellijplugin;

import com.github.besok.foresterintellijplugin.gramma.TreeLexer;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.tt;

public class BraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {
        return new BracePair[]{
                new BracePair(tt(TreeLexer.LPR), tt(TreeLexer.RPR), false),
                new BracePair(tt(TreeLexer.LBR), tt(TreeLexer.RBR), false),
                new BracePair(tt(TreeLexer.LBC), tt(TreeLexer.RBC), false),
        };
    }


    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
