package birding.observationdata.integration.place;

import birding.observationdata.integration.place.dto.request.PlaceDtoReq;
import birding.observationdata.integration.place.dto.response.PlaceDtoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@FeignClient(
        name = "place-client",
        url = "http://localhost:8081"
)
public interface PlaceClient {

    @PostMapping("/places")
    PlaceDtoResp createPlace(PlaceDtoReq placeDtoReq);

    @GetMapping("/places")
    List<PlaceDtoResp> getPlaces();

    @GetMapping("/places/{id}")
    PlaceDtoResp getPlaceById(@PathVariable(value = "id") UUID id);

    @PutMapping("/places/{id}")
    PlaceDtoResp updatePlace(PlaceDtoReq placeDtoReq, @PathVariable(value = "id") UUID id);

    @DeleteMapping("/places/{id}")
    void deletePlaceById(@PathVariable(value = "id") UUID id);

    @GetMapping("/placesByIdList")
    List<PlaceDtoResp> getPlacesByIdList(@RequestParam(value = "uuidSet") Set<UUID> uuidSet);

//    @GetMapping(value = "/placesByIdList")
//    List<PlaceDtoResp> getPlacesByIdList(@RequestParam Set<UUID> uuidSet);

//    public ResponseEntity<Collection<PlaceDtoResp>> getPlacesByIdList(@RequestParam Set<UUID> uuidSet);
}
/**
 * @GetMapping(value = "/placesByIdList")
 * public ResponseEntity<Collection<PlaceDtoResp>> getPlacesByIdList(
 * @Valid @RequestParam Set<UUID> uuidSet) {
 * log.info("Get place info by Id list");
 * return new ResponseEntity<>(placeServiceImpl.getPlacesByIdList(uuidSet), HttpStatus.OK);
 * }
 */