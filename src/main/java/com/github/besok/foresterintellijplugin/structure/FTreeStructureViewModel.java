package com.github.besok.foresterintellijplugin.structure;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;


public class FTreeStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public FTreeStructureViewModel(@NotNull PsiFile psiFile, @NotNull StructureViewTreeElement root) {
        super(psiFile, root);
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }
}
