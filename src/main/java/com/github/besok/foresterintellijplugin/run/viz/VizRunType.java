package com.github.besok.foresterintellijplugin.run.viz;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.NotNullLazyValue;

public class VizRunType extends ConfigurationTypeBase {

    static VizRunType INSTANCE = new VizRunType();

    public static final String ID = "FTreeVisRun";

    protected VizRunType() {
        super(ID,
                "F-Tree Visualization",
                "Visualization of Forester F-tree language",
                NotNullLazyValue.lazy(() -> FTreeLanguage.ICON_LIGHT));
        addFactory(new VizRunFactory(this));
    }






}
