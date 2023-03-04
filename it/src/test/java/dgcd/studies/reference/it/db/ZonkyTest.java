package dgcd.studies.reference.it.db;

import dgcd.studies.reference.it.testSetup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ZonkyTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void checkPostgresVersion() {
        var version = jdbcTemplate.queryForObject("select version();", String.class);

        assertThat(version).isNotNull();
        assertThat(version).contains("PostgreSQL 15.2");
    }

}
