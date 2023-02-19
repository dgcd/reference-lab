package dgcd.studies.reference.app.actuator;

import org.springframework.boot.actuate.info.OsInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContributorsConfig {

    @Bean
    public OsInfoContributor osInfoContributor() {
        return new OsInfoContributor();
    }

}
