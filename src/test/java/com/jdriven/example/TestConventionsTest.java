package com.jdriven.example;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Disabled;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

// 8. Don't disable failing tests
@AnalyzeClasses(packages = "com.jdriven.example", importOptions = ImportOption.OnlyIncludeTests.class)
class TestConventionsTest {

    @ArchTest
    static final ArchRule no_disabled_tests = noMethods()
        .should().beAnnotatedWith(Disabled.class)
        .because("fix the test or the code, don't disable the test");

    @ArchTest
    static final ArchRule no_disabled_test_classes = noClasses()
        .should().beAnnotatedWith(Disabled.class)
        .because("fix the tests or the code, don't disable the test class");
}
