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

- base: General classes that can be reusable for unrelated projects.
- core: Core classes specific to this application.
- rest: REST API dependencies and adapters for custom databases.
- ui: Web user interface with AngularJS.

## Todos

- add: core: 'permission' model and services
- add: core: 'leave' model and services
- fix: rest: error when updating records without id
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
