package com.github.besok.foresterintellijplugin.run.viz;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class VisEditor extends SettingsEditor<VizRun> {


    private TextFieldWithBrowseButton rootV;
    private JCheckBox autodetectCheckBox;
    private JCheckBox projectRootCheckBox;
    private TextFieldWithBrowseButton fileV;
    private JTextField treeV;
    private JTextField output;
    private JCheckBox outputFlag;
    private JCheckBox loggingCheckBox;

    private JPanel myPanel;


    public VisEditor() {
        rootV = new TextFieldWithBrowseButton();
        fileV = new TextFieldWithBrowseButton();

        rootV.addBrowseFolderListener("Root Folder", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());
        fileV.addBrowseFolderListener("Main File", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());

        treeV = new JTextField();
        output = new JTextField();
        autodetectCheckBox = new JCheckBox("Autodetect");
        projectRootCheckBox = new JCheckBox("Project Root");
        loggingCheckBox = new JCheckBox();
        outputFlag = new JCheckBox("Main File Name");

        autodetectCheckBox.addChangeListener((l) -> {
            JCheckBox source = (JCheckBox) l.getSource();
            treeV.setEnabled(!source.isSelected());
        });
        projectRootCheckBox.addChangeListener((l) -> {
            JCheckBox source = (JCheckBox) l.getSource();
            rootV.setEnabled(!source.isSelected());
        });
        outputFlag.addChangeListener((l) -> {
            JCheckBox source = (JCheckBox) l.getSource();
            output.setEnabled(!source.isSelected());
        });

        myPanel = FormBuilder
                .createFormBuilder()
                .addLabeledComponent("Root", rootV).addComponentToRightColumn(projectRootCheckBox).addSeparator()
                .addLabeledComponent("File", fileV).addSeparator()
                .addLabeledComponent("Tree", treeV).addComponentToRightColumn(autodetectCheckBox).addSeparator()
                .addLabeledComponent("Output", output).addComponentToRightColumn(outputFlag).addSeparator()
                .addLabeledComponent("Logging", loggingCheckBox)
                .getPanel();
    }


    @Override
    protected void resetEditorFrom(@NotNull VizRun s) {
        rootV.setText(s.getOptions().getRoot());
        fileV.setText(s.getOptions().getFile());
        treeV.setText(s.getOptions().getTree());
        output.setText(s.getOptions().getOutput());
        autodetectCheckBox.setSelected(s.getOptions().getAutodetect());
        projectRootCheckBox.setSelected(s.getOptions().getFromProject());
        loggingCheckBox.setSelected(s.getOptions().getLog());
        outputFlag.setSelected(s.getOptions().getOutputFlag());
    }

    @Override
    protected void applyEditorTo(@NotNull VizRun s) throws ConfigurationException {
        s.getOptions().setRoot(rootV.getText());
        s.getOptions().setFile(fileV.getText());
        s.getOptions().setTree(treeV.getText());
        s.getOptions().setOutput(output.getText());
        s.getOptions().setAutodetect(autodetectCheckBox.isSelected());
        s.getOptions().setLog(loggingCheckBox.isSelected());
        s.getOptions().setFromProject(projectRootCheckBox.isSelected());
        s.getOptions().setOutputFlag(outputFlag.isSelected());
    }

    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

}

