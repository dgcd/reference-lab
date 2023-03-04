package dgcd.studies.reference.it.testSetup;

import dgcd.studies.reference.it.api.OrderRequest;
import dgcd.studies.reference.it.service.ConsumerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestConsumerServiceImpl implements ConsumerService {

    private final ConsumerService consumerService;

    @Getter
    private volatile CountDownLatch latch = new CountDownLatch(1);

    @Override
    @SneakyThrows
    public void consume(OrderRequest orderRequest) {
        log.info("before service " + latch.getCount());
        consumerService.consume(orderRequest);
        Thread.sleep(1);
        latch.countDown();
        log.info("after service " + latch.getCount());
    }


    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}
