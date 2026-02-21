# OOP + Java: Top-Tier Learning Outline

### A Serious Engineer's Curriculum — Not a Tutorial Checklist

---

> **How to use this document** This is not a speed run. Each phase builds on the last. The spiral structure means you will revisit earlier work with new eyes — that is intentional. Skipping phases doesn't make you faster; it makes your foundation hollow.

---

## 🧭 Phase 0 — Environment, Tooling & Git Discipline

**Duration: 2 days** _Remove magical thinking about Java execution. Establish professional habits from day one._

### JVM Reality

- JDK vs JRE vs JVM — what each actually does
- The bytecode pipeline: `.java` → `.class` → execution
- Compilation vs interpretation (and JIT)
- Stack vs heap (conceptual, not tuning)
- Garbage collection fundamentals: what it is, why it exists

### Setup

- OpenJDK (latest LTS)
- IntelliJ IDEA (not VS Code — the debugger tooling matters)
- Maven or Gradle — pick one, configure it now, use it throughout

### 🔴 Git From Day One _(New — Non-Negotiable)_

Git is not a backup tool. It is a thinking tool.

- `git init` your project on day one
- Commit at every meaningful checkpoint, not at the end of the day
- Write commit messages that explain _why_, not what: `"Switch to immutable design after invariant violation"` beats `"updated BankAccount"`
- Branch for experiments: `git checkout -b experiment/static-vs-oop`
- A portfolio-grade capstone with no meaningful git history is half a portfolio

### 🔴 Debugger First Contact _(Retained + Sharpened)_

Before writing real programs, master the debugger:

- Breakpoints and conditional breakpoints
- Step into / step over / step out
- Variable inspection and watch expressions
- Reading and navigating stack traces

**Mini Drill:**

- Intentionally cause a `NullPointerException`
- Trace it with the debugger — do not just read the error message
- Fix it and commit with a message explaining the root cause

> **Why here?** Elite engineers debug as naturally as they breathe. Introducing the debugger in Phase 0 means you use it throughout, not as a last resort.

---

## 🌀 Phase 1 — Java Fundamentals Through Object Thinking

**Duration: 5–7 days** _Move fast through syntax, but every decision is framed in ownership terms._

### Core Syntax

- Primitives vs references — and why the distinction matters deeply
- Methods: static vs instance
- Control flow
- Arrays
- Reading the Java API documentation fluently _(New — see below)_

### 🔴 Documentation Fluency _(New)_

Elite engineers don't Google everything — they read official docs.

- Learn to navigate `docs.oracle.com` directly
- Read a class's full Javadoc before using it
- Understand what the spec says, not just what Stack Overflow says
- **Drill:** Look up `String`, `Integer`, and `Math` in the official Javadoc before using any method from them

### Mental Habit

At every decision point, ask: _"Who should own this behavior?"_

### 🔁 Spiral Exercise Set A — Three Versions

Build the same system three ways:

1. Procedural
2. Static utility class
3. Object-oriented

**Suggested domain:** Bank balance simulator

**For each version:**

- Set breakpoints, trace execution
- Compare your mental model vs what actually happens
- Commit each version as its own branch; write a comparison in the commit message

---

## 🧩 Phase 2 — Object Modeling Bootcamp _(Critical)_

**Duration: 1 week** _Learn to discover classes from messy requirements._

### Core Concepts

- Responsibility-driven design
- Invariants: what they are and why they must be protected
- Avoiding anemic models (objects that are just data bags with getters)
- Spotting God objects before they form
- Behavior vs data thinking

### 🔴 Null Handling Philosophy _(New)_

This is where a huge percentage of real-world bugs live. Address it explicitly:

- What `null` actually means vs what people use it for
- Defensive null checks vs design that prevents nulls from entering
- Introduction to `Optional<T>` — when it helps and when it's overused
- The Null Object Pattern as a design alternative
- **Rule:** Never return `null` from a method without a documented contract explaining why

### 🔁 Modeling Stress Tests

For each domain (library, ride-share, shopping cart):

Produce three artifacts:

1. **Naive model** — your first instinct
2. **Improved model** — after critique
3. **Written critique of both** — what was wrong, what you changed, and why

This meta-cognition step is what separates strong learners from fast coders.

### Git Checkpoint

Tag your best model: `git tag v1-library-model`

---

## 🔒 Phase 3 — Encapsulation, Invariants & Test Confidence

**Duration: 1.5 weeks** _Prove your objects cannot be corrupted._

### Core Concepts

- Access modifiers: not just `public`/`private`, but _why_ each choice
- Defensive design: copy-in, copy-out, validation on construction
- Immutability: what it means, when to use it, how to enforce it
- Tell-don't-ask principle

### 🔴 Testing From This Point Forward _(Elevated — Previously Too Late)_

JUnit is introduced here, but the mindset starts now and never stops:

- Write tests _alongside_ code, not after
- Test the contract, not the implementation
- Every public method has at least one test for the happy path and one for a violation
- **Invariant testing:** write tests that _try to corrupt_ the object and verify it refuses

**Basic JUnit Setup:**

- `@Test`, `@BeforeEach`, `@AfterEach`
- `assertEquals`, `assertThrows`, `assertNotNull`
- Arrange / Act / Assert structure — name your tests in plain English

### 🔁 Spiral Exercise Set B — Refactor Phase 2 Models

- Make all fields private
- Enforce invariants on construction
- Write tests that attempt every violation you can imagine
- Commit: `"Add invariant tests — BankAccount rejects negative balance"`

### 🔴 Failure Archaeology _(Retained + Sharpened)_

When a test fails:

1. Read the stack trace — don't skip to the bottom
2. Reproduce the failure with the debugger, not by guessing
3. Write the explanation in a comment before fixing it
4. Fix it, then verify the test passes — then commit

---

## 🎭 Phase 4 — Polymorphism as Default Thinking

**Duration: 1 week** _Stop thinking in types. Start thinking in behavior._

### Core Concepts

- Method overriding and `@Override`
- Dynamic dispatch — what actually happens at runtime
- Abstract classes: when to use them and when they're a trap
- Polymorphic references: why you program to the supertype

### Mental Shift

> _Depend on behavior, not concrete types._

### 🔴 Strategy Pattern Here, Not Phase 11 _(New — Moved Earlier)_

Strategy is the natural example of polymorphism done right. Introduce it now:

- Replace `if/else` type-switching with polymorphic dispatch
- **Example:** Payment processing — `CreditCardPayment`, `PaypalPayment`, `CryptoPayment` all implement `PaymentStrategy`
- Write a test per strategy type — they should be interchangeable

### 🔁 Spiral Exercise Set C

Revisit your Phase 2 domains. Introduce polymorphism where the design calls for it — not everywhere, only where it reduces the need to know concrete types.

### 🔴 Smell Detection

For each solution, ask:

- Did I hardcode types anywhere? (`instanceof` is often a symptom)
- Did I violate the Open/Closed Principle?
- Would adding a new type require changing existing classes?

---

## 🧬 Phase 5 — Interfaces, Composition & Design Maturity

**Duration: 1 week** _The most important design decisions in Java happen here._

### Core Concepts

- Interfaces: contracts, not implementations
- Composition over inheritance — and why this is almost always right
- Dependency inversion: depend on abstractions, not concretions
- When inheritance is actually justified (and it's rarer than you think)
- `default` methods: useful, but dangerous if overused

### 🔴 Observer Pattern Here, Not Phase 11 _(New — Moved Earlier)_

Observer is the natural example for interfaces and loose coupling:

- **Example:** An event system where `EventListener` is an interface, and multiple listeners respond to the same event without knowing about each other
- Reinforces: program to interfaces, not implementations

### 🔴 Design Autopsy _(Retained + Sharpened)_

Intentionally examine bad designs and refactor them. Required diagnoses:

- **Tight coupling** — classes that are impossible to test in isolation
- **Inheritance abuse** — using `extends` when composition would serve better
- **Feature envy** — a method that knows too much about another class
- **Anemic domain** — objects with no behavior, just getters and setters

For each: identify the smell, explain the consequence, refactor it, write tests proving the refactor didn't break behavior.

---

## 📦 Phase 6 — Generics: The Real Story

**Duration: 4–5 days** _Generics are not a syntax feature. They are how Java's type system thinks._

> This phase is now standalone and expanded from the original outline, where generics were buried inside the Collections phase. That was a mistake.

### Core Concepts

- Why generics exist: type safety without sacrificing reuse
- Generic classes and generic methods
- Bounded type parameters: `<T extends Comparable<T>>`
- Wildcards: `? extends`, `? super`, and unbounded `?`
- Type erasure: what it means and what constraints it imposes
- Why you cannot do `new T[]`

### Exercises

- Write a generic `Pair<A, B>` class
- Write a generic `Stack<T>` with `push`, `pop`, and `peek`
- Write a utility method that accepts `List<? extends Number>` and returns the sum
- Write tests for all of the above

> **Why separate?** The Collections Framework makes far more sense once you understand what `List<E>` actually means. Without this phase, generics remain magic syntax.

---

## 🗃️ Phase 7 — Collections, Streams & Performance Intuition

**Duration: 2 weeks** _Know your data structures. Don't just use them._

### Core Collections

- `List`, `Set`, `Map` — the interfaces first, then the implementations
- Iterators and the `Iterable` contract
- When to use `ArrayList` vs `LinkedList` (and why `LinkedList` is rarely the answer)
- `HashMap` internals: hashing, buckets, load factor, resizing
- `HashSet`, `LinkedHashMap`, `TreeMap` — when each is appropriate
- `Collections` utility class

### Build Tools

- Maven or Gradle: dependency management, build lifecycle, running tests
- Structure your projects properly from this point forward

### Streams & Lambdas _(With a Decision Framework)_

The original outline said "with restraint" — here is the actual framework:

**Use streams when:**

- The operation is a pure transformation pipeline (filter → map → collect)
- The intent is clearer expressed as a pipeline than a loop
- You are not mutating external state

**Use loops when:**

- You need to mutate local state mid-operation
- Readability suffers (deeply nested lambdas are worse than a loop)
- You need early exit with meaningful meaning beyond `findFirst()`

**Never use streams to be clever. Use them to be clear.**

### 🔴 Performance Intuition Layer _(Retained + Sharpened)_

Not premature optimization. Engineering intuition.

- Benchmark `ArrayList` vs `LinkedList` for random access vs insertion
- Measure `HashMap` lookup at 100, 10,000, and 1,000,000 entries
- Measure stream pipeline vs loop on a large dataset — note where the crossover is
- Cost of autoboxing: benchmark `List<Integer>` vs `int[]` for numerical work
- **Rule:** Measure before you guess. Engineers measure. Guessers speculate.

### 🔁 Spiral Exercise Set D — Domain Models Revisited

Return to your Phase 2/3 models and:

- Introduce collections properly (replace arrays where appropriate)
- Ensure collections are never exposed raw — return unmodifiable views
- Write tests for collection behavior (empty, single-element, large-n)

---

## ⚠️ Phase 8 — Exceptions, Robustness & Null Reinforcement

**Duration: 5 days** _Your code should fail loudly, clearly, and exactly where the problem is._

### Core Concepts

- Checked vs unchecked exceptions — the real distinction and the real debate
- Custom exceptions: when to create them and what information they must carry
- Fail-fast vs fail-safe — which is appropriate when
- Validation strategies: constructor validation, builder validation, method pre-conditions
- Exception chaining: preserve root causes

### 🔴 Exception Design Review _(Retained + Sharpened)_

For every exception in your codebase:

- Is the message meaningful to someone who didn't write this code?
- Is it too generic? (`IllegalArgumentException` with no message is useless)
- Is the boundary correct? Should this be caught here or propagated?
- Does catching it actually add value, or are you swallowing information?

### Refactor

Apply to all previous projects. Every failure mode should now be a deliberate design decision, not an accident.

---

## 🧵 Phase 9 — Concurrency Awareness _(Light but Essential)_

**Duration: 4–5 days** _Not mastery. Dangerous enough to avoid rookie mistakes._

### Core Concepts

- What a thread is and why the JVM uses multiple
- Race conditions: why they are hard to reproduce and easy to introduce
- Why GUI frameworks have an event dispatch thread — and what happens when you violate it
- Basic synchronization: `synchronized`, `volatile`, and why they are not magic
- Thread-safety mindset: design your objects to be safe, not just your methods

### Mini Demos

- Counter race condition: two threads increment the same counter, observe corruption
- Fix with `synchronized` — observe the fix
- Observe what happens when you touch Swing components from a background thread

### What You Are Not Doing

You are not learning `CompletableFuture`, `ExecutorService`, `ReentrantLock`, or concurrent data structures yet. That is a separate, serious subject. This phase is about _awareness_ — knowing that the problem exists so you don't accidentally walk into it.

---

## 🖥️ Phase 10 — GUI with Strict MVC Discipline

**Duration: 2–3 weeks** _The UI tests whether your architecture is actually clean._

### Step 10A — Swing Foundations

- Layout managers: `BorderLayout`, `GridLayout`, `BoxLayout`, `GridBagLayout`
- Event listeners and the event dispatch thread
- Component hierarchy
- **Non-negotiable rule: zero business logic in the UI layer**

### Step 10B — Enforced MVC

Every GUI project must enforce:

- **Model** — pure domain logic, no imports from `javax.swing`
- **View** — UI rendering only, no logic, no decisions
- **Controller** — coordinates between model and view, handles events

**Required projects:**

- Calculator (tests numerical logic in isolation from UI)
- Todo Manager (tests collection management and persistence)
- Student Grade System (tests domain modeling under GUI stress)

For each: write Model tests that run with no UI present. If you can't, your architecture is broken.

### Step 10C — Optional Modern Track

After Swing mastery:

- JavaFX and FXML
- Property binding and `ObservableList`
- Comparison: how MVC maps to JavaFX's architecture

---

## 🔍 Phase 11 — Structured Code Reading

**Duration: Ongoing — weekly ritual** _Pattern recognition is built by reading excellent code, not just writing mediocre code._

### Weekly Ritual

1. Select a small, well-regarded class from the JDK or a quality open-source project
2. Predict what it does before reading the implementation
3. Read it — slowly and with the Javadoc open
4. Run it with the debugger if behavior is surprising
5. Compare your prediction vs reality
6. Write a one-paragraph critique of the design decisions

### Suggested Targets (in order)

- `java.util.ArrayList` — selected methods, not all
- `java.util.HashMap` — understand `put` and `get` at the source level
- `java.util.Objects` — utility patterns
- Small, well-written MVC repositories on GitHub
- Any class in the JDK where you were previously using it without understanding it

---

## 🧪 Phase 12 — Design Patterns in Full Context

**Duration: 2 weeks** _Patterns are vocabulary for design conversations, not templates to apply._

> Note: Strategy and Observer have already been introduced in context (Phases 4 and 5). Here, you formalize and expand.

### Patterns to Master

- **Strategy** _(formalized)_ — the full pattern, common variants, and abuses
- **Observer** _(formalized)_ — event systems, `java.util.Observable` and why it was deprecated
- **Factory Method** — object creation without coupling to concrete types
- **Builder** — constructing complex objects with many optional parameters
- **Singleton** — with serious skepticism: when it is justified, when it is a crutch, and how to test around it
- **MVC deep dive** — architectural analysis of your own Phase 10 projects

### Anti-Pattern Study

For each pattern above, identify its corresponding anti-pattern and find an example in your own previous code.

---

## 🎓 Capstone — Library Management System (GUI)

**Duration: 3–4 weeks** _Portfolio-grade or it doesn't count._

### Hard Requirements

**Domain Model:**

- Rich, non-anemic objects with real behavior and enforced invariants
- `Optional` used correctly where nulls might otherwise appear
- Custom exceptions with meaningful messages for all failure modes

**Design:**

- Polymorphism where behavior varies by type (book vs periodical vs digital resource)
- Interfaces for extensibility (search strategies, notification systems)
- Composition over inheritance wherever possible

**Architecture:**

- Strict MVC — Model is fully testable without a running UI
- At least two design patterns, used because they solve a real problem

**Engineering:**

- Maven or Gradle project structure
- JUnit test suite with at least 30 tests covering happy paths, edge cases, and invariant violations
- Meaningful git history: feature branches, descriptive commits, no "fixed stuff" messages
- Javadoc on all public APIs

**Capstone Self-Review Questions:**

- Can someone run your tests with no UI and validate your business logic?
- Can you add a new resource type without modifying existing classes?
- Does your git history tell the story of how you thought, not just what you submitted?

---

## 🧠 Phase 13 — Professional Polish _(Ongoing Mastery)_

At this point, your code should feel intentional. This phase never ends.

### SOLID in Practice

Not as a definition exercise. Apply each principle as a review lens to your existing codebase:

- **S** — Does each class have exactly one reason to change?
- **O** — Can you add new behavior without editing existing code?
- **L** — Can subtypes be used everywhere their supertype is expected without surprises?
- **I** — Are your interfaces focused, or are they doing too much?
- **D** — Do your high-level modules depend on abstractions?

### Refactoring Patterns

- Extract Method, Extract Class, Move Method
- Replace Conditional with Polymorphism
- Introduce Parameter Object
- Replace Magic Number with Constant

### Package Architecture

- Package by feature, not by layer
- Understand what `package-private` visibility is for
- Design your package structure so that dependencies flow in one direction

### Code Smell Catalogue

Internalize these and spot them in your own work before anyone else does:

- Long Method, Large Class, Feature Envy
- Primitive Obsession, Data Clumps
- Switch Statements (often a polymorphism opportunity)
- Inappropriate Intimacy, Message Chains

### Test Quality Review

- Are your tests testing behavior or implementation details?
- Do your tests have their own design quality, or are they a mess?
- Can you understand what a test is verifying without reading the source class?

---

## Summary: What Was Added and Why

|Gap in Original|Fix Applied|Where|
|---|---|---|
|Testing introduced too late|Testing starts Phase 3, mindset from Phase 0|Phase 3|
|No git discipline|Git as a thinking tool from day one|Phase 0|
|Generics underweighted|Standalone generics phase before collections|Phase 6|
|Streams "with restraint" — vague|Concrete decision framework for streams vs loops|Phase 7|
|No null handling philosophy|Explicit null philosophy + Optional + Null Object|Phase 2|
|Design patterns too late|Strategy in Phase 4, Observer in Phase 5|Phases 4–5|
|No documentation fluency|Official Javadoc as a taught skill|Phase 1|
|Builder pattern missing|Added to Phase 12 patterns|Phase 12|

---

_A serious engineer finishes this outline with a portfolio that reflects how they think, not just what they can copy._
