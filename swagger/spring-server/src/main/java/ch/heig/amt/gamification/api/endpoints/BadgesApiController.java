package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.BadgesApi;
import ch.heig.amt.gamification.api.model.Badge;
import ch.heig.amt.gamification.entities.BadgeEntity;
import ch.heig.amt.gamification.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Object> createBadge(@ApiParam(value = "", required = true) @Valid @RequestBody Badge badge) {
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        int id = newBadgeEntity.getBadgeId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBadgeEntity.getBadgeId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> deleteBadge(Integer badgeId) {
        BadgeEntity badgeEntity = badgeRepository.findOne(badgeId);
        badgeRepository.delete(badgeEntity);
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteBadges() {
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badgeRepository.delete(badgeEntity);
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> editBadge(Integer badgeId) {
        return null;
    }

    @Override
    public ResponseEntity<Badge> getBadge(Integer badgeId) {
        BadgeEntity badgeEntity = badgeRepository.findOne(badgeId);
        return ResponseEntity.ok(toBadge(badgeEntity));
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {
        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }
        return ResponseEntity.ok(badges);
    }

    public BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setBadgeName(badge.getBadgeName());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setBadgeName(entity.getBadgeName());
        return badge;
    }
}
