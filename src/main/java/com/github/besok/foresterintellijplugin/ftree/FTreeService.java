package com.github.besok.foresterintellijplugin.ftree;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FTreeService {

    public static Version VERSION = new Version(0, 2, 5);

    private static final Logger logger = Logger.getInstance(FTreeService.class.getName());


    public Optional<Version> version() {
        SyncCmdResult execute = execute("f-tree -V");

        if (execute.getCode() != 0) {
            return Optional.empty();
        } else {
            if (!execute.getLines().isEmpty()) {
                var line = execute.getLines().get(0);
                Pattern datePattern = Pattern.compile(".* ([0-9]+)\\.([0-9]+)\\.([0-9]+)");
                Matcher matcher = datePattern.matcher(line);
                if (matcher.matches()) {

                    int major = Integer.parseInt(matcher.group(1));
                    int minor = Integer.parseInt(matcher.group(2));
                    int extra = Integer.parseInt(matcher.group(3));

                    return Optional.of(new Version(major, minor, extra));
                } else {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }
    }


    private static @NotNull SyncCmdResult execute(String cmd) {
        try {
            ProcessBuilder processBuilder;

            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // For Unix-like and MacOS
                processBuilder = new ProcessBuilder("sh", "-c", cmd);
            } else {
                logger.info("Unsupported operating system: " + os);
                return new SyncCmdResult(-1,new ArrayList<>(),new ArrayList<>());
            }


            // Start the process
            Process process = processBuilder.start();

            // Read the output from the command
            var inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Read error stream
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            List<String> errorLines = new ArrayList<>();
            while ((line = errorReader.readLine()) != null) {
                errorLines.add(line);
            }

            // Wait for the process to complete and get the exit value
            int exitCode = process.waitFor();
            logger.info("Command exited with code: " + exitCode);
            return new SyncCmdResult(exitCode, lines, errorLines);
        } catch (IOException | InterruptedException e) {
            logger.info("exception: " + e.getMessage());
        }
        return new SyncCmdResult(-1, new ArrayList<>(), new ArrayList<>());
    }

}
