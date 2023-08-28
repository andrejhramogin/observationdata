package birding.observationdata.controller;

import birding.observationdata.dto.request.DtoObservationRq;
import birding.observationdata.dto.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.service.ObservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Observation API")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @PostMapping("/observation")
    @Operation(summary = "Create a new observation in DB", description = "Creates a new observation in DB and returns it")
    @ApiResponse(responseCode = "200", description = "A observation was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public DtoObservationRsp createObservation (@Valid @RequestBody DtoObservationRq observation){
        return observationService.createNewObservation(observation);
    }

    @GetMapping("/observation/{id}")
    @Operation(summary = "Get observation", description = "Get observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public DtoObservationRsp getObsById(@PathVariable int id){
        return observationService.findObservationById(id);
    }

    @GetMapping("/observation")
    @Operation(summary = "Get observation", description = "Get all observation from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public List<DtoObservationRsp> getAllObservation(){
        return observationService.getAllObservation();
    }

    @PutMapping("/observation/{id}")
    @Operation(summary = "Update observation", description = "Updates the observation with the id number and returns it from the DB")
    @ApiResponse(responseCode = "200", description = "A observation was updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public DtoObservationRsp putObservation (@Valid @RequestBody DtoObservationRq rqObs, @PathVariable int id){
        return observationService.updateObservation(rqObs, id);
    }

    @DeleteMapping("/observation/{id}")
    @Operation(summary = "Delete observation", description = "Delete observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public void deleteObsById(@PathVariable int id){
        observationService.deleteObservationById(id);
    }
}
