package dgcd.studies.reference.loaded;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class ApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void checkPostgresVersion() {
        var version = jdbcTemplate.queryForObject("select version();", String.class);

        assertThat(version).isNotNull();
        assertThat(version).contains("PostgreSQL 16.4");
    }

}
