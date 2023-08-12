package com.github.besok.foresterintellijplugin.format;

import com.github.besok.foresterintellijplugin.parser.nodes.Arg;
import com.github.besok.foresterintellijplugin.parser.nodes.Invocation;
import com.github.besok.foresterintellijplugin.parser.nodes.Lambda;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BaseBlock extends AbstractBlock {
    private final SpacingBuilder spacingBuilder;

    protected BaseBlock(ASTNode node,
                        Wrap wrap,
                        Alignment alignment,
                        SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new BaseBlock(child, Wrap.createWrap(WrapType.NONE, false), null,
                        spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        if (myNode.getPsi() instanceof Lambda) {
            return Indent.getNormalIndent();
        } else if (myNode.getPsi() instanceof Invocation) {
            return Indent.getNormalIndent();
        } else if (myNode.getPsi() instanceof Arg) {
            return Indent.getNormalIndent();
        } else {
            return Indent.getNoneIndent();
        }

    }

    @Override
    public Spacing getSpacing(Block child1, Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    @Override
    public @NotNull ChildAttributes getChildAttributes(int newChildIndex) {
        Indent indent = null;
        if (myNode.getPsi() instanceof Lambda) {
            indent= Indent.getNormalIndent();
        } else if (myNode.getPsi() instanceof Invocation) {
            indent= Indent.getNormalIndent();
        } else if (myNode.getPsi() instanceof Arg) {
            indent= Indent.getNormalIndent();
        } else {
            indent= Indent.getAbsoluteNoneIndent();
        }
        return new ChildAttributes(indent,null);
    }
}
