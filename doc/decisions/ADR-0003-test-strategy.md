---
id: ADR-0003
title: Test strategy
status: accepted
date: 2026-10-15
related:
  - ADR-0001
  - ADR-0002
---

# ADR-0003: Test strategy

## Context

The open question was how the test programs of the modules are named, stored, and run. The project overview
records the empty requirement tables (`README.md`, `## Initial Situation`), so no requirement answers the
question; the framework of the test run appears only as a value (`README.md`, `## Technical Assumptions`),
and the modules `core`, `util`, and `xml` carry test sources under `src/test/java` that mix test programs
with demo programs.

## Decision

1. The test programs of a module are located under `src/test/java` of that module.
2. Test programs run with JUnit 5; the assertions come from `org.junit.jupiter.api.Assertions`. The test
   basis is `net.sf.exlp:exlp-test`, which contributes the base classes of the test programs; no further
   test framework is introduced.
3. A test program is a class under `src/test/java` that carries at least one `@Test` method; the method may be
   its own or one inherited from the test basis. Its name follows the selection pattern of the test run. A
   class without such a method is not a test program, even if its name matches the pattern.
4. A test program runs without an application container, without a database, and without a network and is
   therefore repeatable without preparation. A logging configuration under `src/test/resources` is allowed
   and does not stand in the way.
5. Time and inputs of a test program are parameters; it holds them as named constants in the test program or
   as test data under `src/test/resources`. Fixed inputs are allowed, a fixed expectation is not; it derives
   the expectation from its inputs. It does not use identifiers of the environment, such as the time zone,
   as a fixed value.
6. The verification command `mvn -DskipTests=false verify` runs the test programs of every module of the
   reactor and ends with the return value 0.

## Rationale

- JUnit 5 is the test framework of the test basis `net.sf.exlp:exlp-test`, which the module `xml` binds in
  the test scope (`xml/pom.xml`).
- The verification command evaluates only the return value; it therefore needs test programs that determine
  success and failure themselves, and not programs that only write their output.
- The verification command runs without an application container and without a database; a program that
  needs these is therefore not a test program in the sense of this decision.
- A demo program that sends its input to a chart server running as a container
  (`core/src/test/java/org/metachart/export/TestBackgroundExport.java`) is not repeatable without preparation
  and is therefore likewise not a test program.
- The test programs of a module are in the test scope and do not reach its jar.
- The modules `core` and `util` bind the test jar of `xml` (`metachart-xml:tests`) in the test scope
  (`core/pom.xml`, `util/pom.xml`), so the base classes of the test programs are shared across the reactor.

## Alternatives

- Test programs without a framework as pure `main` programs – not selected; then every program would have to
  report its success itself instead of leaving it to the framework.
- Extending the verification command to all classes under `src/test/java`, also without a name pattern – not
  selected; many of them are demo programs that write output, need a network, or need a container and do not
  determine the return value reliably.
- JUnit 4 – not selected; the test basis `net.sf.exlp:exlp-test` builds on JUnit 5 (`net.sf.exlp:exlp-bom`).

## Impact

- Affected requirements: none.
- Files: the `src/test/java` directories of the modules `core`, `util`, and `xml` take the test programs;
  `xml/pom.xml` binds the test basis and the JUnit 5 artifacts.
- `README.md` (`## Technical Assumptions`) – the tests entry names JUnit 5 and the test basis.
- `doc/requirements/architecture.md` (`## External Components`) – the tests entry names the test framework
  and the decision that fixes it.
- `doc/status.md` – the index carries the decision with its status.

## Open Points

- Which test programs the modules need is not regulated by this decision; the demo programs under
  `core/src/test/java` and `util/src/test/java` remain demo programs.
- The version of `maven-surefire-plugin` is not regulated by this decision; it comes from the parent POM
  chain (`org.jeesl.bom:bom`).
- The version of JUnit 5 is not regulated by this decision; the BOM `net.sf.exlp:exlp-bom` maintains it at
  5.10.0, while the project BOM `metachart-bom` still manages `junit:junit` 4.12, which no module declares
  (`bom/pom.xml`).
- The JAXB API of the XML test programs is not regulated by this decision; they run against `javax.xml.bind`
  2.3.1 (`xml/pom.xml`).

## Evidence

### Implementation

- `xml/pom.xml` – binds `net.sf.exlp:exlp-test`, `org.junit.jupiter:junit-jupiter-api`, and
  `org.junit.jupiter:junit-jupiter-engine` in the test scope (points 2 and 3)
- `xml/src/test/java/org/metachart/xml/chart/TestXmlDataSet.java`, `TestXmlTitle.java`,
  `xml/src/test/java/org/metachart/xml/graph/TestXmlNode.java`, and `TestXmlTree.java` – extend
  `AbstractXmlChartTest` and `AbstractXmlGraphTest`, which extend `org.exlp.test.AbstractXmlTest` with the
  `@Test` method `xml()` and `org.junit.jupiter.api.Assertions` (points 1, 2, and 3)
- `core/src/test/java` and `util/src/test/java` – demo programs with a `main` method and classes without a
  `@Test` method, among them `CliEchartCategory`, `TestBackgroundExport`, and `TestJsonDataFactory`
  (points 3 and 4)
- `core/pom.xml` and `util/pom.xml` – bind the test jar `metachart-xml:tests` in the test scope

### Tests

- `mvn -o -DskipTests=false verify` on JDK 17 – BUILD SUCCESS with the return value 0; the
  `JUnitPlatformProvider` of `maven-surefire-plugin` `3.6.0` runs four test programs of the module `xml`
  (`Tests run: 4, Failures: 0, Errors: 0`), and the modules `core`, `util`, `jsf`, and `doc` report no test
  program (point 6)

### Documentation

- `README.md` (`## Technical Assumptions`) – the tests entry names JUnit 5 and the test basis (point 2)
- `doc/requirements/architecture.md` (`## External Components`) – names the test framework and the decision
  that fixes it (point 2)
