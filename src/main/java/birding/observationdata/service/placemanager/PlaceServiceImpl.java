package birding.observationdata.service.placemanager;

import birding.observationdata.integration.place.dto.request.DtoPlaceRq;
import birding.observationdata.integration.place.dto.response.DtoPlaceRsp;
import birding.observationdata.integration.place.PlaceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceClient placeClient;
    @Override
    public DtoPlaceRsp createPlace(DtoPlaceRq dtoPlaceRq) {
        return placeClient.createPlace(dtoPlaceRq);
    }
    @Override
    public List<DtoPlaceRsp> getPlaces() {
        return placeClient.getPlaces();
    }
    @Override
    public DtoPlaceRsp getPlaceById(UUID id) {
        return placeClient.getPlaceById(id);
    }
    @Override
    public DtoPlaceRsp updatePlace(DtoPlaceRq dtoPlaceRq, UUID id) {
        return placeClient.updatePlace(dtoPlaceRq, id);
    }
}
