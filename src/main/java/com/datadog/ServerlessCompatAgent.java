package com.datadog;

import java.io.File;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerlessCompatAgent {
    private static final Logger log = LoggerFactory.getLogger(ServerlessCompatAgent.class);
    private static final String os = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return os.contains("win");
    }

    public static boolean isLinux() {
        return os.contains("linux");
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        final String fileName;
        final String tempDirPath;

        if (isWindows()) {
            log.debug("Detected {}", os);
            fileName = "datadog-serverless-compat.exe";
            tempDirPath = "C:/local/Temp/datadog";
        } else if (isLinux()) {
            log.debug("Detected {}", os);
            fileName = "datadog-serverless-compat";
            tempDirPath = "/tmp/datadog";
        } else {
            log.error("Unsupported operating system {}", os);
            return;
        }

        log.info("Attempting to start {}", fileName);

        try (InputStream inputStream = ServerlessCompatAgent.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            if (inputStream == null) {
                log.error("{} not found", fileName);
                return;
            }

            Path tempDir = Paths.get(tempDirPath);
            Files.createDirectories(tempDir);
            File executableFile = tempDir.resolve(fileName).toFile();

            Files.copy(inputStream, executableFile.getAbsoluteFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
            executableFile.setExecutable(true);

            ProcessBuilder processBuilder = new ProcessBuilder(executableFile.getAbsolutePath());
            processBuilder.inheritIO();
            processBuilder.start();
        } catch (Exception e) {
            log.error("Exception when starting {}", fileName, e);
        }
    }
}
