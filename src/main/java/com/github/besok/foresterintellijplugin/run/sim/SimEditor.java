package com.github.besok.foresterintellijplugin.run.sim;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class SimEditor extends SettingsEditor<SimRun> {


    private final TextFieldWithBrowseButton rootV;
    private final JCheckBox autodetectCheckBox;
    private final JCheckBox projectRootCheckBox;
    private final TextFieldWithBrowseButton fileV;
    private final JTextField treeV;
    private final TextFieldWithBrowseButton profile;
    private final JCheckBox defProfile;
    private final JCheckBox loggingCheckBox;

    private final JPanel myPanel;


    public SimEditor() {
        rootV = new TextFieldWithBrowseButton();
        fileV = new TextFieldWithBrowseButton();
        profile = new TextFieldWithBrowseButton();

        rootV.addBrowseFolderListener("Root Folder", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());
        fileV.addBrowseFolderListener("Main File", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());
        profile.addBrowseFolderListener("Simulation Profile", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());

        treeV = new JTextField();
        autodetectCheckBox = new JCheckBox("Autodetect");
        projectRootCheckBox = new JCheckBox("Project Root");
        loggingCheckBox = new JCheckBox();
        defProfile = new JCheckBox("Default profile");

        autodetectCheckBox.addItemListener((l) -> {
            treeV.setVisible(!treeV.isVisible());
        });
        projectRootCheckBox.addItemListener((l) -> {
            rootV.setVisible(!rootV.isVisible());
        });
        defProfile.addItemListener((l) -> {
            profile.setVisible(!profile.isVisible());
        });

        myPanel = FormBuilder
                .createFormBuilder()
                .addLabeledComponent("Root folder", projectRootCheckBox).addComponentToRightColumn(rootV).addSeparator()
                .addLabeledComponent("Main file", fileV).addSeparator()
                .addLabeledComponent("Main tree", autodetectCheckBox).addComponentToRightColumn(treeV).addSeparator()
                .addLabeledComponent("Sim profile", defProfile).addComponentToRightColumn( profile).addSeparator()
                .addLabeledComponent("Logging", loggingCheckBox)
                .getPanel();
    }


    @Override
    protected void resetEditorFrom(@NotNull SimRun s) {
        rootV.setText(s.getOptions().getRoot());
        fileV.setText(s.getOptions().getFile());
        treeV.setText(s.getOptions().getTree());
        profile.setText(s.getOptions().getProfile());
        autodetectCheckBox.setSelected(s.getOptions().getAutodetect());
        projectRootCheckBox.setSelected(s.getOptions().getFromProject());
        loggingCheckBox.setSelected(s.getOptions().getLog());
        defProfile.setSelected(s.getOptions().getDefProfile());
    }

    @Override
    protected void applyEditorTo(@NotNull SimRun s) throws ConfigurationException {
        s.getOptions().setRoot(rootV.getText());
        s.getOptions().setFile(fileV.getText());
        s.getOptions().setTree(treeV.getText());
        s.getOptions().setProfile(profile.getText());
        s.getOptions().setAutodetect(autodetectCheckBox.isSelected());
        s.getOptions().setLog(loggingCheckBox.isSelected());
        s.getOptions().setFromProject(projectRootCheckBox.isSelected());
        s.getOptions().setDefProfile(defProfile.isSelected());
    }

    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

}

