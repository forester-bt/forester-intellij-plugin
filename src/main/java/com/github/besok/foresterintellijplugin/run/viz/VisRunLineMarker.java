package com.github.besok.foresterintellijplugin.run.viz;

import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.execution.*;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.ConfigurationFromContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.MapDataContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VisRunLineMarker extends RunLineMarkerContributor {
    @Override
    public @Nullable Info getInfo(@NotNull PsiElement element) {
        if (element instanceof TreeDef) {
            String type = element.getFirstChild().getText();
            if (type.equals("root")) {
                var project = element.getProject();
                return new Info(AllIcons.RunConfigurations.TestState.Run, (e) -> "Root Tree Visualization",


                        new AnAction() {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent e) {
                        ConfigurationType type = ConfigurationTypeUtil.findConfigurationType(VizRunType.ID);

                        if (type == null) {
                            return;
                        }

                        ConfigurationFactory[] factories = type.getConfigurationFactories();
                        if (factories.length == 0) {
                            return;
                        }
                        VizRunFactory configurationFactory = (VizRunFactory) factories[0];
                        VizRun runC = (VizRun) configurationFactory.createTemplateConfiguration(project);

                        String treeName = element.getChildren()[1].getText();
                        String fileName = element.getContainingFile().getName();


                        runC.setName("Visualize " + fileName + "#" + treeName);
                        VisOptions options = runC.getOptions();
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
