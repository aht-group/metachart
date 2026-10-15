---
id: ADR-0001
title: Compile all modules against the Java 8 API
status: accepted
date: 2026-10-15
related:
  - ADR-0002
---

# ADR-0001: Compile all modules against the Java 8 API

## Context

The open question was with which compiler setting the modules of the reactor compile against the Java 8 API;
the project overview (`README.md`, `## Relevant Values`, `## Decided Fundamental Decisions`) recorded Java 8
as the compile level with the parameters `source` and `target` `1.8`.

- The root `pom.xml` managed `maven-compiler-plugin` in version `2.3.2` and configured `source` and `target`
  `1.8`; the parent BOM `org.jeesl.bom:bom-parent8` declares the same parameters in its `<build><plugins>`.
- `source` and `target` select the source level and the class-file version, but the compilation uses the API
  of the JDK that runs the compiler.
- A build of the reactor on JDK 17 compiled a call to `java.util.List.of` (Java 9) into class-file version 52;
  the failure appears only in the runtime of a Java 8 consumer.

## Decision

Every module of the repository compiles against the Java 8 API.

- The root `pom.xml` configures the parameter `release` with the value `8`; it drops its own parameters `source` and
  `target` `1.8`, and the version of `maven-compiler-plugin` comes from the parent POMs.
- While `release` is set, the compilation uses the API data of Java 8 and writes class-file version 52; the
  compiler call carries `--release 8` and no value of `source` or `target`.
- The build requires JDK 9 or newer for the `release` argument.
- The artifacts of the modules of the reactor carry class-file version 52 and use the Java 8 API only; the
  test compilation is checked in the same way.

## Rationale

- `--release` selects the API data of that Java version, so the compiler rejects types and members that the
  version does not provide.
- One value in the root `pom.xml` covers the reactor; a module with its own value states it itself.
- The failure appears while compiling instead of in the runtime of a Java 8 consumer.
- The parameters `source` and `target` of the parent BOM stay in the effective configuration of the modules;
  `maven-compiler-plugin` gives `release` precedence, so no `source` or `target` argument reaches the compiler.
- The version of `maven-compiler-plugin` comes from the parent POMs and supports the parameter `release`.

## Alternatives

### `source` and `target` `1.8` (initial situation)

Keeps class-file version 52 but compiles against the API of the build JDK, so a Java 9 or newer API
stays unnoticed; a call to `java.util.List.of` compiles.

### Building with JDK 8

Rejects a newer API by definition, but binds the whole build to JDK 8.

### Signature check of the Java 8 API after the compilation

Checks the bytecode and works with any JDK, but adds an external component, which requires a decision of
its own, and a separate signature artifact.

## Impact

- Affected requirements: none.
- `pom.xml` (root) – the parameter `release` replaces `source` and `target` for every module; the version of
  `maven-compiler-plugin` comes from the parent POMs.
- `README.md` (`## Technical Assumptions`) – the language entry names the Java 8 API of the compilation.
- `README.md` (`## Relevant Values`) – the row of the Java compile level names the parameter `release` and the
  root `pom.xml` as its authoritative location.
- `README.md` (`## Build and Start`) – the prerequisite names JDK 9 or newer in place of JDK 8.
- `README.md` (`## Decided Fundamental Decisions`) – the compile level names the Java 8 API.
- `doc/requirements/architecture.md` – the compile level and its authoritative location.
- `doc/status.md` – the index carries the decision with its status.
- A build on JDK 8 ends in the first module with `invalid flag: --release`.

## Open Points

- A module that later needs Java 11 APIs configures its own `release` value; the decision does not
  regulate which module that is.
- The JDK that runs the build is not regulated beyond the minimum that the `release` argument requires.
- The Javadoc path is not regulated: `maven-javadoc-plugin` `2.9.1` from the parent POMs knows no parameter `release`
  (`doc/decisions/ADR-0002-maven-build.md`).

## Evidence

### Implementation

- `pom.xml` (root) – the parameter `release` `8` in place of the parameters `source` and `target` `1.8`, without a
  version of `maven-compiler-plugin`

### Tests

- `mvn -o -DskipTests clean install` on JDK 17 – BUILD SUCCESS for the six modules
- `mvn -o -DskipTests clean install -pl doc` on JDK 17 – `javac [debug release 8]`, BUILD SUCCESS
- `javap -v` on classes of the modules and of their test classes – `major version: 52`
- `mvn -o -N help:effective-pom` (root) – `maven-compiler-plugin` `3.16.0` with `release` `8` in `pluginManagement`;
  `source` `1.8` and `target` `1.8` next to `release` `8` in the effective configuration
- Compilation of a class that calls `java.util.List.of` – `cannot find symbol: method of(java.lang.String)`
- `mvn -o -DskipTests clean install` on JDK 8 – BUILD FAILURE in `metachart-doc`: `invalid flag: --release`

### Documentation

- `README.md` (`## Technical Assumptions`, `## Relevant Values`, `## Build and Start`,
  `## Decided Fundamental Decisions`) – names the Java 8 API of the compilation, `release` `8`, and JDK 9 or
  newer
- `doc/requirements/architecture.md` – names the compile level and its authoritative location
