package com.github.besok.foresterintellijplugin.parser.nodes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class TreeDef extends FTreeNode {
    public TreeDef(@NotNull ASTNode node, @NotNull IElementType idElementType) {
        super(node, idElementType);
    }
}
