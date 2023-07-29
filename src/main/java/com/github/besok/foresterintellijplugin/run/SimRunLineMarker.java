package com.github.besok.foresterintellijplugin.run;

import com.github.besok.foresterintellijplugin.parser.FTreeAstUtils;
import com.github.besok.foresterintellijplugin.parser.nodes.StaticType;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.github.besok.foresterintellijplugin.run.sim.SimOptions;
import com.github.besok.foresterintellijplugin.run.sim.SimRun;
import com.github.besok.foresterintellijplugin.run.sim.SimRunFactory;
import com.github.besok.foresterintellijplugin.run.sim.SimRunType;
import com.github.besok.foresterintellijplugin.run.viz.VisOptions;
import com.github.besok.foresterintellijplugin.run.viz.VizRun;
import com.github.besok.foresterintellijplugin.run.viz.VizRunFactory;
import com.github.besok.foresterintellijplugin.run.viz.VizRunType;
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

public class SimRunLineMarker extends RunLineMarkerContributor {


    @Override
    public @Nullable Info getInfo(@NotNull PsiElement element) {
        if (element instanceof StaticType) {
            if (element.getText().equals("root")) {
                var project = element.getProject();
                return new Info(AllIcons.RunConfigurations.TestState.Run, (e) -> "Root Tree",
                        new AnAction("Visualize the Tree") {

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
                                FTreeAstUtils.ast(element).up().up().id().ifPresent(id -> {
                                    String treeName = id.getText();
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
                                });

                            }
                        },
                        new AnAction("Run the Default Simulation") {


                            @Override
                            public void actionPerformed(@NotNull AnActionEvent e) {
                                ConfigurationType type = ConfigurationTypeUtil.findConfigurationType(SimRunType.ID);

                                if (type == null) {
                                    return;
                                }

                                ConfigurationFactory[] factories = type.getConfigurationFactories();
                                if (factories.length == 0) {
                                    return;
                                }
                                SimRunFactory configurationFactory = (SimRunFactory) factories[0];
                                SimRun runC = (SimRun) configurationFactory.createTemplateConfiguration(project);

                                FTreeAstUtils.ast(element).up().up().id().ifPresent(id -> {
                                    String treeName = id.getText();
                                    String fileName = element.getContainingFile().getName();
                                    runC.setName("Simulation " + fileName + "#" + treeName);
                                    SimOptions options = runC.getOptions();
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
                                });


                            }
                        });
            }
        }
        return null;
    }
}
