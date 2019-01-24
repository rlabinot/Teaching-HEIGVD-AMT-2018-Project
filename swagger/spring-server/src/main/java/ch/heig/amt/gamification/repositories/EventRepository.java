package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.EventEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface EventRepository extends PagingAndSortingRepository<EventEntity, Integer>{
    List<EventEntity> findAllByApplicationApplicationName(String apiKey);
}
