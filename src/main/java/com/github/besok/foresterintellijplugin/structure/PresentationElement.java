package com.github.besok.foresterintellijplugin.structure;

import com.github.besok.foresterintellijplugin.parser.FTreeAstUtils;
import com.github.besok.foresterintellijplugin.parser.nodes.Invocation;
import com.github.besok.foresterintellijplugin.parser.nodes.Lambda;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PresentationElement implements ItemPresentation {
    private final PsiElement element;

    public PresentationElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public @Nullable String getPresentableText() {

        if (element instanceof TreeDef) {
            return headText(element, "/definition/id");
        } else if (element instanceof Lambda) {
            return headText(element, "/lambda/tree_type");
        } else if (element instanceof Invocation) {
            return headText(element, "/invocation/id");
        } else {
            return defaultText(element);
        }
    }

    @Override
    public @Nullable Icon getIcon(boolean unused) {

        if (element instanceof TreeDef) {
            String text = element.getFirstChild().getText();
            if (text.equals("cond")) {
                return AllIcons.Nodes.ModelClass;
            }else if(text.equals("impl")){
                return AllIcons.Nodes.ModelClass;
            }else{
                return AllIcons.Nodes.Class;
            }
        } else if (element instanceof Lambda) {
            return AllIcons.Nodes.Lambda;
        } else {
            return AllIcons.Nodes.Function;
        }
    }

    private String defaultText(PsiElement element) {
        return element.getNode().getElementType().toString();
    }

    private String headText(PsiElement element, String path) {
        return FTreeAstUtils
                .ast(element)
                .path(path)
                .stream()
                .findFirst()
                .map(PsiElement::getText)
                .orElse(defaultText(element));
    }

}