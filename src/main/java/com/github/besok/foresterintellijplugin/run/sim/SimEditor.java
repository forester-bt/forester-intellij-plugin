package com.github.besok.foresterintellijplugin.run.sim;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class SimEditor extends SettingsEditor<SimRun> {


    private TextFieldWithBrowseButton rootV;
    private JCheckBox autodetectCheckBox;
    private JCheckBox projectRootCheckBox;
    private TextFieldWithBrowseButton fileV;
    private JTextField treeV;
    private TextFieldWithBrowseButton profile;
    private JCheckBox loggingCheckBox;

    private JPanel myPanel;


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

        autodetectCheckBox.addChangeListener((l) -> {
            JCheckBox source = (JCheckBox) l.getSource();
            treeV.setEnabled(!source.isSelected());
        });
        projectRootCheckBox.addChangeListener((l) -> {
            JCheckBox source = (JCheckBox) l.getSource();
            rootV.setEnabled(!source.isSelected());
        });

        myPanel = FormBuilder
                .createFormBuilder()
                .addLabeledComponent("Profile", profile).addSeparator()
                .addLabeledComponent("Root", rootV).addComponentToRightColumn(projectRootCheckBox).addSeparator()
                .addLabeledComponent("File", fileV).addSeparator()
                .addLabeledComponent("Tree", treeV).addComponentToRightColumn(autodetectCheckBox).addSeparator()
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
    }

    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

}

