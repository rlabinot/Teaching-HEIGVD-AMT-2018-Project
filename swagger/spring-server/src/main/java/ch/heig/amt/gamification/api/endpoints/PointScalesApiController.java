package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.PointScalesApi;
import ch.heig.amt.gamification.api.model.PointScale;
import ch.heig.amt.gamification.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PointScalesApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointscale) {
        return null;
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {
        return null;
    }
}
