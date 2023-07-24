package com.github.besok.foresterintellijplugin.structure;

import com.github.besok.foresterintellijplugin.parser.nodes.Lambda;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.ast;

public class BaseStructureViewElem implements SortableTreeElement, StructureViewTreeElement {

    private PsiElement element;

    @Override
    public TreeElement @NotNull [] getChildren() {
        if (element instanceof TreeDef) {

            TreeElement[] invocations = ast(element)
                    .path("/definition/calls/call/invocation")
                    .stream()
                    .map(BaseStructureViewElem::new)
                    .toArray(TreeElement[]::new);

            TreeElement[] lambdas = ast(element)
                    .path("/definition/calls/call/lambda")
                    .stream()
                    .map(BaseStructureViewElem::new)
                    .toArray(TreeElement[]::new);


            return Stream.concat(Stream.of(lambdas), Stream.of(invocations)).toArray(TreeElement[]::new);
        } else if (element instanceof Lambda){
            TreeElement[] invocations = ast(element)
                    .path("/lambda/calls/call/invocation")
                    .stream()
                    .map(BaseStructureViewElem::new)
                    .toArray(TreeElement[]::new);

            TreeElement[] lambdas = ast(element)
                    .path("/lambda/calls/call/lambda")
                    .stream()
                    .map(BaseStructureViewElem::new)
                    .toArray(TreeElement[]::new);
            return Stream.concat(Stream.of(lambdas), Stream.of(invocations)).toArray(TreeElement[]::new);
        } else {
            return new TreeElement[0];
        }
    }

    public BaseStructureViewElem(PsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public @NotNull String getAlphaSortKey() {
        if (element instanceof PsiNamedElement namedElement) {
            return Optional.ofNullable(namedElement.getName()).orElse("");
        } else {
            return "";
        }
    }

    @Override
    public @NotNull ItemPresentation getPresentation() {
        return new PresentationElement(element);
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (element instanceof NavigationItem navItem) {
            navItem.navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        if (element instanceof NavigationItem navItem) {
            return navItem.canNavigate();
        } else {
            return false;
        }
    }

    @Override
    public boolean canNavigateToSource() {
        if (element instanceof NavigationItem navItem) {
            return navItem.canNavigateToSource();
        } else {
            return false;
        }
    }
}