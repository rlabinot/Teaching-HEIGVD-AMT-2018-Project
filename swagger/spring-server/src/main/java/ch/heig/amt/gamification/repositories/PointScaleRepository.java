package ch.heig.amt.gamification.repositories;

import ch.heig.amt.gamification.api.endpoints.EventsApiController;
import ch.heig.amt.gamification.entities.PointScaleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Labinot Rashiti on 14/12/18.
 */
public interface PointScaleRepository extends PagingAndSortingRepository<PointScaleEntity, Integer>{

    // to change : save,findOne, delete, findAll
    PointScaleEntity findByPointScaleIdAndApplicationApplicationName(int id, String apiKey);
    PointScaleEntity findByPointScaleNameAndApplicationApplicationName(String pointScaleName, String apikey);
    List<PointScaleEntity> findAllByApplicationApplicationName(String apiKey);
    void deletePointScaleEntityByPointScaleIdAndApplicationApplicationName(Integer id, String apiKey);
}
