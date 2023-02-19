package dgcd.studies.reference.app.refresh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshScheduler {

    private final ApplicationEventPublisher applicationEventPublisher;


    @Scheduled(
            initialDelayString = "${app-refresh.initial-delay}",
            fixedDelayString = "${app-refresh.fixed-delay}"
    )
    public void publish() {
        var event = new RefreshEvent(this, "Refresh event", "Refresh scope");
        applicationEventPublisher.publishEvent(event);
    }

}
