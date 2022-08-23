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

- base: Classes that can be reusable for unrelated projects.
- core: Classes and business rules specific to this application.
- repo: Adapters for databases or external data sources.
- rest: REST API plugin by using Javalin.
- ui: Web user interface by using AngularJS.

## Kolay IK Integration

- The application can use Kolay IK API instead of local database.
- There is a mock Kolay IK API we created in Postman cloud.
- Change "kolay.enabled" to true in default.properties to use it.
- Current "kolay.url" points to the mock Kolay IK API.
- To make changes in mock API, contact developers and get access.

## Todos

- ref: core: Person: remove 'workingDayId' field
- add: core: 'permission' model and services
- add: core: 'leave' model and services
- add: rest: run pg.sql at startup
- add: rest: sorting, filtering
- add: rest: api authentication with token
- add: rest: kolayik integration
- add: ui: use vue instead of ng
- add: ui: login page
- add: ui: make tables responsive
- add: ui: page selection to tables
- add: ui: message boxes
- add: ui: date picker
- add: ui: tree menu
- add: ui: language selection
- add: ui: user manual
