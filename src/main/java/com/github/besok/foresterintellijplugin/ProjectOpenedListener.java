package com.github.besok.foresterintellijplugin;

import com.github.besok.foresterintellijplugin.ftree.FTreeService;
import com.github.besok.foresterintellijplugin.ftree.Version;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ex.ApplicationEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

public class ProjectOpenedListener implements ProjectManagerListener {
    private static final Logger LOGGER = Logger.getInstance(ProjectOpenedListener.class);

    @Override
    public void projectOpened(@NotNull Project project) {
        var treeService = new FTreeService();

        var vOpt = treeService.version();
        if (vOpt.isEmpty()) {
            ForesterNotifier
                    .notifyWarning(project, "Can't find f-tree. Please run 'cargo install f-tree'");
        }else {
            Version version = vOpt.get();
            if(version.compareTo(FTreeService.VERSION) < 0){
                ForesterNotifier
                        .notifyWarning(project, "f-tree has an outdated version. Please run 'cargo install f-tree'");
            }
        }
    }
}
