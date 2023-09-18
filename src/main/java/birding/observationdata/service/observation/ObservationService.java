package birding.observationdata.service.observation;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.dto.place.request.DtoPlaceRq;

import java.util.List;
import java.util.UUID;

public interface ObservationService {

    DtoObservationRsp createNewObservation(DtoObservationRq observation);

    void deleteObservationById(UUID id);

    DtoObservationRsp findObservationById(UUID id);

    DtoObservationRsp updateObservation(DtoObservationRq rqObs, UUID id);

    List<DtoObservationRsp> getAllObservation();
}
