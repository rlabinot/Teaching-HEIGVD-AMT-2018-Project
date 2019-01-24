package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.EventsApi;
import ch.heig.amt.gamification.api.model.Event;
import ch.heig.amt.gamification.entities.ApplicationEntity;
import ch.heig.amt.gamification.entities.EventEntity;
import ch.heig.amt.gamification.entities.RuleEntity;
import ch.heig.amt.gamification.entities.UserEntity;
import ch.heig.amt.gamification.repositories.ApplicationRepository;
import ch.heig.amt.gamification.repositories.EventRepository;
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
public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Object> createEvent(Event event) {
        // Transform the event to an entity and save it for future state rules
        EventEntity newEventEntity = toEventEntity(event);

        // Get the application that send this event
        ApplicationEntity application = applicationRepository.findByApplicationName(); // TODO
        if (application == null) {
            return null;
        }

        // Get the user which sends the event
        UserEntity user = newEventEntity.get


        // Get the rules that match this event type
        List<RuleEntity> rules = ruleRepository.findAll(); // TODO
        if (rules.isEmpty()) {
            return null;
        }




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
