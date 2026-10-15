---
id: ADR-0002
title: Maven as the build tool
status: accepted
date: 2026-10-15
related:
  - ADR-0001
---

# ADR-0002: Maven as the build tool

## Context

The open question was which build tool builds the modules of the reactor, resolves their dependencies, and binds
the parent, the BOMs, and the target platform; the build tool is evident from the build files and named in
`README.md`, `## Technical Assumptions`.

## Decision

1. Maven builds the project; the root project `metachart` with packaging `pom` leads the modules `doc`, `xml`,
   `util`, `core`, `jsf`, and `bom` in one reactor.
2. The packaging is `pom` for `bom` and `jar` for the other modules; the reactor contains no `war`.
3. The dependency versions come from the parent BOM `org.jeesl.bom:bom-parent8` and from the project BOM
   `metachart-bom`, which imports the BOM `net.sf.exlp:exlp-bom`.
4. Maven enforces banned dependencies with `maven-enforcer-plugin` and generates the Javadoc with
   `maven-javadoc-plugin` in the `release` profile.

## Rationale

- The parent BOM `org.jeesl.bom:bom-parent8` and the project BOM `metachart-bom` come from the JEESL
  environment and the project itself and are Maven artifacts.
- The packagings `pom` and `jar` map the BOM and the reusable libraries to Maven modules.

## Alternatives

- Another build tool (Gradle) – not selected; the parent, the BOMs, and the artifact types are designed for Maven.

## Impact

- Affected requirements: none.
- Files: `pom.xml` (root) and the module POMs of the named modules.

## Open Points

- A fixed Maven version with its own build execution is not regulated; the root `pom.xml` declares no
  `prerequisites`, and the project overview (`README.md`, `## Build and Start`) names Maven 3.9 or newer. The bound
  version is fixed in a constraint or its own decision.

## Evidence

### Implementation

- `pom.xml` (root) – packaging `pom`, six `<module>` entries; parent `org.jeesl.bom:bom-parent8`
- `pom.xml` (root) – `maven-enforcer-plugin` with `bannedDependencies`; `maven-javadoc-plugin` in the `release`
  profile
- `doc/pom.xml`, `xml/pom.xml`, `util/pom.xml`, `core/pom.xml`, `jsf/pom.xml` – packaging `jar`
- `bom/pom.xml` – packaging `pom`; imports `net.sf.exlp:exlp-bom`

### Tests

- `mvn -q clean install` (root) – the reactor resolves the BOMs and builds the six modules

### Documentation

- `README.md` (`## Technical Assumptions`) – names Maven (display location)
