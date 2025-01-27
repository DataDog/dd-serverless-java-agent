# Datadog Serverless Compatibility Java Agent

Java agent used to start the Datadog Serverless Compatibility Layer. Intended for use with [Azure Spring Apps](https://azure.microsoft.com/en-us/products/spring-apps) and [Azure Functions](https://azure.microsoft.com/en-us/products/functions).

# Getting Started

- From the latest releases, download `dd-serverless-compat-java-agent.jar` and `dd-java-agent.jar` to your app:
  * `wget -O dd-serverless-compat-java-agent.jar 'https://dtdg.co/latest-serverless-compat-java-agent'`
  * `wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'`
- Add the Datadog Serverless Compatibility Java Agent and Datadog Java Tracer as Java agents using the appropriate JVM Options environment variable:
  * Azure Spring App
    - `JVM_OPTIONS`
  * Azure Function, Consumption Plan
    - `languageWorkers__java__arguments`
  * Azure Function, Premium/Dedicated Plan
    - `JAVA_OPTS`
  
```
-javaagent:/path/to/dd-serverless-compat-java-agent.jar -javaagent:/path/to/dd-java-agent.jar
```

- Set Datadog environment variables
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

Build the Datadog Serverless Compatibility Layer from [libdatadog](https://github.com/DataDog/libdatadog) and add the binaries, `datadog-serverless-compat` and `datadog-serverless-compat.exe`, to `bin`.

To build the project run:
```
./build.sh
```

