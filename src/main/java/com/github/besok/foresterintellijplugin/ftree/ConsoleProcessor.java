package com.github.besok.foresterintellijplugin.ftree;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;

public class ConsoleProcessor {

    private ConsoleView console;

    public ConsoleProcessor(ConsoleView console) {
        this.console = console;
    }


    public void print(String message) {
        console.print(message, ConsoleViewContentType.NORMAL_OUTPUT);
    }

    public void printError(String message) {
        console.print(message, ConsoleViewContentType.ERROR_OUTPUT);
    }
}
