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
- repo: Repository implementations for local databases and Kolay IK.
- rest: REST API plugin by using Javalin.
- ui: Web user interface by using AlpineJS.

## Kolay IK Integration

- The application can use Kolay IK API instead of local database.
- There is a mock Kolay IK API we created in Postman cloud.
- Change "kolay.enabled" to true in [default.properties](rest/main/resources/default.properties) to use it.
- Current "kolay.url" points to the mock Kolay IK API.
- To make changes in mock API, contact developers and get access.

## Todos

- add: repo: full kolayik integration
- add: rest: login service (jwt)
- add: rest: sorting, filtering
- add: rest: run pg.sql at startup
- add: ui: login page
- add: ui: responsive tables
- add: ui: table pagination
- add: ui: message boxes
- add: ui: tree menu
- add: ui: language selection
- add: ui: user manual
