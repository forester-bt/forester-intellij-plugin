package com.github.besok.foresterintellijplugin.run.sim;

import com.github.besok.foresterintellijplugin.run.viz.VisOptions;
import com.github.besok.foresterintellijplugin.run.viz.VizRun;
import com.github.besok.foresterintellijplugin.run.viz.VizRunType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimRunFactory extends ConfigurationFactory {
    protected SimRunFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public @NotNull String getId() {
        return SimRunType.ID;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(
            @NotNull Project project) {
        return new SimRun(project, this, "FTreeViz");
    }

    @Override
    public @Nullable Class<? extends BaseState> getOptionsClass() {
        return SimOptions.class;
    }
}
