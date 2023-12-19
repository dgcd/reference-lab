package dgcd.studies.reference.loaded.operation;

import java.math.BigDecimal;

public record Operation(
        String id,
        String description,
        BigDecimal amount
) {
}
