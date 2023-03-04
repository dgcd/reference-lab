package dgcd.studies.reference.it.kafka;

import dgcd.studies.reference.it.api.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
class OrderKafkaProducer {

    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Value("${app.kafka.orders-topic}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedRateString = "${app.kafka.producer.rate}")
    public void send() throws ExecutionException, InterruptedException {
        var orderRequest = prepareOrderRequest();
        var key = String.valueOf(orderRequest.getId());

        var future = kafkaTemplate.send(topic, key, orderRequest.toByteArray());

        var result = future.get();
        logProducerMessage(key, result.getRecordMetadata());
    }


    private static OrderRequest prepareOrderRequest() {
        return OrderRequest.newBuilder()
                .setId(idGenerator.getAndIncrement())
                .setDescription(UUID.randomUUID().toString())
                .addItem(OrderRequest.Item.newBuilder()
                        .setTitle("dummy")
                        .setCount(42)
                        .build()
                )
                .addItem(OrderRequest.Item.newBuilder()
                        .setTitle("other")
                        .setCount(666)
                        .build()
                )
                .build();
    }


    static void logProducerMessage(
            String key,
            RecordMetadata result
    ) {
        log.info(
                "Produced -> topic: {}, key: {}, partition: {}, offset: {}, value size: {}",
                result.topic(),
                key,
                result.partition(),
                result.offset(),
                result.serializedValueSize()
        );
    }

}
