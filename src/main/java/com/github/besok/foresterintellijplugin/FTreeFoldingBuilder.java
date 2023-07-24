package com.github.besok.foresterintellijplugin;

import com.github.besok.foresterintellijplugin.parser.nodes.Call;
import com.github.besok.foresterintellijplugin.parser.nodes.Lambda;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Stream;

import static com.github.besok.foresterintellijplugin.gramma.TreeParser.RULE_definition;
import static com.github.besok.foresterintellijplugin.gramma.TreeParser.RULE_lambda;
import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.ast;
import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.isRule;

public class FTreeFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {

        return Stream.of(
                        findBy(root, Lambda.class),
                        findBy(root, TreeDef.class))
                .flatMap(Collection::stream)
                .filter(e -> e.getTextLength() > 0)
                .map(this::createFolding)
                .toArray(FoldingDescriptor[]::new);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        if (isRule(node, RULE_definition)) {
            String type = node.getFirstChildNode().getText();
            String name = ast(node.getPsi()).id().map(LeafElement::getText).orElse("");
            return type + " " + name + " {..}";
        } else if(isRule(node, RULE_lambda)){
            return node.getFirstChildNode().getText() + " {..}";
        } else{
            return node.getElementType().toString();
        }
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }


    private @NotNull <T extends PsiElement> Collection<T> findBy(PsiElement root, Class<T> clazz) {
        return PsiTreeUtil.findChildrenOfType(root, clazz);
    }

    private FoldingDescriptor createFolding(PsiElement node) {
        return new FoldingDescriptor(node.getNode(), node.getTextRange());
    }
}
