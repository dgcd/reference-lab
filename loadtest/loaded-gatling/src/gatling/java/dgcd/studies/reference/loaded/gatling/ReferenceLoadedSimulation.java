package dgcd.studies.reference.loaded.gatling;

import io.gatling.core.body.InputStreamBody;
import io.gatling.core.body.RawFileBodies;
import io.gatling.core.body.RawFileBody;
import io.gatling.core.body.StringBody;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@SuppressWarnings("unused")
public class ReferenceLoadedSimulation extends Simulation {

    private final ChainBuilder getChain = CoreDsl
            .exec(http("Get")
                    .get("/operation")
                    .check(status().is(200)))
//            .pause(1)
            ;

//    private final ChainBuilder getChain2 = CoreDsl
//            .exec(http("Get")
//                    .get("/operation")
//                    .check(status().is(200)))
////            .pause(1)
//            ;

    private final ChainBuilder postChain = CoreDsl
            .exec(http("Create")
                    .post("/operation")
                    .body(StringBody.)
                    .check(status().is(200)))
//            .pause(1)
            ;


    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080/api")
            .acceptHeader("application/json")
//            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling");

    private final ScenarioBuilder operationsScenario = CoreDsl
            .scenario("Operations")
            .exec(getChain);

    {
        var populationBuilder = operationsScenario.injectOpen(
                CoreDsl.atOnceUsers(100)
//                CoreDsl.rampUsers(40).during(10)
        );

        this
                .setUp(populationBuilder)
                .protocols(httpProtocol);
    }

}
