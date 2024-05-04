package dgcd.studies.reference.loaded.operation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.RoundingMode.HALF_UP;

@Slf4j
@Service
public class OperationService {

    @SneakyThrows
    public Operation create(OperationCreateRequest rq) {
        var operation = new Operation(
                UUID.randomUUID().toString(),
                rq.description(),
                rq.amount()
        );
        log.info("create started:  {}", operation);
        Thread.sleep(100);
        log.info("create finished: {}", operation);
        return operation;
    }


    @SneakyThrows
    public Operation get() {
        var id = UUID.randomUUID().toString();
        var description = id.replace("-", "");
        var amount = BigDecimal.valueOf((long) (100000 * Math.random()))
                .setScale(2, HALF_UP)
                .divide(BigDecimal.valueOf(100), HALF_UP);
        var operation = new Operation(id, description, amount);

        log.info("get started:  {}", operation);
        Thread.sleep(50 +(long) (100 * Math.random()) );
        log.info("get finished: {}", operation);
        return operation;
    }

}
