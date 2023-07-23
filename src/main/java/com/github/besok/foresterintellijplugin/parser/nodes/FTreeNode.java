package com.github.besok.foresterintellijplugin.parser.nodes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;

// The root for all nodes in the wren parser
public abstract class FTreeNode extends IdentifierDefSubtree {
    public FTreeNode(@NotNull ASTNode node, @NotNull IElementType idElementType) {
        super(node, idElementType);
    }
}
