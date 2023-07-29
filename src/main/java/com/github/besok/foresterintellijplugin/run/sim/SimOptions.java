package com.github.besok.foresterintellijplugin.run.sim;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class SimOptions extends RunConfigurationOptions {

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

    private final StoredProperty<String> profile =
            string("").provideDelegate(this, "output");

    public String getProfile() {
        return profile.getValue(this);
    }
    public void setProfile(String f) {
        profile.setValue(this,f);
    }

    private final StoredProperty<Boolean> defProfile =
            property(true).provideDelegate(this, "defProfile");

    public Boolean getDefProfile() {
        return defProfile.getValue(this);
    }
    public void setDefProfile(boolean f) {
        defProfile.setValue(this,f);
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
            property(true). provideDelegate(this, "autodetect");
    public Boolean getAutodetect() {
        return autodetect.getValue(this);
    }
    public void setAutodetect(boolean f) {
        autodetect.setValue(this,f);
    }


}
