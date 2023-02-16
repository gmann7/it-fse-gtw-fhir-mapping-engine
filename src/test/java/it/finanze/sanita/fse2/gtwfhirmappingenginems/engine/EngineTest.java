package it.finanze.sanita.fse2.gtwfhirmappingenginems.engine;

import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static it.finanze.sanita.fse2.gtwfhirmappingenginems.base.CDA.LAB;
import static it.finanze.sanita.fse2.gtwfhirmappingenginems.config.Constants.Profile.TEST;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(TEST)
@TestInstance(PER_CLASS)
public class EngineTest extends AbstractEngineTest {

    @BeforeAll
    void setup() throws IOException {
        initDb();
        initEngine();
    }

    @Test
    void transform() {
        assertDoesNotThrow(() -> {
            Bundle bundle = engines.manager().transform(
                LAB.read(),
                LAB.engineId(),
                LAB.transformId()
            );
            assertNotNull(bundle);
        });
    }

    @AfterAll
    void teardown() {
        resetDb();
    }

}
