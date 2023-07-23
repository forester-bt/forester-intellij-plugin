package com.github.besok.foresterintellijplugin.parser.nodes;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.gramma.TreeParser;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;

import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.rt;


public class Id extends ANTLRPsiLeafNode implements PsiNamedElement {
    private IElementType tpe;
    private String text;

    public Id(IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiElement findElem = Trees.createLeafFromText(getProject(), FTreeLanguage.INSTANCE, this, name, rt(TreeParser.RULE_id));
        return Optional
                .ofNullable(findElem)
                .map(this::replace)
                .orElse(this);

    }
}



