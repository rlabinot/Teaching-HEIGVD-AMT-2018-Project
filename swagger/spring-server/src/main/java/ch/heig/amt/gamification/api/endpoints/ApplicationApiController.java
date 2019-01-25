package ch.heig.amt.gamification.api.endpoints;

import ch.heig.amt.gamification.api.ApplicationApi;
import ch.heig.amt.gamification.api.model.Application;
import ch.heig.amt.gamification.entities.ApplicationEntity;
import ch.heig.amt.gamification.repositories.ApplicationRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApplicationApiController implements ApplicationApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Object> createApplication(@ApiParam(value = "" ,required=true ) @RequestBody Application application) {
        // Registration of the badge as an entity
        ApplicationEntity app = toApplicationEntity(application);

        if(applicationRepository.findByApplicationName(application.getName()) != null) {
            return ResponseEntity.status(409).build();
        }
        applicationRepository.save(app);

        return ResponseEntity.ok().build();
    }

    private ApplicationEntity toApplicationEntity(Application app) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationName(app.getName());
        return applicationEntity;
    }
}
