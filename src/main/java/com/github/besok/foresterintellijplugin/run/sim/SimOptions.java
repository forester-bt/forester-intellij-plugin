package com.github.besok.foresterintellijplugin.run.sim;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class SimOptions extends RunConfigurationOptions {

     public final StoredProperty<String> root =
            string("").provideDelegate(this, "root");

    public String getRoot() {
        return root.getValue(this);
    }
    public void setRoot(String f) {
        root.setValue(this,f);
    }

    public final StoredProperty<String> file =
            string("").provideDelegate(this, "file");

    public String getFile() {
        return file.getValue(this);
    }
    public void setFile(String f) {
        file.setValue(this,f);
    }

    public final StoredProperty<String> tree =
            string("").provideDelegate(this, "tree");

    public String getTree() {
        return tree.getValue(this);
    }
    public void setTree(String f) {
        tree.setValue(this,f);
    }

    public final StoredProperty<String> profile =
            string("").provideDelegate(this, "output");

    public String getProfile() {
        return profile.getValue(this);
    }
    public void setProfile(String f) {
        profile.setValue(this,f);
    }

    public final StoredProperty<Boolean> log =
            property(true).provideDelegate(this, "log");

    public Boolean getLog() {
        return log.getValue(this);
    }
    public void setLog(boolean f) {
        log.setValue(this,f);
    }

    public final StoredProperty<Boolean> fromProject =
            property(true).provideDelegate(this, "fromProject");

    public Boolean getFromProject() {
        return fromProject.getValue(this);
    }
    public void setFromProject(boolean f) {
        fromProject.setValue(this,f);
    }
    public final StoredProperty<Boolean> autodetect =
            property(true).provideDelegate(this, "autodetect");
    public Boolean getAutodetect() {
        return autodetect.getValue(this);
    }
    public void setAutodetect(boolean f) {
        autodetect.setValue(this,f);
    }


}