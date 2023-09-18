package birding.observationdata.controller;

import birding.observationdata.dto.country.Country;
import birding.observationdata.dto.place.request.DtoPlaceRq;
import birding.observationdata.dto.place.response.DtoPlaceRsp;
import birding.observationdata.service.placemanager.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Place API")
public class PlaceController {
    @Autowired
    PlaceService placeService;

    @PostMapping("/places")
    @Operation(summary = "Create a new place in DB", description = "Creates a new place in DB and returns it")
    @ApiResponse(responseCode = "200", description = "A place was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<DtoPlaceRsp> createPlace (@RequestBody DtoPlaceRq dtoPlaceRq){
        return new ResponseEntity<DtoPlaceRsp>(placeService.createPlace(dtoPlaceRq), HttpStatus.OK);
    }

    @GetMapping("/places")
    @Operation(summary = "Get place", description = "Get place with the id number from table 'places'")
    @ApiResponse(responseCode = "200", description = "place from the table 'places' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<DtoPlaceRsp>>  getPlaces(){
        return new ResponseEntity<>(placeService.getPlaces(), HttpStatus.OK);
    }

    @GetMapping("/places/{id}")
    @Operation(summary = "Get place", description = "Get place with the id number from table 'places'")
    @ApiResponse(responseCode = "200", description = "place from the table 'places' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Object> getPlaceById(@PathVariable UUID id) {
        return new ResponseEntity<>(placeService.getPlaceById(id), HttpStatus.OK);
    }

    @PutMapping("/places/{id}")
    @Operation(summary = "Update place", description = "Updates the place with the id number and returns it from the DB")
    @ApiResponse(responseCode = "200", description = "A place was updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public DtoPlaceRsp putPlace (@RequestBody DtoPlaceRq dtoPlaceRq, @PathVariable UUID id){
        return placeService.updatePlace(dtoPlaceRq, id);
    }

//Ошибка: ограничения в Place-service. Невозможно удалить.
    @DeleteMapping("/places/{id}")
    @Operation(summary = "Delete place", description = "Delete place with the id number from table 'places'")
    @ApiResponse(responseCode = "200", description = "place from the table 'places' were deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void deletePlaceById(@PathVariable UUID id){
        placeService.deleteObservationById(id);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return new ResponseEntity<>(placeService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable UUID id){
        return new ResponseEntity<>(placeService.getCountryById(id), HttpStatus.OK);
    }
}
