package dgcd.studies.reference.app.catfact;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Profile("catfact")
@ConfigurationProperties("app.catfact")
public class CatfactProperties {

    private String url;
    private boolean enabled;

}
