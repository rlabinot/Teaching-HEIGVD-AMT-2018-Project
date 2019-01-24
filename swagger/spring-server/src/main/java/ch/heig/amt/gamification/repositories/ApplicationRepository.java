package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.ApplicationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, Integer> {
    ApplicationEntity findByApplicationName(String name);
}
