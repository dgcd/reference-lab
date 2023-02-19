package dgcd.studies.reference.app.refresh;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("refresh")
public class RefreshProperties {

    private String property1;
    private String property2;
    private String property3;
    private String property4;

}
