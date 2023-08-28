package birding.observationdata.service.observation;

import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.ObservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private ObservationJpaRepository obsJpaRepository;
    @Autowired
    ObservationMapper mapper;

    public ObservationServiceImpl(ObservationJpaRepository obsJpaRepository) {
        this.obsJpaRepository = obsJpaRepository;
    }

    public ObservationServiceImpl(){
    }

    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dto) {
        Observation newObs = obsJpaRepository.save(mapper.dtoToEntity(dto));
        return findObservationById(newObs.getId());
    }
    @Override
    public void deleteObservationById(int id) {
        obsJpaRepository.deleteById(id);
    }

    @Override
    public DtoObservationRsp findObservationById(int id) {
        return mapper.entityToDto(obsJpaRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }
    @Override
    public DtoObservationRsp updateObservation(DtoObservationRq obs, int id) {
        if (obsJpaRepository.existsById(id)) {
            Observation newObs = mapper.dtoToEntity(obs);
            Observation updatedObs = obsJpaRepository.getReferenceById(id);
            newObs.setId(updatedObs.getId());
            newObs.setCreatedAt(updatedObs.getCreatedAt());
            newObs.setModifiedAt(new Timestamp(System.currentTimeMillis()));
            obsJpaRepository.save(newObs);
            return findObservationById(updatedObs.getId());
        }
        return mapper.entityToDto(obsJpaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }
    @Override
    public List<DtoObservationRsp> getAllObservation() {
        return obsJpaRepository.findAll().stream()
                .map(mapper::entityToDto)
                .toList();
    }
}