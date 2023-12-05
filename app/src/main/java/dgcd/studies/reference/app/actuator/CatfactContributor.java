package dgcd.studies.reference.app.actuator;

import dgcd.studies.reference.app.catfact.CatfactService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class CatfactContributor implements InfoContributor {

    private final CatfactService catfactService;


    @Override
    public void contribute(Info.Builder builder) {
        var factDto = catfactService.getFact();
        if (nonNull(factDto)) {
            var props = new LinkedHashMap<>();
            props.put("fact", factDto.fact());
            props.put("length", factDto.length());
            builder.withDetail("catFact", props);
        }
    }

}
