package ch.heig.amt.gamification.api;

import ch.heig.amt.gamification.api.model.Badge;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-14T09:42:23.127+01:00")

@Api(value = "badges", description = "the badges API")
public interface BadgesApi {

    @ApiOperation(value = "", notes = "create a badge for my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Badge created", response = Object.class) })
    @RequestMapping(value = "/badges",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> createBadge(@ApiParam(value = "" ,required=true ) @RequestBody Badge badge);


    @ApiOperation(value = "", notes = "delete a specific badge of my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Badge successfully deleted", response = Object.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteBadge(@ApiParam(value = "ID of the requested badge",required=true ) @PathVariable("badgeId") Integer badgeId);


    @ApiOperation(value = "", notes = "delete all badges of my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "All badges successfully deleted", response = Object.class) })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteBadges();


    @ApiOperation(value = "", notes = "edit a specific badge of my application", response = Object.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Badge successfully updated", response = Object.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Object> editBadge(@ApiParam(value = "ID of the requested badge",required=true ) @PathVariable("badgeId") Integer badgeId);


    @ApiOperation(value = "", notes = "get a specific badge of my application", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Badge.class) })
    @RequestMapping(value = "/badges/{badgeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Badge> getBadge(@ApiParam(value = "ID of the requested badge",required=true ) @PathVariable("badgeId") Integer badgeId);


    @ApiOperation(value = "", notes = "get the list of all badges of my application", response = Badge.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Badge.class) })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Badge>> getBadges();

}
