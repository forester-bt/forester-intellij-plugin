package com.github.besok.foresterintellijplugin.run.viz;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.SimpleConfigurationType;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VizRunType extends SimpleConfigurationType {

    static VizRunType INSTANCE = new VizRunType();



    protected VizRunType() {
        super("f-tree-viz",
                "F-tree Visualization",
                "Visualization of Forester F-tree language into graph",
                NotNullLazyValue.lazy(() -> FTreeLanguage.ICON_LIGHT));
    }

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new VizRun(project,this,"FTreeViz");
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
        return VisOptions.class;
    }
}
