package ch.heig.amt.gamification.api.spec.steps;

import ch.heig.amt.gamification.ApiException;
import ch.heig.amt.gamification.ApiResponse;
import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.PointScale;
import ch.heig.amt.gamification.api.dto.PointScaleNoId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heig.amt.gamification.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class PointScalesSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private PointScaleNoId pointScaleToInsert;
    private PointScale lastInsertedPointScale;
    private PointScale pointScaleWithId;
    private List<PointScale> lastInsertedPointScales = new ArrayList<>();
    private int lastInsertedId;

    public PointScalesSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    private int getIdFromLastInsertedId(String location){
        String[] url = location.split("/");
        String id =  url[url.length-1];
        id = id.substring(0,id.length()-1);
        return Integer.parseInt(id);
    }

    @Given("^there is a PointScales server$")
    public void there_is_a_PointScales_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a pointScale named \\\"([^\\\"]*)\\\"$")
    public void i_have_a_pointScale_payload(String arg1) throws Throwable {
        pointScaleToInsert = new ch.heig.amt.gamification.api.dto.PointScaleNoId();
        assertNotNull(pointScaleToInsert);
        pointScaleToInsert.setPointScaleName(arg1);
    }

    @When("^I POST it to the /pointScales endpoint$")
    public void i_POST_it_to_the_pointScales_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createPointScaleWithHttpInfo(pointScaleToInsert, environment.getAPPLICATION_NAME());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            lastInsertedId = getIdFromLastInsertedId(lastApiResponse.getHeaders().get("Location").toString());
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }

    }

    @Given("^I GET it from the /pointScales api with its id$")
    public void i_GET_it_from_pointScales_with_its_api_id() throws Throwable {
        try {
            lastApiResponse = api.getPointScaleWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
            lastInsertedPointScale = api.getPointScale(lastInsertedId, environment.getAPPLICATION_NAME());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I GET a fake pointScale from the /pointScales api$")
    public void i_GET_a_fake_pointScale_from_pointScales() throws Throwable {
        try {
            lastApiResponse = api.getPointScaleWithHttpInfo(Integer.MAX_VALUE, environment.getAPPLICATION_NAME());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I check my pointScale with the expected one : \\\"([^\\\"]*)\\\"$")
    public void i_check_my_pointScale_with_the_expected_one(String arg1) throws Throwable {
        assertEquals(arg1, lastInsertedPointScale.getPointScaleName());
    }

    @Then("^I GET all my pointScales$")
    public void i_GET_all_my_pointScales() throws Throwable {
        try {
            lastApiResponse = api.getAllPointScalesWithHttpInfo(environment.getAPPLICATION_NAME());
            lastInsertedPointScales = api.getAllPointScales(environment.getAPPLICATION_NAME());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I check if my pointScales are GET : \\\"([^\\\"]*)\\\" and \\\"([^\\\"]*)\\\"$")
    public void i_check_if_my_pointScales_are_GET(String arg1, String arg2){
        boolean first = false;
        boolean second = false;
        for (PointScale b : lastInsertedPointScales) {
            if(b.getPointScaleName().equals(arg1)){
                first = true;
            }
            if(b.getPointScaleName().equals(arg2)){
                second = true;
            }
        }
        assertTrue(first);
        assertTrue(second);
    }

    @When("^I PUT a new name to this pointScale as \\\"([^\\\"]*)\\\"$")
    public void i_PUT_a_new_name_to_a_pointScale(String arg1) throws Throwable {
        pointScaleWithId = new PointScale();
        pointScaleWithId.setPointScaleName(arg1);
        pointScaleWithId.setPointScaleId(lastInsertedId);
        lastApiResponse = api.editPointScaleWithHttpInfo(pointScaleWithId, environment.getAPPLICATION_NAME());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
    }

    @Then("^I receive a (\\d+) status code for the pointScales$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I DELETE a pointScale$")
    public void i_DELETE_a_pointScale() throws Throwable {
        try {
            lastApiResponse = api.deletePointScaleWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }
    @Given("^I have at least one pointScale in the database$")
    public void i_have_something_in_the_database() throws Throwable {
        try {
            lastInsertedPointScales = api.getAllPointScales(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getAllPointScalesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertNotNull(lastInsertedPointScales);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^There is no pointScales$")
    public void The_database_is_empty() throws Throwable {
        try {
            lastInsertedPointScales = api.getAllPointScales(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getAllPointScalesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertTrue(lastInsertedPointScales.isEmpty());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }
}
