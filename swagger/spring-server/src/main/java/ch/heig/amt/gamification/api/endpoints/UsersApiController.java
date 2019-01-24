package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.UsersApi;
import ch.heig.amt.gamification.api.model.Badge;
import ch.heig.amt.gamification.api.model.User;
import ch.heig.amt.gamification.entities.BadgeEntity;
import ch.heig.amt.gamification.entities.UserEntity;
import ch.heig.amt.gamification.repositories.ApplicationRepository;
import ch.heig.amt.gamification.repositories.BadgeRewardRepository;
import ch.heig.amt.gamification.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<List<User>> getAllUsers(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey",
            required=true) String apiKey) {
        
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAllByApplicationApplicationName(apiKey)) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUser(String apiKey, Integer id) {

        UserEntity badgeEntity = userRepository.findByUserIdAndApplicationApplicationName(id, apiKey);

        // Checking if existing badge
        if (badgeEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toUser(badgeEntity));
    }

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String apiKey, Integer id) {
        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRewardRepository.findBadgeRewardEntitiesByUserUserIdAndApplicationApplicationName(id, apiKey)) {
            badges.add(BadgesApiController.toBadge(badgeEntity));
        }
        return ResponseEntity.ok(badges);
    }
    
    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getId());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setId((entity.getUserId()));
        return user;
    }
}
