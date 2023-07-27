package com.github.besok.foresterintellijplugin.run.sim;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.run.viz.VizRunFactory;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.SimpleConfigurationType;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimRunType extends ConfigurationTypeBase {

    public static SimRunType INSTANCE = new SimRunType();
    public static final String ID = "FTreeSimRun";
    protected SimRunType() {
        super(ID,
                "F-tree Simulation",
                "Simulation process of Forester F-tree language",
                NotNullLazyValue.lazy(() -> FTreeLanguage.ICON_LIGHT));
        addFactory(new SimRunFactory(this));
    }







}
