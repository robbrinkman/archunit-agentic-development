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

## Seeing the rules fail

The `violations` profile adds code that takes the shortcuts the rules are meant to catch, one class per rule.
It lives in `src/violations/java` and `src/violations-test/java`, and is only compiled when the profile is active:

```shell
mvn verify -Pviolations
```

The build fails, and every rule reports what is wrong and why, for example:

```text
Architecture Violation [Priority: MEDIUM] - Rule 'no classes should access the system clock directly, because time should be retrieved using the injected java.time.Clock' was violated (1 times):
Method <com.jdriven.example.order.OrderReport.generatedAt()> calls method <java.time.Instant.now()> in (OrderReport.java:9)
```
