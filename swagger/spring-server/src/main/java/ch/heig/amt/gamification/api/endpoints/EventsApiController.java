package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.EventsApi;
import ch.heig.amt.gamification.api.model.Event;
import ch.heig.amt.gamification.entities.*;
import ch.heig.amt.gamification.repositories.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Override
    public ResponseEntity<Object> sendEvent(@ApiParam(value = "" ,required=true ) @RequestBody Event event,
                                     @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Transform the event to an entity and save it for future state rules
        EventEntity newEventEntity = toEventEntity(event);

        // Get the application that send this event
        ApplicationEntity application = applicationRepository.findByApplicationName(apiKey);
        if (application == null) {
            return null;
        }

        // Get the user which sends the event (if he doesn't exist => create it)
        UserEntity user = newEventEntity.getUser();
        if (userRepository.findByUserIdAndAndApplication_ApplicationName(user.getUserId(), apiKey) == null) {
            user.setApplication(application);
            System.out.println("User " + user.getUserId() + "created");
        }

        // Get all the rules from the app and which are triggered by this event type
        List<RuleEntity> rules = ruleRepository.findAllByEventTriggerAndApplication_ApplicationName(event.getEventType(), apiKey);
        if (rules.isEmpty()) {
            return null;
        }


        for (RuleEntity rule : rules) {
            // Get the badges from the rules
            BadgeEntity badge = rule.getBadge();
            if (badge != null) {
                BadgeRewardEntity badgeReward = new BadgeRewardEntity();
                badgeReward.setUser(user);
                badgeReward.setBadge(badge);
                badgeRewardRepository.save(badgeReward);
                System.out.println("User + " + user.getUserId() + " win " + badge.getBadgeName() + "badge.");
            }

            // Get the pointscales from the rules
            PointScaleEntity pointScale = rule.getPointScale();
            if (pointScale != null) {
                PointScaleRewardEntity pointScaleReward = new PointScaleRewardEntity();
                pointScaleReward.setUser(user);
                pointScaleReward.setPointScale(pointScale);
                pointScaleReward
            }
        }

        // Get the pointscales from the rules








        // Get the event and build the response content of this event from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEventEntity.getEventType()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = new ArrayList<>();
        for (EventEntity eventEntity : eventRepository.findAll()) {
            events.add(toEvent(eventEntity));
        }
        return ResponseEntity.ok(events);
    }

    public EventEntity toEventEntity(Event event) {
            EventEntity entity = new EventEntity();
            entity.setEventType(event.getEventType());
        return entity;
    }

    private Event toEvent(EventEntity entity) {
        Event event = new Event();
        event.setEventType(entity.getEventType());
        //event.setEventProperties(entity.getEventProperties());
        //event.setUserId(entity.getUserId());
        return event;
    }
}
