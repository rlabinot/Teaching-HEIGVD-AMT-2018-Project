package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.PointscalesApi;
import ch.heig.amt.gamification.api.model.PointScale;
import ch.heig.amt.gamification.api.model.PointScaleNoId;
import ch.heig.amt.gamification.entities.PointScaleEntity;
import ch.heig.amt.gamification.entities.PointScaleEntity;
import ch.heig.amt.gamification.repositories.PointScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PointScalesApiController implements PointscalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<PointScale> createPointScale(@ApiParam(value = "" ,required=true ) @RequestBody PointScaleNoId pointscale,
                                                @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey",
                                                        required=true) String apiKey) {
        // Registration of the pointScale as an entity
        PointScaleEntity newPointScaleEntity = toPointScaleEntityNoId(pointscale);
        pointScaleRepository.save(newPointScaleEntity);

        // Get the pointScale and build the response content of this pointScale from his new link with id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPointScaleEntity.getPointScaleName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> deletePointScale(@ApiParam(value = "pointscale id",required=true ) @PathVariable("id") Integer id,
                                            @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey",
                                                    required=true) String apiKey) {
        PointScaleEntity pointScaleEntity = pointScaleRepository.findOne(id);

        // Checking if existing PointScale
        if (pointScaleEntity == null) {
            return ResponseEntity.notFound().build();
        }

        // delete PointScale and send no content as response
        pointScaleRepository.delete(pointScaleEntity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> editPointScale(@ApiParam(value = "pointscale with his new content" ,required=true ) @RequestBody PointScale pointscale,
                                          @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        // Checking if existing PointScale
        if (toPointScaleEntity(pointscale) == null) {
            return ResponseEntity.notFound().build();
        }

        // edit PointScale and send no content as response
        pointscale.setPointScaleName(pointscale.getPointScaleName());
        pointScaleRepository.save(toPointScaleEntity(pointscale));
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<PointScale>> getAllPointScales(@ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity : pointScaleRepository.findAll()) {
            pointScales.add(toPointScale(pointScaleEntity));
        }
        return ResponseEntity.ok(pointScales);
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(@ApiParam(value = "ID of the requested pointscale",required=true ) @PathVariable("id") Integer id,
                                             @ApiParam(value = "" ,required=true ) @RequestHeader(value="apiKey", required=true) String apiKey) {
        PointScaleEntity pointScaleEntity = pointScaleRepository.findOne(id);

        // Checking if existing PointScale
        if (pointScaleEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toPointScale(pointScaleEntity));
    }

    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setPointScaleName(pointScale.getPointScaleName());
        entity.setCounter(pointScale.getCounter());
        return entity;
    }
    private PointScaleEntity toPointScaleEntityNoId(PointScaleNoId pointScale) {
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
