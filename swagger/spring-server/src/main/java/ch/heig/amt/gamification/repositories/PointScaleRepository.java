package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.entities.PointScaleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface PointScaleRepository extends PagingAndSortingRepository<PointScaleEntity, Integer>{

}
