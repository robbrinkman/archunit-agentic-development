package com.jdriven.example;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.library.GeneralCodingRules;
import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.tngtech.archunit.base.DescribedPredicate.describe;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.jdriven.example", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    // 1. No deprecated APIs
    @ArchTest
    static final ArchRule no_deprecated_api = GeneralCodingRules.DEPRECATED_API_SHOULD_NOT_BE_USED;

    // 2. No forbidden libraries or APIs
    @ArchTest
    static final ArchRule no_forbidden_apis = noClasses()
        .should().dependOnClassesThat()
        .belongToAnyOf(java.util.Date.class, java.util.Calendar.class)
        .orShould().dependOnClassesThat()
        .resideInAnyPackage("org.apache.http..", "org.json..")
        .because("we use java.time, the Spring RestClient and Jackson");

    // 3. Use the abstractions we introduced
    @ArchTest
    static final ArchRule no_direct_system_clock_access = noClasses()
        .should().accessTargetWhere(describe("access the system clock directly", access -> Set.of(
            "java.time.Instant.now()",
            "java.time.LocalDateTime.now()",
            "java.lang.System.currentTimeMillis()"
        ).contains(access.getTarget().getFullName())))
        .as("no classes should access the system clock directly")
        .because("time should be retrieved using the injected java.time.Clock");

    // 4. Keep the chosen HTTP APIs in the clients
    @ArchTest
    static final ArchRule http_apis_only_in_clients = noClasses()
        .that().resideOutsideOfPackage("..client..")
        .should().dependOnClassesThat()
        .resideInAnyPackage(
            "java.net.http..",
            "org.springframework.web.client..",
            "okhttp3..")
        .because("external systems are accessed through the clients in the client package");

    // 5. Don't bypass security
    @ArchTest
    static final ArchRule jwt_libraries_only_in_security = noClasses()
        .that().resideOutsideOfPackage("..security..")
        .should().dependOnClassesThat()
        .resideInAnyPackage("io.jsonwebtoken..", "com.auth0.jwt..", "com.nimbusds..")
        .because("authentication is handled by the security package");

    // 6. Controllers don't return entities
    @ArchTest
    static final ArchRule controllers_do_not_return_entities = methods()
        .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
        .should(new ArchCondition<JavaMethod>("have no entities in their return type") {
            @Override
            public void check(JavaMethod method, ConditionEvents events) {
                boolean returnsEntity = method.getReturnType().getAllInvolvedRawTypes().stream()
                    .anyMatch(returnType -> returnType.isAnnotatedWith(Entity.class));
                events.add(new SimpleConditionEvent(method, !returnsEntity,
                    method.getDescription() + " has an entity in its return type"));
            }
        })
        .because("controllers return DTOs, not entities");

    // 7. Keep the listed concurrency APIs in the async package
    @ArchTest
    static final ArchRule concurrency_only_in_async = noClasses()
        .that().resideOutsideOfPackage("..async..")
        .should().dependOnClassesThat()
        .belongToAnyOf(
            CompletableFuture.class,
            ExecutorService.class,
            Executors.class,
            Thread.class)
        .because("concurrency is handled in the async package");
}
