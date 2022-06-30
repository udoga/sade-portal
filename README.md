# Sade Portal

## Basic Setup

1. Install Java (>= 9) and Gradle (>= 6.9.1).
2. Start application from command line: `gradle run`
3. Open browser: http://localhost:7000

## IntelliJ IDEA

1. Open project in IntelliJ IDEA.
2. Select File -> Settings -> Build, Execution, Deployment -> Gradle.
3. Select "Use Gradle From: Specified Location" (under Gradle section) and select local Gradle location.
4. Close the window and close project (File -> Close Project).
5. Clean up files created by IntelliJ IDEA: `git clean -df`
6. Reopen the project and there shouldn't be any extra files: `git status`

## Modules

- base: General classes that can be useful in any unrelated projects.
- core: Core classes specific to Sade Portal application.
- rest: REST API dependencies and adapters for custom databases.
- ui: Web user interface with AngularJS.
