package birding.observationdata.service.placemanager;

import java.util.List;
import java.util.UUID;

public interface PlaceService {
    List<Object>getAllCountries();

    Object getCountryById(UUID id);

    Object getPlaceById(UUID id);
}
