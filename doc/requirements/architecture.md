# Architecture

MetaChart is a Java charting library. It turns chart and graph models into JavaScript/JSON, XHTML, XML, and
plain text and ships a JSF/PrimeFaces component library that renders charts in web applications. The library
is built as a multi-module Apache Maven reactor; the project overview is `README.md`.

## Modules

| Module | Artifact | Purpose |
|---|---|---|
| `doc` | `metachart-doc` | Documentation placeholder |
| `xml` | `metachart-xml` | XML (JAXB) and JSON models for charts and graphs |
| `util` | `metachart-util` | Factories that turn models into JS/JSON, XHTML, XML, and TXT |
| `core` | `metachart-core` | Servlets and processors (export, graph/DOT, timeseries) |
| `jsf` | `metachart-jsf` | JSF/PrimeFaces components and web resources |
| `bom` | `metachart-bom` | Dependency management for the modules |

The reactor, its order, and the coordinates of the modules are maintained in the root `pom.xml` and in the
module POMs; the build tool is fixed by `doc/decisions/ADR-0002-maven-build.md`.

## Build

- The modules compile against the Java 8 API; the parameter is maintained in the root `pom.xml` and the
  compile level is fixed by `doc/decisions/ADR-0001-java-compile-level.md`.
- The dependency versions come from the parent BOM `org.jeesl.bom:bom-parent8` and the project BOM
  `metachart-bom`; both are maintained in `pom.xml` and in `bom/pom.xml`
  (`doc/decisions/ADR-0002-maven-build.md`).
- The profile `release` attaches sources and Javadoc and signs the artifacts
  (`doc/decisions/ADR-0002-maven-build.md`).

## Rendering Path

- Chart configuration is modelled as JSON (`org.metachart.model.json.*`) and emitted as JavaScript
  (`org.metachart.factory.json.chart.echart.*`) for the ECharts runtime.
- XML models (`org.metachart.model.xml.*`) are the input of the Highcharts-based renderers and of the graph
  (Graphviz/DOT) conversion.
- The JSF module renders charts as HTML and JavaScript fragments that a host application embeds.

## External Components

- JAXB marshals the XML models, Jackson handles JSON, and the JSF module targets a JSF 2.3 / PrimeFaces
  servlet container; the versions are maintained in the build files (`README.md`, `## Relevant Values`).
- Helper libraries come from `net.sf.exlp` and the JEESL BOM family
  (`README.md`, `## Technical Assumptions`).
- Tests run with JUnit 5 over the test basis `net.sf.exlp:exlp-test`; the test strategy is fixed by
  `doc/decisions/ADR-0003-test-strategy.md` (`README.md`, `## Technical Assumptions`).

## Documentation Framework

- Rulebook `doc/requirements/requirements.md`, index `doc/status.md`, decisions `doc/decisions/`, activity log
  `doc/changelog.md`, project overview `README.md`.
