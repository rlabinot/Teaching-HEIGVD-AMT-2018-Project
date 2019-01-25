package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.RulesApi;
import ch.heig.amt.gamification.api.model.Rule;
import ch.heig.amt.gamification.api.model.RuleNoId;
import ch.heig.amt.gamification.entities.ApplicationEntity;
import ch.heig.amt.gamification.entities.BadgeEntity;
import ch.heig.amt.gamification.entities.PointScaleEntity;
import ch.heig.amt.gamification.entities.RuleEntity;
import ch.heig.amt.gamification.repositories.ApplicationRepository;
import ch.heig.amt.gamification.repositories.BadgeRepository;
import ch.heig.amt.gamification.repositories.PointScaleRepository;
import ch.heig.amt.gamification.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Object> createRule(@ApiParam(value = "" ,required=true ) @RequestBody RuleNoId rule,
                                      @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Registration of the rule as an entity
        ApplicationEntity app = applicationRepository.findByApplicationName(apiKey);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        RuleEntity newRuleEntity = toRuleEntityNoId(rule);
        newRuleEntity.setApplication(app);
        ruleRepository.save(newRuleEntity);

        // Get the rule and build the response content of this rule from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRuleEntity.getRuleId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> deleteAllRules(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        for (RuleEntity ruleEntity : ruleRepository.findAllByApplicationApplicationName(apiKey)) {
            ruleRepository.delete(ruleEntity);
        }
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteRule(@ApiParam(value = "ID of the requested badge",required=true ) @PathVariable("id") Integer id,
                                    @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey){

        RuleEntity ruleEntity = ruleRepository.findByRuleIdAndApplicationApplicationName(id, apiKey);

        // Checking if existing badge
        if (ruleEntity == null) {
            return ResponseEntity.notFound().build();
        }

        // Registration of the rule as an entity
        ruleRepository.delete(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Object> editRule(@ApiParam(value = "rule with his new content" ,required=true ) @RequestBody Rule rule,
                                    @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Checking if existing badge
        RuleEntity ruleEntity = toRuleEntity(rule);
        if (ruleEntity == null) {
            return ResponseEntity.notFound().build();
        }
        ApplicationEntity app = applicationRepository.findByApplicationName(apiKey);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        ruleEntity.setApplication(app);

        // edit badge and send no content as respo
        ruleRepository.save(ruleEntity);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<Rule>> getAllRules(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        List<Rule> rules = new ArrayList<>();
        for (RuleEntity ruleEntity : ruleRepository.findAllByApplicationApplicationName(apiKey)) {
            rules.add(toRule(ruleEntity));
        }
        return ResponseEntity.ok(rules);
    }

    @Override
    public ResponseEntity<Rule> getRule(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                        @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {

        RuleEntity ruleEntity = ruleRepository.findByRuleIdAndApplicationApplicationName(id, apiKey);

        // Checking if existing badge
        if (ruleEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toRule(ruleEntity));
    }


    public RuleEntity toRuleEntityNoId(RuleNoId rule) {
        RuleEntity entity = new RuleEntity();
        entity.setRuleName(rule.getRuleName());
        entity.setEventTrigger(rule.getEventTrigger());
        if (rule.getBadgeId() != null) {
            BadgeEntity badge = badgeRepository.findOne(rule.getBadgeId());
            entity.setBadge(badge);
        } else {
            entity.setBadge(null);
        }
        if (rule.getPointScaleId() != null) {
            PointScaleEntity pointScale = pointScaleRepository.findOne(rule.getPointScaleId());
            entity.setPointScale(pointScale);
            entity.setAmount(rule.getAmount());
        } else {
            entity.setPointScale(null);
            entity.setAmount(null);
        }

        return entity;
    }

    public RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();
        entity.setRuleId(rule.getRuleId());
        entity.setRuleName(rule.getRuleName());
        entity.setEventTrigger(rule.getEventTrigger());
        if (rule.getBadgeId() != null) {
            BadgeEntity badge = badgeRepository.findOne(rule.getBadgeId());
            entity.setBadge(badge);
        } else {
            entity.setBadge(null);
        }
        if (rule.getPointScaleId() != null) {
            PointScaleEntity pointScale = pointScaleRepository.findOne(rule.getPointScaleId());
            entity.setPointScale(pointScale);
            entity.setAmount(rule.getAmount());
        } else {
            entity.setPointScale(null);
            entity.setAmount(null);
        }
        return entity;
    }

    private Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setRuleId(entity.getRuleId());
        rule.setRuleName(entity.getRuleName());
        rule.setEventTrigger(entity.getEventTrigger());
        if (entity.getPointScale() != null) {
            rule.setPointScaleId(entity.getPointScale().getPointScaleId());
            rule.setAmount(entity.getAmount());
        } else  {
            rule.setPointScaleId(null);
            rule.setAmount(null);
        }
        if (entity.getBadge() != null) {
            rule.setBadgeId(entity.getBadge().getBadgeId());
        } else {
            rule.setBadgeId(null);
        }


        return rule;
    }
}
