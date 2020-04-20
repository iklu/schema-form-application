package com.ovdr.schemaform;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ovdr.schemaform");

        noClasses()
            .that()
            .resideInAnyPackage("com.ovdr.schemaform.service..")
            .or()
            .resideInAnyPackage("com.ovdr.schemaform.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ovdr.schemaform.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
