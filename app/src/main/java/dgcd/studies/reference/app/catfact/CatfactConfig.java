package dgcd.studies.reference.app.catfact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Profile("catfact")
@Configuration
public class CatfactConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
