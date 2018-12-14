package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface PointScaleRepository extends CrudRepository<FruitEntity, Long>{

}
