package dgcd.studies.reference.app.actuator;

import dgcd.studies.reference.app.refresh.RefreshProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

import static dgcd.studies.reference.app.Utils.logWithPrefix;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertiesContributor implements InfoContributor {

    private final Environment env;
    private final RefreshProperties refreshProperties;


    @Value("${app.static-property:}")
    private String staticProperty;

    @Value("${refresh.property1:}")
    private String refreshProperty1;


    @Override
    public void contribute(Info.Builder builder) {
        logWithPrefix(log, "props");

        var props = new LinkedHashMap<>();
        props.put("static property", staticProperty);
        props.put("static env property", env.getProperty("app.static-env-property"));
        props.put("refreshProperty1", refreshProperty1);
        props.put("refreshProperty2", env.getProperty("refresh.property2"));
        props.put("refreshProperty3", refreshProperties.getProperty3());
        props.put("refreshProperty4", refreshProperties.getProperty4());

        builder.withDetail("propertiesInfo", props);
    }

}
