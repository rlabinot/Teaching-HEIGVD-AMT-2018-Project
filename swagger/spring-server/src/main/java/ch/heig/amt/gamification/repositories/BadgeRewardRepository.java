package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.BadgeRewardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BadgeRewardRepository extends PagingAndSortingRepository<BadgeRewardEntity, Integer> {
}
