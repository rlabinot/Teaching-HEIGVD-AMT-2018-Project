package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByUserIdAndApplicationApplicationName(int userId, String apiKey);
    List<UserEntity> findAllByApplicationApplicationName(String apiKey);
}
