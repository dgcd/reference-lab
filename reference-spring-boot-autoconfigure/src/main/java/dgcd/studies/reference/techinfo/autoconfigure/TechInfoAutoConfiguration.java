package dgcd.studies.reference.techinfo.autoconfigure;

import dgcd.studies.reference.techinfo.TechInfoService;
import dgcd.studies.reference.techinfo.TechInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.HashMap;

import static org.springframework.util.StringUtils.hasText;

@AutoConfiguration
@ConditionalOnProperty(
        prefix = "dgcd.studies.reference.techinfo",
        name = "enabled",
        matchIfMissing = true
)
@EnableConfigurationProperties(TechInfoProperties.class)
@RequiredArgsConstructor
public class TechInfoAutoConfiguration {

    private final TechInfoProperties techinfoProperties;


    @Bean
    @ConditionalOnMissingBean(TechInfoService.class)
    public InfoContributor techinfoService(
            @Value("${spring.application.name:}") String appName,
            Environment env
    ) {
        var map = prepareAdditionalParams(appName, env);
        var srv = new TechInfoServiceImpl(map);
        return new TechInfoContributor(srv);
    }


    private HashMap<String, String> prepareAdditionalParams(String appName, Environment env) {
        var map = new HashMap<String, String>();

        if (hasText(appName)) {
            map.put("appName", appName);
        }

        map.put("springProfiles", Arrays.toString(env.getActiveProfiles()));
        map.put("springBootVersion", SpringBootVersion.getVersion());

        if (hasText(techinfoProperties.getCustomParam())) {
            map.put("customParam", techinfoProperties.getCustomParam());
        }
        return map;
    }

}
