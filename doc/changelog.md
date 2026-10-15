# Change Log

Activities of this project, newest first; the topmost entry covers the activity since the last commit.

## 2026-10-15 – ADR-0001 accepted and compile level changed

What: Findings and notes of the ADR-0001 review accepted; the decision accepted and the compile level changed.
Result: `mvn -o -DskipTests clean install` passes on JDK 17; `javap -v` reports class-file version 52.
Evidence: Build log in the working repository; review list of ADR-0001.
Files: `pom.xml`, `doc/decisions/ADR-0001-java-compile-level.md`, `doc/status.md`,
`README.md`, `doc/requirements/architecture.md`, `doc/changelog.md`.
