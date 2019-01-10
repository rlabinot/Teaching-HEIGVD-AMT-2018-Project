package ch.heig.amt.gamification.api;

import ch.heig.amt.gamification.api.model.Rule;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-10T14:12:27.802+01:00")

@Api(value = "rules", description = "the rules API")
public interface RulesApi {

    @ApiOperation(value = "", notes = "create a rule for my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "created", response = Object.class) })
    @RequestMapping(value = "/rules",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> createRule(@ApiParam(value = "" ,required=true ) @RequestBody Rule rule);


    @ApiOperation(value = "", notes = "get the list of all point-scale of my application", response = Rule.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Rule.class) })
    @RequestMapping(value = "/rules",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Rule>> getRules();

}
