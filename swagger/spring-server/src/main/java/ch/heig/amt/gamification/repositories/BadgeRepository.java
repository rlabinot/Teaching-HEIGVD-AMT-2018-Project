package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.api.model.Badge;
import ch.heig.amt.gamification.entities.BadgeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface BadgeRepository extends PagingAndSortingRepository<BadgeEntity, Integer>{
    List<BadgeEntity> findAllByApplicationApplicationName(String apiKey);
    Badge findByBadgeIdAndApplicationApplicationName(int id, String apiKey);
}
