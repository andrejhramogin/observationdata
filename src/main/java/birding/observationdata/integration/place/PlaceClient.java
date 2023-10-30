package birding.observationdata.integration.place;

import birding.observationdata.integration.place.dto.response.PlaceDtoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * FeignClient for Place service
 */
@FeignClient(
        name = "place-client",
        url = "http://localhost:8081"
)
public interface PlaceClient {

    @GetMapping("/places/{id}")
    PlaceDtoResp getPlaceById(@PathVariable(value = "id") UUID id);

    @GetMapping("/placesByIdList")
    List<PlaceDtoResp> getPlacesByIdList(@RequestParam(value = "uuidSet") Set<UUID> uuidSet);
}
