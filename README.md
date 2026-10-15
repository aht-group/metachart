# MetaChart

MetaChart (MC) is a Java charting library. It turns chart and graph models into web-ready output —
JavaScript/JSON for the Apache ECharts runtime, XHTML, XML, and plain text — and it ships a
JSF/PrimeFaces component library that renders these charts inside web applications. The library is
built as a multi-module Apache Maven reactor.

## Initial Situation

- MC is a long-standing charting library (`org.metachart`). Historically it rendered charts through
  Highcharts (bundled in the JSF module) and JFreeChart.
- The current rendering direction is Apache ECharts: chart configuration is modelled as JSON
  (`org.metachart.model.json.*`) and emitted as JavaScript (`org.metachart.factory.json.chart.echart.*`).
- JFreeChart usage has been removed from the code; only commented-out dependency remnants remain in
  the POMs.
- XML/JAXB models (`org.metachart.model.xml.*`) are still the input for the Highcharts-based renderers
  and for the graph (Graphviz/DOT) conversion.
- The project documentation is being migrated to a requirements/decision framework. In place:
  `doc/llm.md` (AI assistant instructions) and `doc/requirements/requirements.md` (rulebook).
  Not yet populated: project requirements, `doc/status.md`, `doc/changelog.md`, `doc/decisions/`,
  `doc/requirements/architecture.md`, and the AI tool rule files under `.clinerules/`.

## Relevant Values

| Value | Setting | Authoritative location |
|---|---|---|
| Maven coordinates | `org.metachart:metachart:0.0.8-SNAPSHOT` | `pom.xml` |
| Parent BOM | `org.jeesl.bom:bom-parent8:0.3.2-SNAPSHOT` | `pom.xml` |
| Java source/target | 1.8 | `pom.xml` (`maven-compiler-plugin`) |
| License | GNU GPL v3 | `pom.xml` |
| SCM | `https://github.com/aht-group/metachart.git` | `pom.xml` |
| ECharts runtime | 5.4.3 (CDN) / `^5.5.0` (npm bundle) | `XhtmlEchartFactory`, `mcTypeScript/package.json` |
| Highcharts | `^11.4.3` (npm bundle `dist/hc.bundle.js`); 8.0.4 (legacy static resources) | `mcTypeScript/package.json`, `jsMetaChart/highchart-8.0.4/` |
| JSF | `javax.faces` API 2.3; PrimeFaces 12.0.0 | `core/pom.xml`, `jsf/pom.xml` |
| JSON | Jackson 2.15.2 | `bom/pom.xml` |
| Banned dependency | `commons-logging:commons-logging` | `pom.xml` (`maven-enforcer-plugin`) |

The values above are maintained in the build files; this table only names them.

## Technical Assumptions

- Java 8 (e.g. Azul Zulu 8) and Apache Maven 3.9+ are available; the modules compile with the
  compiler settings in `pom.xml`.
- Dependencies resolve against Maven Central
- XML models are marshalled with JAXB (`javax.xml.bind` 2.3.1); JSON is handled with Jackson.
- The JSF module targets a JSF 2.3 / PrimeFaces 12 servlet container; charts are rendered as HTML
  and JavaScript fragments that a host application embeds.
- Helper libraries come from `net.sf.exlp` (`exlp-util`, `exlp-xml`, ...) and the JEESL BOM family.
- The `doc` module is a documentation placeholder with no production code.

## Open Fundamental Decisions

- Whether the `web` module (packaging `war`, version `0.0.5-SNAPSHOT`, not part of the reactor
  modules) is continued, upgraded, or removed.
- Whether the remaining Highcharts render path is kept alongside ECharts or replaced by it.
- Whether the commented-out JFreeChart entries in the POMs are removed.
- Which ECharts version is authoritative (5.4.3 via CDN vs. 5.5.0 via the npm bundle).
- Whether the project-specific documentation is filled (`doc/status.md`, `doc/changelog.md`,
  `doc/decisions/`, `doc/requirements/architecture.md`) and whether `.clinerules/` is added.

## Decided Fundamental Decisions

No architecture decision has been recorded in `doc/decisions/` yet. The constraints below are
evident from the build files:

- The project is a multi-module Maven reactor with a central `metachart-bom` for version management.
- Java 8 is the compile target and GNU GPL v3 the license (see Relevant Values).
- ECharts with a JSON model is the target rendering path; its chart families are line, time,
  scatter, heatbar/heatmap, gauge, graph, and sankey.

## Planned Milestones

No milestones have been agreed yet. The observed (non-binding) direction of work is the completion
of the ECharts/JSON rendering path and the reduction of the legacy Highcharts/JFreeChart code.

## Build and Start

Prerequisites:

- JDK 8
- Apache Maven 3.9+ with access to Maven Central and the aht-group Nexus (or a populated local repository)

Build the whole reactor from the repository root:

```
mvn clean install
```

Useful variants:

```
mvn -o clean install        # offline, using an already populated local repository
mvn clean install -DskipTests
```

Modules (reactor order):

| Module | Artifact | Purpose |
|---|---|---|
| `bom` | `metachart-bom` | Dependency management (BOM) for all MC modules |
| `doc` | `metachart-doc` | Documentation placeholder (no production code) |
| `xml` | `metachart-xml` | XML (JAXB) and JSON models for charts and graphs |
| `util` | `metachart-util` | Factories that turn models into JS/JSON, XHTML, XML, and TXT |
| `core` | `metachart-core` | Servlets and processors (export, graph/DOT, timeseries) |
| `jsf` | `metachart-jsf` | JSF/PrimeFaces components and web resources (TypeScript bundles) |

The `web` directory is not part of the reactor modules.

Running the demos:

- The `util` module contains CLI demos with `main` methods under
  `util/src/test/java/org/metachart/factory/xhtml/chart/e/` (for example `CliEchartCategory`,
  `CliEchartTime`, `CliEchartScatter`, `CliEchartGauge`, `CliEchartHeatbar`). They write HTML/JS
  output to a temp directory taken from the `dir.tmp` property (default: `java.io.tmpdir`).
- The `core` module contains demos for the XML/Highcharts path (for example `CliEchartDynamic`,
  `CliTimeSeriesChartRenderer`).
- Bootstrap and configuration are provided by `org.metachart.test.McBootstrap`, which reads
  `metachart/system/property/mc.xml` and the JEESL/ExLP central configuration for the `metachart`
  system.

## Development Principles

- Follow the project instructions for the AI assistant in `doc/llm.md` and the **Requirements Rules** in `doc/requirements/requirements.md`.
- Write documents, source code, and new file names in **English**; use ASCII kebab-case for new file names.
- Cover new functionality with tests and run them (together with the Git diff) after each change.
- Never create a commit automatically; see the **Commit Message Rule**.

## Rules and Roles

- Project instructions for the AI assistant: `doc/llm.md`; rule files of the AI tool: `.clinerules/`.
- Commit Message Rule: `.clinerules/10-git-commit.md`.
- Requirements Rules: `doc/requirements/requirements.md`.
- Index File of requirements and architecture decisions including status: `doc/status.md`.
- Change Log (chronicle): `doc/changelog.md`.
- Architecture File: `doc/requirements/architecture.md`; Decision Repository: `doc/decisions/`.

