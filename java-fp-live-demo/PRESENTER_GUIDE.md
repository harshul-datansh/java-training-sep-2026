# Presenter Guide

The live script for the session. Three topics, in order, one story: **our shop's order-processing backend**. Every demo and exercise uses the same 7 orders from `SampleData.orders()`.

Suggested total: about **2 h 50 min** including two breaks. The timings below are a plan, not a rule; cut exercises 3-4 of a topic if you run late.

| Block | Time | Minutes |
|---|---|---|
| Intro + the Order record | 0:00 | 10 |
| Topic 1: Lambdas | 0:10 | 45 |
| Break | 0:55 | 10 |
| Topic 2: Functional Interfaces | 1:05 | 40 |
| Break | 1:45 | 10 |
| Topic 3: Streams | 1:55 | 50 |
| Wrap-up and questions | 2:45 | 5 to 10 |

---

## Before the session (checklist)

- [ ] `mvn -q compile` runs with no output (dependencies are downloaded, code compiles).
- [ ] `mvn test -Dtest=Ex01_SortByTotalTest` runs and shows **red** (expected: the exercise is not solved yet).
- [ ] `present.html` is open in the browser and the diagrams show (needs internet for the CDN scripts).
- [ ] IDE font size is large (at least 18 pt). Terminal font large too.
- [ ] Close notifications. Have `solutions/` ready but not open.
- [ ] Optional: make a copy of the project, so you can reset the exercises after the session.

**Screen layout that works well:** IDE on the left two thirds, terminal at the bottom, `present.html` in a separate window you switch to for diagrams and tables.

---

## How to run a live exercise (same routine every time)

1. Open the exercise class. Read the docstring and the hint **out loud**.
2. Run its test: `mvn test -Dtest=<ClassName>Test`. Show the red result and the `UnsupportedOperation TODO` message.
3. Ask the room: "What goes in? What comes out? Which lambda shape is that?"
4. Type the answer the room suggests, even if it is wrong. Run the test. A wrong answer with a clear failure message is a great teaching moment.
5. Green? Open the matching class in `solutions/` and read the "why" comment together.

---

## Intro (10 min)

**Open:** `src/main/java/com/training/demo/model/Order.java`, then `SampleData.java`.

**Say:**

- "Today we are the backend team of a small shop. Everything revolves around this `Order` record."
- "A record gives us a constructor, getters like `total()` and `id()`, `equals`, `hashCode` and `toString`, for free."
- "Today is about **passing behaviour around**: 'sort like this', 'keep only these', 'turn each order into that'."

---

## Topic 1: Lambda Expressions (45 min)

Doc to show: `docs/01-lambdas.md`

### Why it exists

To pass a small piece of behaviour (like "compare by total") you used to need a whole anonymous class. A lambda is just the interesting part: parameters, arrow, body.

**Before**

```java
Comparator<Order> byTotal = new Comparator<Order>() {
    @Override
    public int compare(Order a, Order b) {
        return Double.compare(a.total(), b.total());
    }
};
```

**After**

```java
Comparator<Order> byTotal = (a, b) -> Double.compare(a.total(), b.total());
```

### Steps

| # | Min | Open | Run | Talking points |
|---|---|---|---|---|
| 1 | 7 | `lambdas/demo/Demo01_AnonymousClassVsLambda` | `Demo01_AnonymousClassVsLambda` | Count the lines of ceremony vs the one line of logic. The compiler knows the method (`compare`), the types (`Order`), the return type (`int`) from the target type. Same output both times. |
| 2 | 7 | `Demo02_LambdaSyntax` + diagram "Anatomy" in the doc | `Demo02_LambdaSyntax` | Parameters, arrow, body. Shorthands: one parameter means no parentheses, one expression means no braces and no `return`. Zero parameters need `()`. |
| 3 | 7 | `Demo03_EffectivelyFinal` + the sequence diagram | `Demo03_EffectivelyFinal` | The lambda is **used after** `totalAbove` returned, and on **another thread**. Java copies the value. Then **uncomment** the `count++` lines and show the compile error live. Re-comment. |
| 4 | 5 | `Demo04_LoopVariableTrap` | `Demo04_LoopVariableTrap` | One `i` for the whole loop, changed by `i++`. Fix: copy into a local, or use for-each. Optional: `javac --release 17 -d target/broken broken-examples/02-loop-variable/*.java` |
| 5 | 7 | `Demo05_MethodReferences` + the "which kind" diagram | `Demo05_MethodReferences` | 4 kinds. Spend the time on bound vs unbound: `vipEmail::equals` (object fixed now) vs `Order::customerEmail` (object arrives as first parameter). |
| 6 | 3 | `Demo06_CommonMistakes` | `Demo06_CommonMistakes` | Same answers, but only one version can be named, tested and read. |
| 7 | 10 | `lambdas/exercises/Ex01` to `Ex04` | `mvn test -Dtest=Ex01_SortByTotalTest` etc. | Do Ex01 and Ex02 together for sure. Ex04 is the loop trap for real: let the room hit the compile error first. |

### Misconceptions interns have

- **"A lambda is a new kind of object/function type."** It is an instance of an interface; the lambda body becomes that interface's one method.
- **"Effectively final means the object cannot change."** Only the *variable* cannot be reassigned. `List<String> names = new ArrayList<>(); r = () -> names.add("x");` compiles. (It is still a bad idea: see Streams.)
- **"Lambdas are always faster/better."** They are shorter. For long logic, a named method is clearer.
- **"`this` inside a lambda is the lambda."** It is the surrounding object, unlike an anonymous class.
- **"`Order::customerEmail` calls the method now."** It does not call anything; it creates a function to call later.

---

## Topic 2: Functional Interfaces (40 min)

Doc to show: `docs/02-functional-interfaces.md`

### Why it exists

A lambda needs a **type** so the compiler knows which method it implements. Java reused interfaces for that: any interface with exactly one abstract method can hold a lambda. `java.util.function` provides the common shapes, so you rarely write your own.

**Before**: invent an interface for every kind of behaviour

```java
interface OrderFilter { boolean matches(Order o); }
interface OrderToText { String convert(Order o); }
```

**After**: use the standard shapes, and get helper methods for free

```java
Predicate<Order> isPaid = o -> o.status() == OrderStatus.PAID;
Function<Order, String> toEmail = Order::customerEmail;
Predicate<Order> paidAndPremium = isPaid.and(Order::premium);
```

### Steps

| # | Min | Open | Run | Talking points |
|---|---|---|---|---|
| 1 | 6 | `functional/demo/Demo01_WhatMakesItFunctional` | `Demo01_WhatMakesItFunctional` | 4 methods declared, only `check` counts. Default, static and `Object` methods do not. |
| 2 | 6 | `broken-examples/03-*`, `04-*`, `05-*` | the three `javac` commands from the README | With the annotation the error is **in the interface**. Without it the error appears **where the lambda is used**, maybe in someone else's code. The annotation is optional; it is a safety check. |
| 3 | 7 | `Demo02_CoreFunctionalTypes` + the table and class diagram in the doc | `Demo02_CoreFunctionalTypes` | Pick by shape: what goes in, what comes out. Ask the room for each: "which one is `Order -> boolean`?" (The Supplier line prints a random id; that is expected.) |
| 4 | 5 | `functional/DiscountRule.java` + `Demo03_CustomFunctionalInterface` | `Demo03_CustomFunctionalInterface` | When a domain name beats `Function<Order, Double>`. `plus` is a default method, `none` is static. `checkout(order, rule)`: behaviour as a parameter. |
| 5 | 6 | `Demo04_ComposingFunctions` + the andThen/compose diagram | `Demo04_ComposingFunctions` | Each step prints. `andThen` = left to right. `compose` = the argument first. 187.5 vs 200.0 shows why order matters. `Function.identity()` as a "no change" step. |
| 6 | 10 | `functional/exercises/Ex01` to `Ex04` | `mvn test -Dtest=Ex01_CoreTypesTest` etc. | Ex03: "no new lambdas, only `and`/`or`/`negate`/`not`". Ex04: the test fails with 200.0 if the order is wrong. |

### Misconceptions interns have

- **"`@FunctionalInterface` makes an interface functional."** One abstract method makes it functional; the annotation only checks.
- **"Default methods break the one-method rule."** They do not count; they have a body.
- **"`f.compose(g)` runs f first."** It runs **g** first. Read it as "f of g".
- **"I need my own interface for every lambda."** Check `java.util.function` first.
- **"`Predicate.not` and `negate` are different."** Same result; `Predicate.not(...)` (Java 11+) is handy with method references: `filter(Predicate.not(String::isBlank))`.

---

## Topic 3: Stream API (50 min)

Doc to show: `docs/03-streams.md`

### Why it exists

Most backend code is "take a collection, keep some, transform them, summarise them". Loops say *how*; streams say *what*, using the lambdas and functional interfaces from topics 1 and 2.

**Before**

```java
List<String> ids = new ArrayList<>();
for (Order o : orders) {
    if (o.isPaid()) {
        ids.add(o.id());
    }
}
```

**After**

```java
List<String> ids = orders.stream()
        .filter(Order::isPaid)
        .map(Order::id)
        .toList();
```

### Steps

| # | Min | Open | Run | Talking points |
|---|---|---|---|---|
| 1 | 5 | `streams/demo/Demo01_WhatIsAStream` | `Demo01_WhatIsAStream` | Not a data structure; the list still has 7 orders. Single use: show the real `IllegalStateException` message. |
| 2 | 5 | `Demo02_StreamSources` | `Demo02_StreamSources` | Collection, `Stream.of`, `Arrays.stream`, `IntStream.range` (end exclusive), `Files.lines` in try-with-resources, `Stream.iterate` + `limit`. |
| 3 | 7 | `Demo03_IntermediateAndTerminal` + the pipeline diagram and table | `Demo03_IntermediateAndTerminal` | Intermediate returns a Stream (chainable, lazy). Terminal returns a result and starts the work. `flatMap`: a list of lists becomes one list. |
| 4 | 8 | `Demo04_Laziness` + the sequence diagram | `Demo04_Laziness` | Part 1: nothing printed. Part 2: filter/map **interleave**, one element at a time. Part 3: `limit(2)` stops after A-102. Let the room predict each output before you run it. |
| 5 | 7 | `Demo05_Collectors` | `Demo05_Collectors` | `toList`, `toMap` (and the real "Duplicate key" crash, then the merge fix), `groupingBy` + `counting`, `joining`. |
| 6 | 6 | `Demo06_EndToEndDto` + the last diagram | `Demo06_EndToEndDto` | Loop version vs pipeline, same result (`true`). The pipeline reads like the requirement. Mapping lives in a named `toDto` method. |
| 7 | 12 | `streams/exercises/Ex01` to `Ex04` | `mvn test -Dtest=Ex01_FilterAndMapTest` etc. | Ex02: why is `0.0` the right start value for `reduce`? Ex04 is a stretch goal; show the solution if time is short. |

### Misconceptions interns have

- **"A stream is like a List."** It holds nothing and can be used once.
- **"`filter` removes elements from my list."** The source is never changed.
- **"Streams process step by step: all filtering, then all mapping."** Elements go through the whole pipeline one at a time (Demo04 proves it).
- **"`peek`/`map` with a `println` will always print."** Only when a terminal operation runs.
- **"`forEach` + `list.add` is how you collect."** Use `collect`/`toList`. Side effects in lambdas break with parallel streams.
- **"Streams are always faster than loops."** Not automatically. Choose them for readability; measure before claiming speed.

---

## Wrap-up (5 to 10 min)

- A **lambda** is the body of one method, written as a value.
- A **functional interface** is the type that gives a lambda its meaning (one abstract method).
- A **stream** is a lazy pipeline that takes lambdas as its steps.
- Homework: finish the remaining exercises; run `mvn test` until it says `Tests run: 37, Failures: 0, Errors: 0`.
