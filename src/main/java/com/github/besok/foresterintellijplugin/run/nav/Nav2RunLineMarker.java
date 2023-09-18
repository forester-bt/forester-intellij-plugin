package com.github.besok.foresterintellijplugin.run.nav;

import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Nav2RunLineMarker extends RunLineMarkerContributor {
    @Override
    public @Nullable Info getInfo(@NotNull PsiElement element) {
        if (element instanceof TreeDef) {
            String type = element.getFirstChild().getText();
            if (type.equals("root")) {
                var project = element.getProject();
                return new Info(AllIcons.RunConfigurations.TestState.Run, (e) -> "Root Tree Export To ROS Nav2",


                        new AnAction() {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent e) {
                        ConfigurationType type = ConfigurationTypeUtil.findConfigurationType(Nav2RunType.ID);

                        if (type == null) {
                            return;
                        }

                        ConfigurationFactory[] factories = type.getConfigurationFactories();
                        if (factories.length == 0) {
                            return;
                        }
                        Nav2RunFactory configurationFactory = (Nav2RunFactory) factories[0];
                        Nav2Run runC = (Nav2Run) configurationFactory.createTemplateConfiguration(project);

                        String treeName = element.getChildren()[1].getText();
                        String fileName = element.getContainingFile().getName();


                        runC.setName("Export " + fileName + "#" + treeName + " to ROS Nav2");
                        Nav2Options options = runC.getOptions();
                        options.setFile(element.getContainingFile().getVirtualFile().getPath());
                        options.setTree(treeName);
                        options.setAutodetect(false);

                        RunnerAndConfigurationSettings configuration = RunManager.getInstance(project)
                                .createConfiguration(
                                        runC,
                                        configurationFactory);
                        RunManager.getInstance(project).addConfiguration(configuration);
                        ExecutionUtil.runConfiguration(
                                configuration,
                                DefaultRunExecutor.getRunExecutorInstance());
                    }
                });
            }
        }
        return null;
    }
}
