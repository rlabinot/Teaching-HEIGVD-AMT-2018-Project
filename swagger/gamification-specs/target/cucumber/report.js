$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("creation.feature");
formatter.feature({
  "line": 1,
  "name": "Creation of fruits",
  "description": "",
  "id": "creation-of-fruits",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "there is a Fruits server",
  "keyword": "Given "
});
formatter.match({
  "location": "CreationSteps.there_is_a_Fruits_server()"
});
formatter.result({
  "duration": 217819413,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "create a fruit",
  "description": "",
  "id": "creation-of-fruits;create-a-fruit",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I have a fruit payload",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I POST it to the /fruits endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I receive a 201 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "CreationSteps.i_have_a_fruit_payload()"
});
formatter.result({
  "duration": 122894,
  "status": "passed"
});
formatter.match({
  "location": "CreationSteps.i_POST_it_to_the_fruits_endpoint()"
});
formatter.result({
  "duration": 13868118,
  "error_message": "java.lang.IllegalArgumentException: unexpected url: null/fruits\r\n\tat com.squareup.okhttp.Request$Builder.url(Request.java:163)\r\n\tat ch.heig.amt.gamification.ApiClient.buildCall(ApiClient.java:1080)\r\n\tat ch.heig.amt.gamification.api.DefaultApi.createFruitCall(DefaultApi.java:112)\r\n\tat ch.heig.amt.gamification.api.DefaultApi.createFruitWithHttpInfo(DefaultApi.java:135)\r\n\tat ch.heig.amt.gamification.api.spec.steps.CreationSteps.i_POST_it_to_the_fruits_endpoint(CreationSteps.java:48)\r\n\tat ✽.When I POST it to the /fruits endpoint(creation.feature:8)\r\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 12
    }
  ],
  "location": "CreationSteps.i_receive_a_status_code(int)"
});
formatter.result({
  "status": "skipped"
});
});