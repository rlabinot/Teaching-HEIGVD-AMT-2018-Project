package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByUserIdAndAndApplication_ApplicationName(int userId, String applicationName);
    
}
