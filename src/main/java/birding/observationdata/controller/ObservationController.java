package birding.observationdata.controller;

import birding.observationdata.entity.Observation;
import birding.observationdata.service.ObservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @PostMapping("/observation")
    @Operation(summary = "Create a new observation in DB", description = "Creates a new observation in DB and returns it")
    @ApiResponse(responseCode = "200", description = "A observation was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public Observation createObservation (@RequestBody Observation observation){
        return observationService.createNewObservation(observation);
    }

    @GetMapping("/observation/{id}")
    @Operation(summary = "Get observation", description = "Get observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'cars' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public Observation getObsById(@PathVariable int id){
        return observationService.findObservationById(id);
    }



    @DeleteMapping("/observation/{id}")
    @Operation(summary = "Delete observation", description = "Delete observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'cars' were deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public void deleteObsById(@PathVariable int id){
        observationService.deleteObservationById(id);
    }
}
