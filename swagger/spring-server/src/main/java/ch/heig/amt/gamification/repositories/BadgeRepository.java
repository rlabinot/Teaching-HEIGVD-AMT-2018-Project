package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.BadgeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface BadgeRepository extends PagingAndSortingRepository<BadgeEntity, Integer>{

}
