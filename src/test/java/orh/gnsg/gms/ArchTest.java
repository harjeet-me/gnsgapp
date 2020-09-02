package orh.gnsg.gms;

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
            .importPackages("orh.gnsg.gms");

        noClasses()
            .that()
            .resideInAnyPackage("orh.gnsg.gms.service..")
            .or()
            .resideInAnyPackage("orh.gnsg.gms.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..orh.gnsg.gms.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
