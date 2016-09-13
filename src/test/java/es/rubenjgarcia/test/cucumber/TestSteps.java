package es.rubenjgarcia.test.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import es.rubenjgarcia.Application;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class TestSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<HashMap> response;

    @Given("I call GET on (.*)")
    public void iCallGetOn(String url) {
        this.response = this.restTemplate.getForEntity(url, HashMap.class, new HashMap<>());
    }

    @Then("^the response status is (\\d+)$")
    public void theResponseStatusIs(int status) throws Throwable {
        Assert.assertEquals(status, response.getStatusCode().value());
    }

    @And("^the response body must contain (.*) with (.*)$")
    public void theResponseBodyMustContainFieldWithValue(String field, String value) throws Throwable {
        Assert.assertTrue(response.getBody().containsKey(field));
        Assert.assertEquals(value, response.getBody().get(field));
    }
}
