package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.PointScaleRewardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PointRewardRepository extends PagingAndSortingRepository<PointScaleRewardEntity, Integer> {
}
