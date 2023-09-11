package birding.observationdata.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "place-client",
        url = "http://localhost:8081"
)
public interface PlaceClient {
    @GetMapping("/countries")
    List<Object>getCountries();

    @GetMapping("/countries/{id}")
    Object getCountryById(UUID id);

    @GetMapping("/places/{id}")
    Object getPlaceById(UUID id);

//    @PostMapping("/places")

}
