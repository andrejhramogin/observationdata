package birding.observationdata.service.placemanager;

import birding.observationdata.dto.country.Country;
import birding.observationdata.dto.place.request.DtoPlaceRq;
import birding.observationdata.dto.place.response.DtoPlaceRsp;
import birding.observationdata.feignclient.PlaceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {
    private static final Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);
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

    @Override
    public void deleteObservationById(UUID id) {
        placeClient.deletePlaceById(id);
    }

    @Override
    public List<Country> getAllCountries() {
        return placeClient.getCountries();
    }

    @Override
    public Country getCountryById(UUID id) {
        return placeClient.getCountryById(id);
    }
}
