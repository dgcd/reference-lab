package dgcd.studies.reference.techinfo.autoconfigure;

import dgcd.studies.reference.techinfo.TechInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

@RequiredArgsConstructor
public class TechInfoContributor implements InfoContributor {

    private final TechInfoService techinfoService;


    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("techInfo", techinfoService.getTechinfoMap());
    }

}
