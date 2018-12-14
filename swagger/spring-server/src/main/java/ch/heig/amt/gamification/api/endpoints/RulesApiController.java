package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.RulesApi;
import ch.heig.amt.gamification.api.model.PointScale;
import ch.heig.amt.gamification.api.model.Rule;
import ch.heig.amt.gamification.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;


    @Override
    public ResponseEntity<Object> createRule(Rule rule) {
        return null;
    }

    @Override
    public ResponseEntity<List<PointScale>> getRules() {
        return null;
    }
}
