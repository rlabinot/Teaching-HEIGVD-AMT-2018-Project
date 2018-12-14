package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.BadgesApi;
import ch.heig.amt.gamification.api.model.Badge;
import ch.heig.amt.gamification.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    public ResponseEntity<Object> createBadge(@ApiParam(value = "", required = true) @Valid @RequestBody Badge badge) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteBadge(Integer badgeId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteBadges() {
        return null;
    }

    @Override
    public ResponseEntity<Object> editBadge(Integer badgeId) {
        return null;
    }

    @Override
    public ResponseEntity<Badge> getBadge(Integer badgeId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {
        return null;
    }
}
