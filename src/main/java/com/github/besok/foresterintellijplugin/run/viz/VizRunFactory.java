package com.github.besok.foresterintellijplugin.run.viz;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VizRunFactory extends ConfigurationFactory {
    protected VizRunFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public @NotNull String getId() {
        return VizRunType.ID;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(
            @NotNull Project project) {
        return new VizRun(project, this, "FTreeViz");
    }

    @Override
    public @Nullable Class<? extends BaseState> getOptionsClass() {
        return VisOptions.class;
    }
}
