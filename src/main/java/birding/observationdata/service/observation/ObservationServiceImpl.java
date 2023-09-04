package birding.observationdata.service.observation;

import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.ObservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private ObservationJpaRepository obsJpaRepository;
    @Autowired
    private ObservationMapper mapper;

    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dto) {
        Observation observation = mapper.dtoToEntity(dto);
        obsJpaRepository.save(observation);
        return findObservationById(observation.getId());
//        return mapper.entityToDto(obsJpaRepository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public void deleteObservationById(UUID id) {
        obsJpaRepository.deleteById(id);
    }

    @Override
    public DtoObservationRsp findObservationById(UUID id) {
        return mapper.entityToDto(obsJpaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }

    @Override
    public DtoObservationRsp updateObservation(DtoObservationRq obs, UUID id) {
        if (obsJpaRepository.existsById(id)) {
            Observation newObs = mapper.dtoToEntity(obs);
            Observation updatedObs = obsJpaRepository.getReferenceById(id);
            newObs.setId(updatedObs.getId());
            obsJpaRepository.save(newObs);
            return findObservationById(updatedObs.getId());
        }
        return mapper.entityToDto(obsJpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }

    @Override
    public List<DtoObservationRsp> getAllObservation() {
        //не корректно работает: в observation вместо nest выводит null
        return mapper.listEntityToDto(obsJpaRepository.findAll());

//        return obsJpaRepository.findAll().stream()
//                .map(mapper::entityToDto)
//                .toList();
    }
}
