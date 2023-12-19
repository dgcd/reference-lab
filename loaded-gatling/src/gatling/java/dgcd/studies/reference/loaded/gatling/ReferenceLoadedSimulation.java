package dgcd.studies.reference.loaded.gatling;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@SuppressWarnings("unused")
public class ReferenceLoadedSimulation extends Simulation {

    ChainBuilder operation = CoreDsl
            .exec(http("Get")
                    .get("/operation")
                    .check(status().is(200)))
//            .pause(1)
            ;


    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080/api")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
//            .userAgentHeader("Gatling")
            ;

    ScenarioBuilder users = CoreDsl.scenario("Users").exec(operation);

    {
        PopulationBuilder populationBuilder = users.injectOpen(CoreDsl.rampUsers(40).during(10));

        this
                .setUp(populationBuilder)
                .protocols(httpProtocol);
    }

}
