package com.github.besok.foresterintellijplugin.run.nav;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class Nav2Editor extends SettingsEditor<Nav2Run> {


    private final TextFieldWithBrowseButton rootV;
    private final JCheckBox autodetectCheckBox;
    private final JCheckBox projectRootCheckBox;
    private final TextFieldWithBrowseButton fileV;
    private final JTextField treeV;
    private final JTextField output;
    private final JCheckBox outputFlag;
    private final JCheckBox loggingCheckBox;

    private final JPanel myPanel;


    public Nav2Editor() {
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


        autodetectCheckBox.addItemListener((l) -> treeV.setVisible(!treeV.isVisible()));
        projectRootCheckBox.addItemListener((l) -> rootV.setVisible(!rootV.isVisible()));
        outputFlag.addItemListener((l) -> output.setVisible(!output.isVisible()));

        myPanel = FormBuilder
                .createFormBuilder()
                .addLabeledComponent("Root folder", projectRootCheckBox).addComponentToRightColumn(rootV).addSeparator()
                .addLabeledComponent("Main file", fileV).addSeparator()
                .addLabeledComponent("Main tree", autodetectCheckBox).addComponentToRightColumn(treeV).addSeparator()
                .addLabeledComponent("Output file", outputFlag).addComponentToRightColumn(output).addSeparator()
                .addLabeledComponent("Logging", loggingCheckBox)
                .getPanel();
    }


    @Override
    protected void resetEditorFrom(@NotNull Nav2Run s) {
        rootV.setText(s.getOptions().getRoot());
        fileV.setText(s.getOptions().getFile());
        treeV.setText(s.getOptions().getTree());
        output.setText(s.getOptions().getOutput());
        autodetectCheckBox.setSelected(s.getOptions().isAutodetect());
        projectRootCheckBox.setSelected(s.getOptions().getFromProject());
        loggingCheckBox.setSelected(s.getOptions().getLog());
        outputFlag.setSelected(s.getOptions().getOutputFlag());
    }

    @Override
    protected void applyEditorTo(@NotNull Nav2Run s) throws ConfigurationException {
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
    protected @NotNull JComponent createEditor() {
        return myPanel;
    }




}

