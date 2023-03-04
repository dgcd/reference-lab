package dgcd.studies.reference.it.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import dgcd.studies.reference.it.api.OrderRequest;
import dgcd.studies.reference.it.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final ConsumerService consumerService;


    @KafkaListener(topics = "${app.kafka.orders-topic}")
    public void consume(
            @Payload byte[] message,
            @Header(KafkaHeaders.OFFSET) Long offset,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
            Acknowledgment acknowledgment
    ) {
        try {
            OrderRequest orderRequest = OrderRequest.parseFrom(message);
            logConsumerMessage(offset, key, partition, topic, ts);
            consumerService.consume(orderRequest);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        acknowledgment.acknowledge();
    }


    public static void logConsumerMessage(
            Long offset,
            String key,
            Integer partition,
            String topic,
            Long ts
    ) {
        log.info(
                "Consumed -> topic: {}, key: {}, partition: {}, offset: {}, timestamp: {}",
                topic,
                key,
                partition,
                offset,
                ts
        );

    }

}
