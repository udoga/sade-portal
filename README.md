# Sade Portal

## Basic Setup

1. Install JDK (>= 11) and Gradle (>= 7).
2. Start application from command line: `gradle run`
3. Open browser: http://localhost:7000

## IntelliJ IDEA Setup

1. Open project in IntelliJ IDEA.
2. Select File -> Project Structure -> SDK and select your local JDK 11 path.
3. Select "Language Level: SDK Default" in the same window and click OK.
4. Select File -> Settings -> Build, Execution, Deployment -> Gradle.
5. Select "Use Gradle From: Specified Location" and select your local Gradle path.
6. Select "Gradle JVM: Project SDK 11" in the same window and click OK.
7. Select File -> Close Project and remove extra files created by IntelliJ IDEA: `git clean -df`
8. Open the project again and check that there isn't any extra files: `git status`

## Modules

- base: Classes that can be reusable for unrelated projects.
- core: Classes and business rules specific to this application.
- repo: Repository implementations for data sources.
- rest: REST API plugin by using Javalin.
- ui: Web user interface by using AlpineJS.

## Todos

* Password encryption should use salt.
* Authorization service.
* Language selection.
* Message Box style.
* Pagination buttons.
