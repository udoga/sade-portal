# Sade Portal

## Basic Setup

1. Install JDK (>= 17) and Gradle (>= 7).
2. Run unit tests: `gradle test --rerun-tasks`
3. Start the application: `gradle run`
4. Login: http://localhost:7000 (admin / admin-pwd)

## Modules

- base: Classes that can be reusable for unrelated projects.
- core: Classes and business rules specific to this application.
- repo: Repository implementations for data sources.
- rest: REST API plugin by using Javalin.
- ui: Web user interface by using AlpineJS.
