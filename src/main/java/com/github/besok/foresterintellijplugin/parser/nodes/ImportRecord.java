package com.github.besok.foresterintellijplugin.parser.nodes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class ImportRecord extends FTreeNode {
    public ImportRecord(@NotNull ASTNode node, @NotNull IElementType idElementType) {
        super(node, idElementType);
    }
}
