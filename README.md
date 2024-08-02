# Datadog Serverless Java Agent

Java agent used to start the Datadog Serverless Mini Agent. Intended for use with [Azure Spring Apps](https://azure.microsoft.com/en-us/products/spring-apps).

# Getting Started

- From the latest release, download `dd-serverless-java-agent.jar` and upload it to your Azure Spring App in persistent storage.
- Add the Datadog Serverless Java Agent and Datadog Java Tracer as Java agents to your `JVM_OPTIONS`.
```
-javaagent:/persistent/dd-serverless-java-agent.jar -javaagent:/persistent/dd-java-agent.jar
```
- Set environment variables
* `DD_API_KEY` = `<YOUR API KEY>`
* `DD_SITE` = `datadoghq.com`
* `DD_SERVICE` = `<SERVICE NAME>`
* `DD_ENV` = `<ENVIRONMENT`
* `DD_VERSION` = `<VERSION>`
* `DD_TRACE_TRACER_METRICS_ENABLED` = `true`

# Contributing

## Setting up development environment

Follow the instructions in the `dd-trace-java` repo to set up your Java environment: [Setting up development environment](https://github.com/DataDog/dd-trace-java/blob/master/BUILDING.md#setting-up-development-environment).

## Building the project

Build the Datadog Serverless Mini Agent from [libdatadog](https://github.com/DataDog/libdatadog) and add the linux binary, `datadog-serverless-trace-mini-agent`, to `src/main/resources`.

To build the project run:
```
mvn clean install
```

