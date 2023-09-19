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
        return mapper.entityToDto(obsJpaRepository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public void deleteObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            obsJpaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Observation with id " + id + " not found");
        }
    }

    @Override
    public DtoObservationRsp findObservationById(UUID id) {
        if (obsJpaRepository.existsById(id))
            return mapper.entityToDto(obsJpaRepository.getReferenceById(id));
        throw new ResourceNotFoundException("Observation with id " + id + " not found");
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
        //ошибка: в observation вместо nest выводит null
        return mapper.listEntityToDto(obsJpaRepository.findAll());
    }
}
