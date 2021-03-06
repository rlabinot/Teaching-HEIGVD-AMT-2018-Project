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

    @Autowired
    PointScaleRewardRepository pointScaleRewardRepository;

    @Override
    public ResponseEntity<Object> sendEvent(@ApiParam(value = "" ,required=true ) @RequestBody Event event,
                                     @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Transform the event to an entity and save it for future state rules
        EventEntity newEvent = toEventEntity(event);

        // Get the application that send this event
        ApplicationEntity application = applicationRepository.findByApplicationName(apiKey);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the user which sends the event (if he doesn't exist => create it)
        UserEntity user = newEvent.getUser();
        if (userRepository.findByUserIdAndApplicationApplicationName(user.getUserId(), apiKey) == null) {
            user.setApplication(application);
            userRepository.save(user);
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
                badgeReward.setApplication(application);
                badgeReward.setTimestamp(System.currentTimeMillis());
                badgeRewardRepository.save(badgeReward);
                System.out.println("User + " + user.getUserId() + " win " + badge.getBadgeName() + "badge.");
            }

            // Get the pointscales from the rules
            PointScaleEntity pointScale = rule.getPointScale();
            if (pointScale != null) {
                PointScaleRewardEntity pointScaleReward = pointScaleRewardRepository.findPointScaleRewardEntityByPointScalePointScaleNameAndUserUserId(pointScale.getPointScaleName(), user.getUserId());
                if (pointScaleReward == null) {
                    pointScaleReward = new PointScaleRewardEntity();
                    pointScaleReward.setUser(user);
                    pointScaleReward.setPointScale(pointScale);
                }
                pointScaleReward.setAmount(rule.getAmount());
                pointScaleReward.setTimestamp(System.currentTimeMillis());
                pointScaleReward.setApplication(application);
                pointScaleRewardRepository.save(pointScaleReward);
                System.out.println("+" + rule.getAmount() + " " + rule.getPointScale() + " added to " + user.getUserId());
            }
        }

        // Save event for state rules purpose
        newEvent.setUser(user);
        newEvent.setApplication(application);
        newEvent.setTimestamp(System.currentTimeMillis());
        eventRepository.save(newEvent);


        // Get the event and build the response content of this event from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEvent.getEventId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Event>> getAllEvents(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        List<Event> events = new ArrayList<>();
        for (EventEntity eventEntity : eventRepository.findAllByApplicationApplicationName(apiKey)) {
            events.add(toEvent(eventEntity));
        }
        return ResponseEntity.ok(events);
    }

    public EventEntity toEventEntity(Event event) {
            EventEntity entity = new EventEntity();
            entity.setEventType(event.getEventType());
            UserEntity user = new UserEntity();
            user.setUserId(event.getUserId());
            entity.setUser(user);
        return entity;
    }

    private Event toEvent(EventEntity entity) {
        Event event = new Event();
        event.setEventType(entity.getEventType());
        // event.setEventProperties(entity.getEventProperties());
        event.setUserId(entity.getUser().getUserId());
        return event;
    }
}
