package dgcd.studies.reference.it.service;

import dgcd.studies.reference.it.api.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public void consume(OrderRequest orderRequest) {
        log.info("Consumed -> orderRequest: \n{}", orderRequest);
    }

}
