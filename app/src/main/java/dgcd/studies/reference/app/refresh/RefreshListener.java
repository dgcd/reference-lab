package dgcd.studies.reference.app.refresh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshListener {

    private final Environment env;

    @EventListener
    public void listener(EnvironmentChangeEvent event) {
        log.debug("Properties changed '{}'", event.getKeys());
        for (var key : event.getKeys()) {
            log.info("Property changed '{}', new value: '{}'", key, env.getProperty(key));
        }
    }

}
