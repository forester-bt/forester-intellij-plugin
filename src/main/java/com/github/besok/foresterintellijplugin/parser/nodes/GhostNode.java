package com.github.besok.foresterintellijplugin.parser.nodes;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.psi.impl.source.tree.CompositePsiElement;
import com.intellij.psi.tree.IElementType;


/**
 * The node represents an empty node that does not exist in the tree and summoned to stand for the None analog.
 */
public class GhostNode extends FTreeNode {
    public GhostNode() {
        super(
                new CompositePsiElement(new IElementType("ghost", FTreeLanguage.INSTANCE)) {
                },
                new IElementType("ghost", FTreeLanguage.INSTANCE)
        );
    }
}
