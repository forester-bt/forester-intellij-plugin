package com.github.besok.foresterintellijplugin.run.viz;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VizRunProfileState extends CommandLineState {

    private final VisOptions options;
    private final ExecutionEnvironment env;

    protected VizRunProfileState(ExecutionEnvironment env, VisOptions options) {
        super(env);
        this.options = options;
        this.env = env;
    }


    @Override
    protected @NotNull ProcessHandler startProcess() throws ExecutionException {

        var args = new ArrayList<String>();

        args.add("f-tree");
        if (options.getLog()) {
            args.add("-d");
        }
        args.add("vis");
        if (options.getFromProject()) {
            args.add("-r" + env.getProject().getBasePath());
        } else {
            args.add("-r" + options.getRoot());
        }
        if (options.getFile().equals("")) {
            args.add("-mmain.tree");
        } else {
            args.add("-m" + options.getFile());
        }
        if (!options.isAutodetect()) {
            args.add("-t"+ options.getTree());
        }
        if (!options.getOutputFlag() ) {
            args.add("-o"+ options.getOutput());
        }



        GeneralCommandLine commandLine = new GeneralCommandLine(args);
        OSProcessHandler processHandler = ProcessHandlerFactory.getInstance()
                .createColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
    }
}
