# Trainer Notes

For the person walking interns through this material live. These notes cover talking points, timings, the misconceptions interns actually have, and what to watch for in each exercise.

**Total time:** about 5 hours of teaching plus exercise time. Split it over two or three sessions: Topic 1 alone, then Topic 2, then Topic 3.

## Before the session

- Run `mvn clean test` once on the machine you'll present from, so dependencies are downloaded. Expect 170 failing tests. That's correct, because the exercises aren't solved yet.
- Run `mvn clean test -Psolutions` to confirm every solution passes the same tests: 170 green. Use `clean` whenever you switch between the two modes.
- Open the root `pom.xml` in your IDE so all three modules import, then check that you can run an example's `main` with the green arrow.
- GitHub renders the READMEs' Mermaid diagrams as they are. IDE Markdown previews may need Mermaid support switched on or a plugin installed, so check that before you present.

## General tips

- **Ask the room to predict, then run.** Most examples print a trace. Asking "what will this print?" before running it is the fastest way to surface misconceptions.
- **Show the compiler errors live.** Every "does not compile" snippet in the examples is commented out. Uncomment one, let the IDE go red, read the message together, and put it back.
- **Keep solutions closed** until someone is green or stuck for about 10 minutes. The solution comments explain *why*, so they're worth reading together afterwards.

---

## Topic 1: Lambda Expressions (~90 min)

### Talking points

| # | Segment | Key message | Min |
|---|---------|-------------|-----|
| 1 | Motivation (`Example01`) | Some APIs need behaviour, and before Java 8 behaviour had to be wrapped in an object. Put the anonymous class and the lambda side by side and ask "which line matters?" | 10 |
| 2 | Syntax (`Example02`, `Example03`, README anatomy diagram) | Parameters, arrow, body. The shorthand rules: drop the parentheses only for one untyped parameter; braces mean `return`. | 15 |
| 3 | Capture: the *why* (`Example04`) | A lambda can run after its method has returned, so Java copies the value. If mutation were allowed, the copy and the original would silently disagree. Walk through the sequence diagram. | 15 |
| 4 | Loop trap and fields (`Example05`) | A classic `for` counter can't be captured, so copy it. Enhanced-for variables can. Fields can be changed because `this` is captured. | 10 |
| 5 | Method references (`Example06` to `Example09`) | They are shorthand for "a lambda that just calls this method". The four kinds differ in where the object comes from. Use the decision flowchart in the README. | 20 |
| 6 | Extract, don't cram (`Example10`), then exercises | If a lambda needs comments, give it a name. Start Exercises 1 to 3 together, then let them continue. | 20 |

### Misconceptions interns have

| Misconception | Reality | How to address it live |
|---------------|---------|------------------------|
| "A lambda is a new kind of function, separate from objects" | It's an object implementing an interface with one abstract method | Assign the same lambda to a `Comparator<String>` variable and call `.compare()` on it |
| "Effectively final is just an arbitrary rule" | The lambda holds a *copy* and can outlive the method or run on another thread | `Example04` part 2: return a lambda from a method, then call it after the method has finished |
| "I can't use a list in a lambda if I add to it" | The rule is about reassigning the *variable*, not changing the object | Exercise 4 `liveItemCount`: the list grows and the lambda sees it |
| "`String::length` and `myString::length` are the same" | The first is unbound (the parameter is the object); the second is bound to one string | Assign each one to `Function<String,Integer>` and to `Supplier<Integer>` and see which compiles |
| "Method references are faster or more magical than lambdas" | They're just shorter syntax for the same thing | Write the lambda and the reference for each kind next to each other |
| "Parameter types must be written" | They're inferred from the target interface | Remove the types from `(String a, String b) ->` and show that it still compiles |

### Exercises

| Exercise | Concept | Difficulty | Watch for |
|----------|---------|------------|-----------|
| 1 AnonymousToLambda | Anonymous class to lambda | Easy | Keeping `return` without braces. Thinking `addToLog` runs straight away. |
| 2 SyntaxForms | Zero, one and two parameters | Easy | Leaving out `()` for zero parameters. Adding needless braces. |
| 3 BlockBodies | Multi-step bodies | Medium | A missing `return` on one branch. `substring` off-by-one. Forgetting to remove spaces first. |
| 4 VariableCapture | Effectively final, loop trap | Medium | Capturing `i`. Reaching for `int[]` hacks. Calling `cart.size()` *outside* the lambda, which freezes the value. |
| 5 MethodReferences | All four kinds | Medium | Writing lambdas anyway (the tests can't tell, so review the code). Kind 2 vs 3. `List::new` doesn't compile. |
| 6 ExtractToMethod | Named method plus method reference | Medium/Hard | Putting the loop inside the lambda. Forgetting `null`. `weakPasswords` must keep the original order. |
| 7 CalculatorCommands | Behaviour in a `Map` | Hard | Using a `switch` instead of the map. Not handling the missing key (NPE instead of a clear message). Stepping through the chain wrongly (`i += 2`). |

---

## Topic 2: Functional Interfaces (~90 min)

### Talking points

| # | Segment | Key message | Min |
|---|---------|-------------|-----|
| 1 | Why they exist | Before Java 8, every callback needed its own `interface OrderFilter` plus an anonymous class. After, one `Predicate<Order>` plus a lambda. A lambda has no type of its own; it needs a target type. | 10 |
| 2 | The rule (`Example01`, `Example02`) | Exactly one abstract method. Default, static and redeclared `Object` methods don't count (show `Comparator`, which declares `equals`). Inherited abstract methods do count. Compile both broken versions live to show *where* the error lands with and without `@FunctionalInterface`. | 15 |
| 3 | The core nine (`Example03` to `Example06`) | Walk the class diagram. `UnaryOperator` *is a* `Function`; the `Bi` versions take two inputs. The method names differ: `apply`, `test`, `accept`, `get`. `Runnable` lives in `java.lang`. Mention primitive specialisations in one sentence. | 15 |
| 4 | Custom interface (`Example07`) | The same interface implemented as a named class, an anonymous class and a lambda: no magic. Write your own for a domain name, three or more parameters, or checked exceptions. | 10 |
| 5 | Combinators (`Example08`, `Example09`, `Example10`) | `andThen` vs `compose`: ask the room whether the answer is 13 or 16 before running it. Show the short-circuit trace. Combinators build a *new* function; nothing runs until `apply` or `test` is called. | 20 |
| 6 | Laziness and mistakes, then exercises | A `Supplier` re-runs on every `get()`. The `getOrDefault(key, fallback.get())` trap. Start the exercises. | 20 |

### Misconceptions interns have

| Misconception | Reality | How to address it live |
|---------------|---------|------------------------|
| "`@FunctionalInterface` is what makes it functional" | It's optional: a compiler guard plus documentation | Remove the annotation from `ShippingCost` in `Example02` and show that the lambda still compiles |
| "Default and static methods break the one-method rule" | Only abstract methods count | Open `Comparator`'s Javadoc: it has many default and static methods, plus `equals` |
| "`f.andThen(g)` and `f.compose(g)` are the same" | `andThen` runs `f` first; `compose` runs `g` first | Run `Example08` and have the room predict 13 or 16 first |
| "`Function<T, Boolean>` is just a `Predicate`" | It's a different type: `removeIf` rejects it, and it has no `and` or `or` | Compile `list.removeIf(f)` and read the error together |
| "A `Supplier` holds its value" | It's code that runs again on every `get()` | Use a Supplier with a counter: call `get()` three times and print the counter |
| "`and` / `or` always evaluate both sides" | They short-circuit like `&&` and `\|\|` | Show the `Example09` trace, then ask: what if the second predicate writes to a database? |

### Exercises

| Exercise | Concept | Difficulty | Watch for |
|----------|---------|------------|-----------|
| 1 IsItFunctional | Counting abstract methods | Easy | Forgetting inherited abstract methods. Thinking zero abstract methods is fine. The `equals` rule. |
| 2 PickTheInterface | One lambda per core type | Easy | `List::of` (immutable) for the mutable-list Supplier. `matches` vs `contains`. `Map.merge` for the counter. |
| 3 DiscountRules | Custom functional interface | Easy/Medium | Looking for `andThen` on `DiscountRule` (there isn't one; they write the chaining). Forgetting the floor at 0. |
| 4 FunctionComposition | `andThen` vs `compose` | Medium | Getting the order the wrong way round. Stripping characters before replacing spaces with hyphens. |
| 5 PredicateCombinators | `and` / `or` / `negate`, grouping | Medium | Wrong grouping lets unpaid domestic orders through (a test catches this). Changing the input list in `excluding`. |
| 6 SupplierLaziness | Laziness, memoize, a stateful Supplier | Medium/Hard | `getOrDefault(key, fallback.get())`. Trying to reassign a captured local in `memoize`. |
| 7 RuleEngine | Generic combining, identity values | Hard | Wrong starting value (always-true for AND, always-false for OR). `UnaryOperator.andThen` returns a `Function`. Losing insertion order. |

---

## Topic 3: Stream API (~120 min)

### Talking points

| # | Segment | Key message | Min |
|---|---------|-------------|-----|
| 1 | Why streams | Live-code the README's loop version (temporary list, `if`, `add`, sort, second loop), then the five-line pipeline. Ask "where is the business rule?" in each. | 15 |
| 2 | Mental model before syntax (`Example01`) | Not a container, the source is untouched, single-use, lazy, can be infinite. Let the second `count()` throw. | 15 |
| 3 | Sources and pipeline shape (`Example02`, `Example03`, flowchart) | `range` vs `rangeClosed`, `iterate` plus `limit`. `Files.lines` must go in try-with-resources. | 15 |
| 4 | Intermediate operations (`Example04` to `Example06`) | `filter`, `map`, `flatMap` (nested list vs flat list), `sorted`, `distinct`, `limit`, `skip`. Stateful vs stateless. | 25 |
| 5 | Laziness and terminal operations (`Example08`, `Example07`) | Walk the sequence diagram, then run the trace: filter and map alternate for each element, `dave` and `eve` never appear, and `anyMatch` stops at `cy`. `Optional` from `findFirst`, `min` and `max`. | 20 |
| 6 | Collectors and the end-to-end example (`Example09` to `Example13`) | `toList()` vs `Collectors.toList()`. The `toMap` duplicate-key crash and the merge function. `groupingBy` plus `counting` or `mapping`. `joining`. Finish with `Example13` as a "real service method". | 30 |

### Misconceptions interns have

| Misconception | Reality | How to address it live |
|---------------|---------|------------------------|
| "A stream is a kind of list" | It stores nothing; it's a pipeline that pulls from a source | Printing a stream in `Example01` shows `ReferencePipeline$Head`, not data |
| "`filter` runs over the whole list, then `map` runs" | Elements pass through one at a time, and short-circuiting operations stop early | Have the room predict the `Example08` trace before running it |
| "I can keep a stream in a field and reuse it" | Single-use: a second terminal operation throws `IllegalStateException` | `Example01` part 3. Keep the source, or a `Supplier<Stream<T>>`, not the stream |
| "`map` changes the elements or the list" | It produces new values; the source is unchanged | `Example01` part 1 prints the source after the pipeline |
| "`toMap` overwrites duplicates like `put` does" | It throws `Duplicate key ...` unless you pass a merge function | `Example10`: show the real message, then add `Math::max` |
| "`forEach(x -> result.add(x))` is the stream way to fill a list" | That's a side effect; `collect` or `toList()` is the stream way | Refactor it live into `.toList()` and mention that it breaks with `parallel()` |

### Exercises

| Exercise | Concept | Difficulty | Watch for |
|----------|---------|------------|-----------|
| 1 CreatingStreams | Sources, `rangeClosed`, `iterate` plus `limit`, `Files.lines` | Easy | A missing `boxed()` on an `IntStream`. `range` vs `rangeClosed`. `iterate` without `limit` hangs. The tests can't detect a missing try-with-resources, so check it by eye. |
| 2 FilterAndMap | `filter`, `map`, method references | Easy | Mapping to the name *before* filtering on price or stock. `==` instead of `equals` for strings. `<` vs `<=`. |
| 3 SortDistinctLimitSkip | Paging, top N, `sorted` plus `findFirst` | Easy/Medium | Page numbers are 1-based, so skip `(page - 1) * size`. Sort and `distinct` before `skip` and `limit`. The out-of-stock Gel Pen trap. |
| 4 FlatMap | `flatMap`, `mapToInt().sum()` | Medium | `map(Article::tags)` gives nested lists. Filtering by customer after flattening, when the customer is no longer known. |
| 5 TerminalOperations | Match operations, `count`, `reduce`, `findFirst`, `max` | Medium | Wrong `reduce` identity. `allMatch` on an empty list is `true`. "Due today" is not overdue. |
| 6 Collectors | `toMap` plus merge, `groupingBy` plus downstream, `joining` | Medium/Hard | Two-argument `toMap` on department crashes. `counting()` returns `Long`. `joining` needs strings. |
| 7 OrderReport | End-to-end DTO pipeline | Hard | Forgetting to exclude CANCELLED orders. Sorting customer reports explicitly (a `HashMap` has no order). Most popular SKU needs two streams. |
