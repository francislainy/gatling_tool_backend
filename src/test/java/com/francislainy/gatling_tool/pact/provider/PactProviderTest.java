package com.francislainy.gatling_tool.pact.provider;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
//import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.http.HttpRequest;

import static Util.Util.logCurlFromPact;
import static com.francislainy.gatling_tool.config.Constants.*;

//@PactFolder("target/pacts")

@Provider(PACT_PROVIDER)
@PactBroker(host = BROKER_PACT_URL, authentication = @PactBrokerAuth(token = BROKER_TOKEN), consumers = {PACT_CONSUMER})
@VerificationReports(value = {"markdown"}, reportDir = "target/pacts/myreports")
@Consumer(PACT_CONSUMER)
public class PactProviderTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactTestTemplate(PactVerificationContext context, HttpRequest request) {

        logCurlFromPact(context, request);

        context.verifyInteraction();
    }


    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget(BASE_PACT_URL, 8081, "/api/gatling-tool"));

    }


    @State("a request for all categories")
    void sampleState() {
    }

    @State("a request for a single category")
    void sampleState1() {
    }

}