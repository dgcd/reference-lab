package dgcd.studies.reference.it.kafka;

import dgcd.studies.reference.it.api.OrderRequest;
import dgcd.studies.reference.it.testSetup.IntegrationTest;
import dgcd.studies.reference.it.testSetup.TestConsumerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Slf4j
@Timeout(5)
@IntegrationTest
class OrderKafkaConsumerTest {

    @Value("${app.kafka.orders-topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TestConsumerServiceImpl testConsumerService;

//    @BeforeAll
//    static void delay() throws InterruptedException {
//        Thread.sleep(10);
//    }


    @RepeatedTest(2)
    void test_kafka() throws ExecutionException, InterruptedException {
        testConsumerService.resetLatch();

        var orderRequest = prepareOrderRequest();
        log.info(">>> before test " + orderRequest.getDescription());

        kafkaTemplate
                .send(topic, "test_key", orderRequest.toByteArray())
                .get();

        testConsumerService.getLatch().await();
        Thread.sleep(1);
        log.info(">>> after test " + orderRequest.getDescription());
    }


    private static OrderRequest prepareOrderRequest() {
        return OrderRequest.newBuilder()
                .setId(123L)
                .setDescription(LocalDateTime.now().toString())
                .addItem(OrderRequest.Item.newBuilder()
                        .setTitle("test_item")
                        .setCount(2)
                        .build()
                )
                .build();
    }

}
