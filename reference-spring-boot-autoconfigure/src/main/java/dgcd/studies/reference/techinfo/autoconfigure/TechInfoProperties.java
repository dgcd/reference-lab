package dgcd.studies.reference.techinfo.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(
        prefix = "dgcd.studies.reference.techinfo",
        ignoreUnknownFields = false
)
public class TechInfoProperties {

    private boolean enabled = true;

    private String customParam;

}
