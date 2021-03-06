package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.PointScaleEntity;
import ch.heig.amt.gamification.entities.PointScaleRewardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PointScaleRewardRepository extends PagingAndSortingRepository<PointScaleRewardEntity, Integer> {
    PointScaleRewardEntity findPointScaleRewardEntityByPointScalePointScaleNameAndUserUserId(String pointScaleName, int userId);
}
