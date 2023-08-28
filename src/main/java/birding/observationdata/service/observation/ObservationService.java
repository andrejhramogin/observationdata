package birding.observationdata.service.observation;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;

import java.util.List;

public interface ObservationService {

    DtoObservationRsp createNewObservation(DtoObservationRq observation);

    void deleteObservationById(int id);

    DtoObservationRsp findObservationById(int id);

    DtoObservationRsp updateObservation(DtoObservationRq rqObs, int id);

    List<DtoObservationRsp> getAllObservation();
}
