package dgcd.studies.reference.it.testSetup;

import dgcd.studies.reference.it.service.ConsumerService;
import dgcd.studies.reference.it.service.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class IntegrationTestConfig {

    @Bean
    @Primary
    public ConsumerService testConsumerService(@Qualifier("default") ConsumerService consumerService) {
        return new TestConsumerServiceImpl(consumerService);
    }

    @Bean
    @Qualifier("default")
    public ConsumerService consumerService() {
        return new ConsumerServiceImpl();
    }

}
