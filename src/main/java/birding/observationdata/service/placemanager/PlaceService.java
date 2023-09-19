package birding.observationdata.service.placemanager;

import birding.observationdata.dto.country.Country;
import birding.observationdata.dto.place.request.DtoPlaceRq;
import birding.observationdata.dto.place.response.DtoPlaceRsp;

import java.util.List;
import java.util.UUID;

public interface PlaceService {

    DtoPlaceRsp createPlace(DtoPlaceRq dtoPlaceRq);

    List<DtoPlaceRsp> getPlaces();

    DtoPlaceRsp getPlaceById(UUID id);

    DtoPlaceRsp updatePlace(DtoPlaceRq dtoPlaceRq, UUID id);
}
