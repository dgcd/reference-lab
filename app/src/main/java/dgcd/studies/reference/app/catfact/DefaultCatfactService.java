package dgcd.studies.reference.app.catfact;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Profile("catfact")
@RequiredArgsConstructor
public class DefaultCatfactService implements CatfactService {

    private final RestTemplate restTemplate;
    private final CatfactProperties catfactProperties;


    @Override
    public FactDto getFact() {
        if (!catfactProperties.isEnabled()) {
            log.debug("Cat fact service skipped");
            return null;
        }

        try {
            var responseEntity = restTemplate.getForEntity(catfactProperties.getUrl(), FactDto.class);
            var factDto = responseEntity.getBody();
            log.debug("Received cat fact: {}", factDto);
            return factDto;
        } catch (Exception e) {
            log.error("Exception while fetching cats fact: ", e);
            return null;
        }
    }

}
