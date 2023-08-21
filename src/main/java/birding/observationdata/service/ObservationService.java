package birding.observationdata.service;

import birding.observationdata.entity.Observation;

import java.util.List;

public interface ObservationService {

    Observation createNewObservation(Observation observation);

    void deleteObservationById(int id);

    Observation findObservationById(int id);

    Observation updateObservation(Observation observation, int id);

    List<Observation> getObservationWithSortingAndFiltration();
}
