package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.BadgeEntity;
import ch.heig.amt.gamification.entities.BadgeRewardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BadgeRewardRepository extends PagingAndSortingRepository<BadgeRewardEntity, Integer> {

    List<BadgeEntity> findBadgeRewardEntitiesByUser_UserId(int id);
}
