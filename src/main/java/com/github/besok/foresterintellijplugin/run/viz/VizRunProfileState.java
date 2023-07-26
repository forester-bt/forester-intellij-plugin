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
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (options.log.getValue(options)) {
            args.add("-d");
        }
        args.add("vis");
        if (options.fromProject.getValue(options)) {
            args.add("-r" + env.getProject().getBasePath());
        } else {
            args.add("-r" + options.root.getValue(options));
        }
        if (options.file.getValue(options).equals("")) {
            args.add("-mmain.tree");
        } else {
            args.add("-m" + options.file.getValue(options));
        }
        if (!options.autodetect.getValue(options)) {
            args.add("-t"+ options.tree.getValue(options));
        }
        if (!options.outputFlag.getValue(options) ) {
            args.add("-o"+ options.output.getValue(options));
        }



        GeneralCommandLine commandLine = new GeneralCommandLine(args);
        OSProcessHandler processHandler = ProcessHandlerFactory.getInstance()
                .createColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
    }
}
