package dgcd.studies.reference.loaded;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Slf4j
@RestController
public class TestController {

    @GetMapping(value = "/", produces = TEXT_PLAIN_VALUE)
    public String ok() {
        return "OK";
    }

}
