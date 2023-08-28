package birding.observationdata.service;

import birding.observationdata.dto.request.DtoObservationRq;
import birding.observationdata.dto.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;

import java.util.List;

public interface ObservationService {

    DtoObservationRsp createNewObservation(DtoObservationRq observation);

    void deleteObservationById(int id);

    DtoObservationRsp findObservationById(int id);

    DtoObservationRsp updateObservation(DtoObservationRq rqObs, int id);

    List<DtoObservationRsp> getAllObservation();
}
