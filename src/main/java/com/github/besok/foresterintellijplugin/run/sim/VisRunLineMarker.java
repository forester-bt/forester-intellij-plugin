package com.github.besok.foresterintellijplugin.run.sim;

import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VisRunLineMarker extends RunLineMarkerContributor {
    @Override
    public @Nullable Info getInfo(@NotNull PsiElement element) {
        if(element instanceof TreeDef){
            String type = element.getFirstChild().getText();
            if(type.equals("root")){
                return new Info(AllIcons.RunConfigurations.TestState.Run, (e) -> "Root Tree Definition", new AnAction() {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent e) {

                    }
                });
            }
        }
        return null;
    }
}
