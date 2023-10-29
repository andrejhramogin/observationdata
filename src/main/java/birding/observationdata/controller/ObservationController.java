package birding.observationdata.controller;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.ObservationPage;
import birding.observationdata.entity.ObservationSearchCriteria;
import birding.observationdata.service.observation.ObservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Observation API")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @PostMapping("/observation")
    @Operation(summary = "Create a new observation in DB", description = "Creates a new observation in DB and returns it")
    @ApiResponse(responseCode = "201", description = "A observation was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<DtoObservationRsp> createObservation(@Valid @RequestBody DtoObservationRq observation) {
        return new ResponseEntity<>(observationService.createNewObservation(observation), HttpStatus.CREATED);
    }

    @GetMapping("/observation/{id}")
    @Operation(summary = "Get observation", description = "Get observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<DtoObservationRsp> getObservationById(
            @PathVariable("id") @NotNull UUID id) {
        return new ResponseEntity<>(observationService.findObservationById(id), HttpStatus.OK);
    }

    @GetMapping("/observation")
    @Operation(summary = "Get observation", description = "Get all observation from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<DtoObservationRsp>> getAllObservation() {
        return new ResponseEntity<>(observationService.getAllObservation(), HttpStatus.OK);
    }

    @GetMapping("/observationfiltr")
    @Operation(summary = "Get observation with sorting", description = "Get observations with filtration and sorting " +
            "according to the specified parameters")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Page<DtoObservationRsp>> getObservationWithFiltration(ObservationPage observationPage,
                                                                          ObservationSearchCriteria observationSearchCriteria) {
        return new ResponseEntity<>(observationService.getObservationsWithSortingAndFiltration(observationPage,
                observationSearchCriteria), HttpStatus.OK);
    }

    @PutMapping("/observation/{id}")
    @Operation(summary = "Update observation", description = "Updates the observation with the id number " +
            "and returns it from the DB")
    @ApiResponse(responseCode = "200", description = "A observation was updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<DtoObservationRsp> putObservation(@Valid @RequestBody DtoObservationRq rqObs,
                                                            @PathVariable("id") @NotNull UUID id) {
        return new ResponseEntity<>(observationService.updateObservation(rqObs, id), HttpStatus.OK);
    }

    @DeleteMapping("/observation/{id}")
    @Operation(summary = "Delete observation", description = "Delete observation with the id number from table 'observation'")
    @ApiResponse(responseCode = "200", description = "observation from the table 'observations' were deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<String> deleteObservationById(@PathVariable UUID id) {
        observationService.deleteObservationById(id);
        return new ResponseEntity<>("Observation with id = " + id + " deleted successfully.", HttpStatus.OK);
    }
}
