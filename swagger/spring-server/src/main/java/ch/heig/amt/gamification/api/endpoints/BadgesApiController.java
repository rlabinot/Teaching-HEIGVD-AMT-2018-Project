package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.BadgesApi;
import ch.heig.amt.gamification.api.model.Badge;
import ch.heig.amt.gamification.api.model.BadgeNoId;
import ch.heig.amt.gamification.entities.BadgeEntity;
import ch.heig.amt.gamification.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<Badge> createBadge(@ApiParam(value = "" ,required=true ) @RequestBody BadgeNoId badge,
                                      @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Registration of the badge as an entity
        BadgeEntity newBadgeEntity = toBadgeEntityNoId(badge);
        badgeRepository.save(newBadgeEntity);

        // Get the badge and build the response content of this badge from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getBadgeId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> deleteBadge(@ApiParam(value = "badge id",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey)
    {
        BadgeEntity badgeEntity = badgeRepository.findOne(id);

        // Checking if existing badge
        if (badgeEntity == null) {
            return ResponseEntity.notFound().build();
        }

        // delete badge and send no content as response
        badgeRepository.delete(badgeEntity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteAllBadges(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey",
            required=true) String apiKey) {
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badgeRepository.delete(badgeEntity);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Object> editBadge(@ApiParam(value = "badge with his new content" ,required=true ) @RequestBody Badge badge,
                                     @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {

        // Checking if existing badge
        if (toBadgeEntity(badge) == null) {
            return ResponseEntity.notFound().build();
        }

        // edit badge and send no content as response
        badge.setBadgeName(badge.getBadgeName());
        badgeRepository.save(toBadgeEntity(badge));
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Badge> getBadge(@ApiParam(value = "ID of the requested badge",required=true ) @PathVariable("id") Integer id,
                                   @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {

        BadgeEntity badgeEntity = badgeRepository.findOne(id);

        // Checking if existing badge
        if (badgeEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toBadge(badgeEntity));
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey",
            required=true) String apiKey) {
        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }
        return ResponseEntity.ok(badges);
    }

    public BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setBadgeId(badge.getBadgeId());
        entity.setBadgeName(badge.getBadgeName());
        return entity;
    }

    public BadgeEntity toBadgeEntityNoId(BadgeNoId badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setBadgeName(badge.getBadgeName());
        return entity;
    }

    public static Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setBadgeId((entity.getBadgeId()));
        badge.setBadgeName(entity.getBadgeName());
        return badge;
    }
}
