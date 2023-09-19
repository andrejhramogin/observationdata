package birding.observationdata.integration.place;

import birding.observationdata.dto.country.Country;
import birding.observationdata.integration.place.dto.request.DtoPlaceRq;
import birding.observationdata.integration.place.dto.response.DtoPlaceRsp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "place-client",
        url = "http://localhost:8081"
)
public interface PlaceClient {

    @PostMapping("/places")
    DtoPlaceRsp createPlace(DtoPlaceRq dtoPlaceRq);

    @GetMapping("/places")
    List<DtoPlaceRsp> getPlaces();

    @GetMapping("/places/{id}")
    DtoPlaceRsp getPlaceById(@PathVariable(value = "id") UUID id);

    @PutMapping("/places/{id}")
    DtoPlaceRsp updatePlace(DtoPlaceRq dtoPlaceRq, @PathVariable(value = "id") UUID id);

    @DeleteMapping("/places/{id}")
    void deletePlaceById(@PathVariable(value = "id") UUID id);

    @GetMapping("/countries")
    List<Country> getCountries();

    @GetMapping("/countries/{id}")
    Country getCountryById(@PathVariable(value = "id") UUID id);
}
