package dgcd.studies.reference.loaded.operation;

import java.math.BigDecimal;

public record OperationCreateRequest(
        String description,
        BigDecimal amount
) {
}
