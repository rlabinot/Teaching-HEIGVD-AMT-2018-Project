package ch.heig.amt.gamification.api.spec.steps;

import ch.heig.amt.gamification.ApiException;
import ch.heig.amt.gamification.ApiResponse;
import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.Rule;
import ch.heig.amt.gamification.api.dto.RuleNoId;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heig.amt.gamification.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class RulesSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private RuleNoId ruleToInsert;
    private Rule lastInsertedRule;
    private Rule ruleWithId;
    private List<Rule> lastInsertedRules = new ArrayList<>();
    private int lastInsertedId;

    public RulesSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    private int getIdFromLastInsertedId(String location){
        String[] url = location.split("/");
        String id =  url[url.length-1];
        id = id.substring(0,id.length()-1);
        return Integer.parseInt(id);
    }
    
    @Given("^there is a Rules server$")
    public void there_is_a_Rules_server() throws Throwable {
        assertNotNull(api);
    }
    @Given("^I have a rule named \"([^\"]*)\" where the trigger is \"([^\"]*)\" and the amount is (\\d+) the badge id is (\\d+) the pointScale is (\\d+)$")
    public void i_have_a_rule_named_where_the_trigger_is_and_the_amount_is_the_badge_id_is_the_pointScale_is(String arg1, String arg2, int arg3, int arg4, int arg5) throws Throwable {
        ruleToInsert = new ch.heig.amt.gamification.api.dto.RuleNoId();
        assertNotNull(ruleToInsert);
        ruleToInsert.setRuleName(arg1);
        ruleToInsert.setEventTrigger(arg2);
        ruleToInsert.setAmount(arg3);
        ruleToInsert.setBadgeId(arg4);
        ruleToInsert.setPointScaleId(arg5);
    }

    @When("^I POST it to the /rules endpoint$")
    public void i_POST_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createRuleWithHttpInfo(ruleToInsert, environment.getAPPLICATION_NAME());
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

    @Then("^I receive a (\\d+) status code for the rules$")
    public void i_receive_a_status_code_for_the_rules(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I GET it from the /rules api with its id$")
    public void i_GET_it_from_the_rules_api_with_its_id() throws Throwable {
        try {
            lastApiResponse = api.getRuleWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
            lastInsertedRule = api.getRule(lastInsertedId, environment.getAPPLICATION_NAME());
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


    @When("^I GET a fake rule from the /rules api$")
    public void i_GET_a_fake_rule_from_the_rules_api() throws Throwable {
        try {
            lastApiResponse = api.getRuleWithHttpInfo(Integer.MAX_VALUE, environment.getAPPLICATION_NAME());
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

    @Then("^I GET all my rules$")
    public void i_GET_all_my_rules() throws Throwable {
        try {
            lastApiResponse = api.getAllRulesWithHttpInfo(environment.getAPPLICATION_NAME());
            lastInsertedRules = api.getAllRules(environment.getAPPLICATION_NAME());
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

    @Then("^I check if my rules are GET : \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_check_if_my_rules_are_GET_and(String arg1, String arg2) throws Throwable {
        boolean first = false;
        boolean second = false;
        for (Rule b : lastInsertedRules) {
            if(b.getRuleName().equals(arg1)){
                first = true;
            }
            if(b.getRuleName().equals(arg2)){
                second = true;
            }
        }
        assertTrue(first);
        assertTrue(second);
    }

    @When("^I PUT a new name to this rule as \"([^\"]*)\"$")
    public void i_PUT_a_new_name_to_this_rule_as(String arg1) throws Throwable {
        ruleWithId = new Rule();
        ruleWithId.setRuleName(arg1);
        ruleWithId.setRuleId(lastInsertedId);
        lastApiResponse = api.editRuleWithHttpInfo(ruleWithId, environment.getAPPLICATION_NAME());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
    }

    @Then("^I check my rule with the expected one : \"([^\"]*)\"$")
    public void i_check_my_rule_with_the_expected_one(String arg1) throws Throwable {
        assertEquals(arg1, lastInsertedRule.getRuleName());
    }


    @When("^I DELETE a rule$")
    public void i_DELETE_a_rule() throws Throwable {
        try {
            lastApiResponse = api.deleteRuleWithHttpInfo(lastInsertedId, environment.getAPPLICATION_NAME());
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

    @When("^I DELETE all the rules$")
    public void iDELETEAllTheRules() throws Throwable {
        try {
            lastApiResponse = api.deleteAllRulesWithHttpInfo(environment.getAPPLICATION_NAME());
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

    @Given("^I have at least one rule in the database$")
    public void iHaveAtLeastOneRuleInTheDatabase() throws Throwable {
        try {
            lastInsertedRules = api.getAllRules(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getBadgesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertNotNull(lastInsertedRules);
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

    @Then("^There is no rules$")
    public void thereIsNoRules() throws Throwable {
        try {
            lastInsertedRules = api.getAllRules(environment.getAPPLICATION_NAME());
            lastApiResponse = api.getAllRulesWithHttpInfo(environment.getAPPLICATION_NAME());
            assertTrue(lastInsertedRules.isEmpty());
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
