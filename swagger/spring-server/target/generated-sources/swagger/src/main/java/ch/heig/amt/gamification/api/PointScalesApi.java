package ch.heig.amt.gamification.api;

import ch.heig.amt.gamification.api.model.PointScale;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-07T16:35:12.037+01:00")

@Api(value = "pointScales", description = "the pointScales API")
public interface PointScalesApi {

    @ApiOperation(value = "", notes = "create a point-scale for my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "created", response = Object.class) })
    @RequestMapping(value = "/pointScales",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> createPointScale(@ApiParam(value = "" ,required=true ) @RequestBody PointScale pointscale);


    @ApiOperation(value = "", notes = "get the list of all point-scale of my application", response = PointScale.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = PointScale.class) })
    @RequestMapping(value = "/pointScales",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PointScale>> getPointScales();

}
