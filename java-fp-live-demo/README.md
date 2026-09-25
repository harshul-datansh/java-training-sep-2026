# Java FP Live Demo: Lambdas, Functional Interfaces, Streams

A live-coding project for an intern session. One running story (an **order-processing backend**) takes you through three topics, in this order:

1. [Lambda Expressions](docs/01-lambdas.md)
2. [Functional Interfaces](docs/02-functional-interfaces.md)
3. [Stream API](docs/03-streams.md)

Presenter? Start with [PRESENTER_GUIDE.md](PRESENTER_GUIDE.md), then open `present.html` in a browser for screen sharing.

---

## Prerequisites

| Tool | Version | Check with |
|---|---|---|
| JDK | 17 or newer (the project compiles with `release 17`) | `java -version` |
| Maven | 3.9.x (tested with 3.9.6) | `mvn -v` |
| IDE (optional) | IntelliJ IDEA, Eclipse or VS Code with Java support | |

The only dependency is JUnit Jupiter 5.10.2 (test scope).

---

## Project layout

```text
java-fp-live-demo/
  pom.xml
  README.md                 <- you are here
  PRESENTER_GUIDE.md        <- the live script for the trainer
  present.html              <- screen-share viewer for all the Markdown (generated)
  docs/
    01-lambdas.md
    02-functional-interfaces.md
    03-streams.md
  broken-examples/          <- code that does NOT compile, on purpose (see below)
  tools/
    GeneratePresent.java    <- regenerates present.html from the .md files
  src/main/java/com/training/demo/
    model/                  <- Order, OrderStatus, OrderDto, SampleData (shared by everything)
    lambdas/     demo/ exercises/ solutions/
    functional/  demo/ exercises/ solutions/   (+ DiscountRule.java)
    streams/     demo/ exercises/ solutions/
  src/test/java/com/training/demo/
    lambdas/exercises/  functional/exercises/  streams/exercises/   <- one test class per exercise
```

Every `demo` class has a `main` method and prints labelled output. Demos are numbered in the order they are shown.

---

## Run a demo

### From the IDE

Open the folder (or `pom.xml`) as a Maven project, open any `DemoNN_...` class and click the green run arrow next to `main`.

### From the terminal (PowerShell, cmd or bash, run in the project folder)

```text
mvn -q compile
java -cp target/classes com.training.demo.lambdas.demo.Demo01_AnonymousClassVsLambda
```

`mvn -q compile` prints nothing when it succeeds. Compile once, then run as many demos as you like. Re-run `mvn -q compile` after you edit code.

All demos, in presentation order:

```text
java -cp target/classes com.training.demo.lambdas.demo.Demo01_AnonymousClassVsLambda
java -cp target/classes com.training.demo.lambdas.demo.Demo02_LambdaSyntax
java -cp target/classes com.training.demo.lambdas.demo.Demo03_EffectivelyFinal
java -cp target/classes com.training.demo.lambdas.demo.Demo04_LoopVariableTrap
java -cp target/classes com.training.demo.lambdas.demo.Demo05_MethodReferences
java -cp target/classes com.training.demo.lambdas.demo.Demo06_CommonMistakes

java -cp target/classes com.training.demo.functional.demo.Demo01_WhatMakesItFunctional
java -cp target/classes com.training.demo.functional.demo.Demo02_CoreFunctionalTypes
java -cp target/classes com.training.demo.functional.demo.Demo03_CustomFunctionalInterface
java -cp target/classes com.training.demo.functional.demo.Demo04_ComposingFunctions

java -cp target/classes com.training.demo.streams.demo.Demo01_WhatIsAStream
java -cp target/classes com.training.demo.streams.demo.Demo02_StreamSources
java -cp target/classes com.training.demo.streams.demo.Demo03_IntermediateAndTerminal
java -cp target/classes com.training.demo.streams.demo.Demo04_Laziness
java -cp target/classes com.training.demo.streams.demo.Demo05_Collectors
java -cp target/classes com.training.demo.streams.demo.Demo06_EndToEndDto
```

---

## Exercises and tests

Each topic has 4 exercises, easy to hard. Each exercise is a class in an `exercises` package with stub methods that throw `UnsupportedOperationException("TODO")`, a docstring, and a hint. Each has its own JUnit 5 test class.

**Run the tests for one exercise:**

```text
mvn test -Dtest=Ex01_SortByTotalTest
```

| Topic | Exercise class | Test class |
|---|---|---|
| Lambdas | `Ex01_SortByTotal` | `Ex01_SortByTotalTest` |
| Lambdas | `Ex02_CaptureThreshold` | `Ex02_CaptureThresholdTest` |
| Lambdas | `Ex03_MethodReferences` | `Ex03_MethodReferencesTest` |
| Lambdas | `Ex04_DelayedLabels` | `Ex04_DelayedLabelsTest` |
| Functional interfaces | `Ex01_CoreTypes` | `Ex01_CoreTypesTest` |
| Functional interfaces | `Ex02_CustomDiscountRules` | `Ex02_CustomDiscountRulesTest` |
| Functional interfaces | `Ex03_CombinePredicates` | `Ex03_CombinePredicatesTest` |
| Functional interfaces | `Ex04_PricePipeline` | `Ex04_PricePipelineTest` |
| Streams | `Ex01_FilterAndMap` | `Ex01_FilterAndMapTest` |
| Streams | `Ex02_FlatMapAndReduce` | `Ex02_FlatMapAndReduceTest` |
| Streams | `Ex03_Collectors` | `Ex03_CollectorsTest` |
| Streams | `Ex04_TopCustomers` | `Ex04_TopCustomersTest` |

**What to expect:**

- Before an exercise is solved, its tests **fail** (red) with `UnsupportedOperation TODO`. That is by design: the room watches red turn green.
- `mvn test` (all exercises) therefore reports `Tests run: 37, Failures: 0, Errors: 37` and `BUILD FAILURE` on a fresh copy. Every error is a TODO stub.
- With all exercises solved, `mvn test` reports `Tests run: 37, Failures: 0, Errors: 0` and `BUILD SUCCESS`.
- Worked solutions (with a short "why" comment) are in the `solutions` package next to each `exercises` package. The class names are the same, so you can copy a solution body straight into the exercise.

---

## Broken examples (compiler errors on purpose)

`broken-examples/` holds tiny files that **do not compile**, so you can show the real compiler message live. They are outside `src/`, so Maven never sees them. Run from the project folder:

```text
javac --release 17 -d target/broken broken-examples/01-captured-variable-changed/*.java
javac --release 17 -d target/broken broken-examples/02-loop-variable/*.java
javac --release 17 -d target/broken broken-examples/03-annotated-two-abstract-methods/*.java
javac --release 17 -d target/broken broken-examples/04-not-annotated-two-abstract-methods/*.java
javac --release 17 -d target/broken broken-examples/05-annotated-no-abstract-method/*.java
```

The expected messages are quoted in [docs/01-lambdas.md](docs/01-lambdas.md) and [docs/02-functional-interfaces.md](docs/02-functional-interfaces.md).

---

## The presentation viewer: `present.html`

`present.html` shows this README, the presenter guide and all docs with a sidebar, big fonts, a light/dark switch, syntax-highlighted Java and rendered Mermaid diagrams. Double-click it to open it in a browser. It needs internet access, because it loads `marked`, `mermaid` and `highlight.js` from `cdn.jsdelivr.net`.

Browsers do not let a page opened from `file://` read other local files, so the Markdown is **embedded** into `present.html`. After you edit any `.md` file, regenerate it (from the project folder):

```text
java tools/GeneratePresent.java
```

This uses Java's single-file source launcher (JDK 11+), so there is nothing to compile first. It prints the files it embedded.

Keyboard shortcuts in the viewer: `Left` / `Right` arrows switch documents, `+` / `-` change the font size, `D` toggles dark mode.
