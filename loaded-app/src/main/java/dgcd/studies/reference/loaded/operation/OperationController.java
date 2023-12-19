package dgcd.studies.reference.loaded.operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(
        value = "/api/operation",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public Operation create(@RequestBody OperationCreateRequest operationCreateRequest) {
        log.info("create operation request: {}", operationCreateRequest);
        return operationService.create(operationCreateRequest);
    }

    @GetMapping
    public Operation get() {
        log.info("get operation request");
        return operationService.get();
    }

}
