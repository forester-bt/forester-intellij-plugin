package com.github.besok.foresterintellijplugin.run.nav;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.NotNullLazyValue;

public class Nav2RunType extends ConfigurationTypeBase {

    static Nav2RunType INSTANCE = new Nav2RunType();

    public static final String ID = "FTreeNav2Run";

    protected Nav2RunType() {
        super(ID,
                "F-Tree Export to ROS Nav2",
                "F-Tree Export to ROS Nav2",
                NotNullLazyValue.lazy(() -> FTreeLanguage.ICON_LIGHT));
        addFactory(new Nav2RunFactory(this));
    }






}
