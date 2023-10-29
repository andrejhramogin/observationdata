package birding.observationdata.service.observation;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.entity.ObservationPage;
import birding.observationdata.entity.ObservationSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * ObservationService interface.
 */
public interface ObservationService {

    DtoObservationRsp createNewObservation(DtoObservationRq observation);

    void deleteObservationById(UUID id);

    DtoObservationRsp findObservationById(UUID id);

    DtoObservationRsp updateObservation(DtoObservationRq rqObs, UUID id);

    List<DtoObservationRsp> getAllObservation();

    Set<UUID> createSetOfPlaceId(List<Observation> listEntity);

    Page<DtoObservationRsp> getObservationsWithSortingAndFiltration(ObservationPage observationPage,
                                                              ObservationSearchCriteria observationSearchCriteria);
}
