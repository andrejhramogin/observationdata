package birding.observationdata.service.placemanager;

import birding.observationdata.feignclient.PlaceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceClient placeClient;
    @Override
    public List<Object> getAllCountries() {
        return placeClient.getCountries();
    }

    @Override
    public Object getCountryById(UUID id) {
        return placeClient.getCountryById(id);
    }

    @Override
    public Object getPlaceById(UUID id) {
        return placeClient.getPlaceById(id);
    }
}
