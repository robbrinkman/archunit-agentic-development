# ArchUnit in the Age of Agentic Development

Example project for the JDriven blog post
[ArchUnit in the Age of Agentic Development](https://jdriven.com/blog/2026/10/ArchUnit-in-the-Age-of-Agentic-Development).

It uses ArchUnit 1.5.1 with JUnit 6 and contains all ArchUnit rules from the post, together with a small code base that complies with them:

- `src/test/java/com/jdriven/example/ArchitectureTest.java` – rules 1 to 7, checked against the production code
- `src/test/java/com/jdriven/example/TestConventionsTest.java` – rule 8, checked against the test code

## Running the tests

Requires Java 21 or newer.

```shell
mvn verify
```

## Seeing a violation

Replace `Instant.now(clock)` with `Instant.now()` in `OrderService` and run `mvn verify` again:

```text
Architecture Violation [Priority: MEDIUM] - Rule 'no classes should access the system clock directly, because time should be retrieved using the injected java.time.Clock' was violated (1 times):
Method <com.jdriven.example.order.OrderService.complete(java.lang.String)> calls method <java.time.Instant.now()> in (OrderService.java:20)
```
