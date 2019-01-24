package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.PointRewardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PointRewardRepository extends PagingAndSortingRepository<PointRewardEntity, Integer> {
}
