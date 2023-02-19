package dgcd.studies.reference.app.catfact;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!catfact")
public class NoOpCatfactService implements CatfactService {

    @Override
    public FactDto getFact() {
        return null;
    }

}
