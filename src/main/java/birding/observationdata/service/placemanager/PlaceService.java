package birding.observationdata.service.placemanager;

import birding.observationdata.integration.place.dto.request.DtoPlaceRq;
import birding.observationdata.integration.place.dto.response.DtoPlaceRsp;

import java.util.List;
import java.util.UUID;

public interface PlaceService {

    DtoPlaceRsp createPlace(DtoPlaceRq dtoPlaceRq);

    List<DtoPlaceRsp> getPlaces();

    DtoPlaceRsp getPlaceById(UUID id);

    DtoPlaceRsp updatePlace(DtoPlaceRq dtoPlaceRq, UUID id);
}
