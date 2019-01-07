package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.RuleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface RuleRepository extends PagingAndSortingRepository<RuleEntity, String>{

}
