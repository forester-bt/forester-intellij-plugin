package com.github.besok.foresterintellijplugin.run.nav;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Nav2RunFactory extends ConfigurationFactory {
    protected Nav2RunFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public @NotNull String getId() {
        return Nav2RunType.ID;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(
            @NotNull Project project) {
        return new Nav2Run(project, this, "FTreeToNav2");
    }

    @Override
    public @Nullable Class<? extends BaseState> getOptionsClass() {
        return Nav2Options.class;
    }
}
