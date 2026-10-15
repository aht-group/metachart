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
the parent, the BOMs, and the target platform; the build files are Maven POMs, and the project overview
(`README.md`, `## Technical Assumptions`) names Apache Maven.

## Decision

1. Maven builds the project; the root project `metachart` with packaging `pom` leads the modules `doc`, `jsf`,
   `core`, `bom`, `util`, and `xml` in one reactor.
2. The packaging is `pom` for `bom` and `jar` for the other modules; the reactor contains no `war`.
3. The dependency versions come from the parent BOM `org.jeesl.bom:bom-parent8` and from the project BOM
   `metachart-bom`, which imports the BOM `net.sf.exlp:exlp-bom`.
4. `maven-enforcer-plugin` enforces banned dependencies; the rule `bannedDependencies` bans
   `commons-logging:commons-logging`; the module `metachart-jsf` deactivates the rule with `enforcer.skip`.
5. The `release` profile generates the Javadoc with `maven-javadoc-plugin`.
6. The plugin versions come from the parent POMs; the BOM `org.jeesl.bom:bom` binds them in its `pluginManagement`,
   among them `maven-javadoc-plugin` `2.9.1`.
7. The parent POM `org.jeesl.bom:bom` enforces the minimum Maven version `3.6.3` with the rule `requireMavenVersion`
   of `maven-enforcer-plugin` `1.4.1`.

## Rationale

- The parent BOM `org.jeesl.bom:bom-parent8` and the project BOM `metachart-bom` come from the JEESL
  environment and the project itself and are Maven artifacts.
- The packagings `pom` and `jar` map the BOM and the reusable libraries to Maven modules.
- The plugin versions and the minimum Maven version are maintained once in the parent POM chain; the root `pom.xml`
  overrides only `maven-compiler-plugin` (ADR-0001).

## Alternatives

- Another build tool (Gradle) – not selected; the parent, the BOMs, and the artifact types are designed for Maven.

## Impact

- Affected requirements: none.
- Files: `pom.xml` (root) and `doc/pom.xml`, `xml/pom.xml`, `util/pom.xml`, `core/pom.xml`, `jsf/pom.xml`,
  `bom/pom.xml`.

## Open Points

None.

## Evidence

### Implementation

- `pom.xml` (root) – packaging `pom`, six `<module>` entries; parent `org.jeesl.bom:bom-parent8`
- `pom.xml` (root) – `maven-enforcer-plugin` with `bannedDependencies`; `maven-javadoc-plugin` in the `release`
  profile
- `jsf/pom.xml` – deactivates the rule `bannedDependencies` with `enforcer.skip`
- `doc/pom.xml`, `xml/pom.xml`, `util/pom.xml`, `core/pom.xml`, `jsf/pom.xml` – packaging `jar`
- `bom/pom.xml` – packaging `pom`; imports `net.sf.exlp:exlp-bom`

### Tests

- `mvn -q clean install` (root) – the reactor resolves the BOMs and builds the six modules
- `mvn -o -N help:effective-pom` (root) – `maven-javadoc-plugin` `2.9.1` in `pluginManagement`;
  `requireMavenVersion` `3.6.3`

### Documentation

- `README.md` (`## Technical Assumptions`) – names Maven (display location)
