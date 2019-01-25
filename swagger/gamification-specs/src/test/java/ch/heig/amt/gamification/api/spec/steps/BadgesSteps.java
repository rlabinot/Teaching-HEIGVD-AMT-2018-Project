package ch.heig.amt.gamification.api.spec.steps;

import ch.heig.amt.gamification.ApiException;
import ch.heig.amt.gamification.ApiResponse;
import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.Badge;
import ch.heig.amt.gamification.api.dto.BadgeNoId;
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
public class BadgesSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private BadgeNoId badgeToInsert;
    private Badge lastInsertedBadge;
    private Badge badgeWithId;
    private List<Badge> lastInsertedBadges = new ArrayList<>();
    private int lastInsertedId;

    public BadgesSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    private int getIdFromLastInsertedId(String location){
        String[] url = location.split("/");
        String id =  url[url.length-1];
        id = id.substring(0,id.length()-1);
        return Integer.parseInt(id);
    }

    @Given("^there is a Badges server$")
    public void there_is_a_Badges_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a badge named \\\"([^\\\"]*)\\\"$")
    public void i_have_a_badge_payload(String arg1) throws Throwable {
        badgeToInsert = new ch.heig.amt.gamification.api.dto.BadgeNoId();
        assertNotNull(badgeToInsert);
        badgeToInsert.setBadgeName(arg1);
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(badgeToInsert, environment.getAPPLICATION_NAME());
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

    @Given("^I GET it from the /badges api with its id$")
    public void i_GET_it_from_badges_with_its_api_id() throws Throwable {
        try {
            lastApiResponse = api.getBadgeWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
            lastInsertedBadge = api.getBadge(lastInsertedId, environment.getAPPLICATION_NAME());
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

    @Given("^I GET a fake badge from the /badges api$")
    public void i_GET_a_fake_badge_from_badges() throws Throwable {
        try {
            lastApiResponse = api.getBadgeWithHttpInfo(Integer.MAX_VALUE, environment.getAPPLICATION_NAME());
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

    @Then("^I check my badge with the expected one : \\\"([^\\\"]*)\\\"$")
    public void i_check_my_badge_with_the_expected_one(String arg1) throws Throwable {
        assertEquals(arg1, lastInsertedBadge.getBadgeName());
    }

    @Then("^I GET all my badges$")
    public void i_GET_all_my_badges() throws Throwable {
        try {
            lastApiResponse = api.getBadgesWithHttpInfo(environment.getAPPLICATION_NAME());
            lastInsertedBadges = api.getBadges(environment.getAPPLICATION_NAME());
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

    @Then("^I check if my badges are GET : \\\"([^\\\"]*)\\\" and \\\"([^\\\"]*)\\\"$")
    public void i_check_if_my_badges_are_GET(String arg1, String arg2){
        boolean first = false;
        boolean second = false;
        for (Badge b : lastInsertedBadges) {
            if(b.getBadgeName().equals(arg1)){
                first = true;
            }
            if(b.getBadgeName().equals(arg2)){
                second = true;
            }
        }
        assertTrue(first);
        assertTrue(second);
    }

    @When("^I PUT a new name to this badge as \\\"([^\\\"]*)\\\"$")
    public void i_PUT_a_new_name_to_a_badge(String arg1) throws Throwable {
        badgeWithId = new Badge();
        badgeToInsert.setBadgeName(arg1);
        lastApiResponse = api.editBadgeWithHttpInfo(badgeWithId, environment.getAPPLICATION_NAME());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I DELETE it")
    public void i_DELETE_it() throws Throwable {
        try {
            lastApiResponse = api.deleteBadgeWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
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
    @Given("^I have something in the database$")
    public void i_have_something_in_the_database() throws Throwable {
        try {
            lastInsertedBadges = api.getBadges(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getBadgesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertNotNull(lastInsertedBadges);
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

    @When("^I DELETE everything$")
    public void i_DELETE_everything() throws Throwable {
        try {
            lastApiResponse = api.deleteAllBadgesWithHttpInfo(environment.getAPPLICATION_NAME());
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
    @Then("^The database is empty$")
    public void The_database_is_empty() throws Throwable {
        try {
            lastInsertedBadges = api.getBadges(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getBadgesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertTrue(lastInsertedBadges.isEmpty());
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
