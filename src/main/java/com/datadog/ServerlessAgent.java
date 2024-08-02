package com.datadog;

import java.io.File;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerlessAgent {
    private static final Logger log = LoggerFactory.getLogger(ServerlessAgent.class);
    private static final String fileName = "datadog-serverless-trace-mini-agent";

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        log.info("Attempting to start {}", fileName);

        try (InputStream inputStream = ServerlessAgent.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            if (inputStream == null) {
                log.error("{} not found", fileName);
            }

            File executableFile = new File(fileName);
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
