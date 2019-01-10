package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.PointScalesApi;
import ch.heig.amt.gamification.api.model.PointScale;
import ch.heig.amt.gamification.entities.PointScaleEntity;
import ch.heig.amt.gamification.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PointScalesApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointScale) {
        // Registration of the pointScale as an entity
        PointScaleEntity newPointScaleEntity = toPointScaleEntity(pointScale);
        pointScaleRepository.save(newPointScaleEntity);

        // Get the pointScale and build the response content of this pointScale from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPointScaleEntity.getPointScaleName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {
        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity : pointScaleRepository.findAll()) {
            pointScales.add(toPointScale(pointScaleEntity));
        }
        return ResponseEntity.ok(pointScales);
    }

    public PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setPointScaleName(pointScale.getPointScaleName());
        entity.setCounter(pointScale.getCounter());
        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setPointScaleName(entity.getPointScaleName());
        pointScale.setCounter(entity.getCounter());
        return pointScale;
    }
}
