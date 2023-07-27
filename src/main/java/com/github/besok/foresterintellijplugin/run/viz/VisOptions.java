package com.github.besok.foresterintellijplugin.run.viz;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class VisOptions extends RunConfigurationOptions {

    private final StoredProperty<String> root =
            string("").provideDelegate(this, "root");

    public String getRoot() {
        return root.getValue(this);
    }
    public void setRoot(String f) {
        root.setValue(this,f);
    }

    private final StoredProperty<String> file =
            string("").provideDelegate(this, "file");

    public String getFile() {
        return file.getValue(this);
    }
    public void setFile(String f) {
        file.setValue(this,f);
    }

    private final StoredProperty<String> tree =
            string("").provideDelegate(this, "tree");

    public String getTree() {
        return tree.getValue(this);
    }
    public void setTree(String f) {
        tree.setValue(this,f);
    }

    private final StoredProperty<String> output =
            string("").provideDelegate(this, "output");

    public String getOutput() {
        return output.getValue(this);
    }
    public void setOutput(String f) {
        output.setValue(this,f);
    }

    private final StoredProperty<Boolean> log =
            property(true).provideDelegate(this, "log");

    public Boolean getLog() {
        return log.getValue(this);
    }
    public void setLog(boolean f) {
        log.setValue(this,f);
    }

    private final StoredProperty<Boolean> fromProject =
            property(true).provideDelegate(this, "fromProject");

    public Boolean getFromProject() {
        return fromProject.getValue(this);
    }
    public void setFromProject(boolean f) {
        fromProject.setValue(this,f);
    }
    private final StoredProperty<Boolean> autodetect =
            property(true).provideDelegate(this, "autodetect");
    public Boolean isAutodetect() {
        return autodetect.getValue(this);
    }
    public void setAutodetect(boolean f) {
        autodetect.setValue(this,f);
    }
    private final StoredProperty<Boolean> outputFlag =
            property(true).provideDelegate(this, "outputFlag");

    public Boolean getOutputFlag() {
        return outputFlag.getValue(this);
    }
    public void setOutputFlag(boolean f) {
        outputFlag.setValue(this,f);
    }



}
