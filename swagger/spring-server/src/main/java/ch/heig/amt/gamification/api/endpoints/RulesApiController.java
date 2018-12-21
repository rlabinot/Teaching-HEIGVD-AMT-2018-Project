package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.RulesApi;
import ch.heig.amt.gamification.api.model.Rule;
import ch.heig.amt.gamification.entities.RuleEntity;
import ch.heig.amt.gamification.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;


    @Override
    public ResponseEntity<Object> createRule(Rule rule) {
        RuleEntity newRuleEntity = toRuleEntity(rule);
        ruleRepository.save(newRuleEntity);
        String id = newRuleEntity.getRuleName();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRuleEntity.getRuleName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Rule>> getRules() {
        List<Rule> rules = new ArrayList<>();
        for (RuleEntity ruleEntity : ruleRepository.findAll()) {
            rules.add(toRule(ruleEntity));
        }
        return ResponseEntity.ok(rules);
    }

    public RuleEntity toRuleEntity(Rule pointScale) {
        RuleEntity entity = new RuleEntity();
        entity.setRuleName(pointScale.getRuleName());
        return entity;
    }

    private Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setRuleName(entity.getRuleName());
        return rule;
    }
}
