package dgcd.studies.reference.app;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@SpringBootTest
@Tag("integration")
//@AutoConfigureMockMvc
//@Transactional
@ActiveProfiles("integrationTest")
@AutoConfigureEmbeddedDatabase(provider = ZONKY, type = POSTGRES)
public @interface IntegrationTest {
}
