package com.github.besok.foresterintellijplugin.ftree;

import java.util.List;

public class SyncCmdResult {
    @Override
    public String toString() {
        return "CmdResult{" +
                "code=" + code +
                ", lines=" + String.join(System.lineSeparator(), lines) +
                ", errorLines=" + String.join(System.lineSeparator(), errorLines) +
                '}';
    }

    private int code;
    private List<String> lines;
    private List<String> errorLines;


    public SyncCmdResult(int code, List<String> lines, List<String> errorLines) {
        this.code = code;
        this.lines = lines;
        this.errorLines = errorLines;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getErrorLines() {
        return errorLines;
    }

    public void setErrorLines(List<String> errorLines) {
        this.errorLines = errorLines;
    }
}
