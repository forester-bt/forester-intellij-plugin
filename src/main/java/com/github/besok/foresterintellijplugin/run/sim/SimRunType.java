package com.github.besok.foresterintellijplugin.run.sim;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.SimpleConfigurationType;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimRunType extends SimpleConfigurationType {

    static SimRunType INSTANCE = new SimRunType();

    protected SimRunType() {
        super("f-tree-sim",
                "F-tree Simulation",
                "Simulation process of Forester F-tree language",
                NotNullLazyValue.lazy(() -> FTreeLanguage.ICON_LIGHT));
    }

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new SimRun(project,this,"FTreeSim");
    }


    @Override
    public @NonNls @Nullable String getHelpTopic() {
        return super.getHelpTopic();
    }

    @Override
    public boolean isManaged() {
        return super.isManaged();
    }

    @Override
    public @Nullable Class<? extends BaseState> getOptionsClass() {
        return SimOptions.class;
    }
}
