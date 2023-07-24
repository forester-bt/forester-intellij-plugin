package com.github.besok.foresterintellijplugin.structure;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.ast;

public class FTreeFileElement extends BaseStructureViewElem {

    private PsiFile psiFile;

    public FTreeFileElement(PsiFile element) {
        super(element);
        psiFile = element;
    }

    @Override
    public TreeElement @NotNull [] getChildren() {

        TreeElement[] classes = ast(psiFile)
                .path("/file/definition")
                .stream()
                .map(BaseStructureViewElem::new)
                .toArray(TreeElement[]::new);

        return Stream.of(classes).toArray(TreeElement[]::new);
    }
}