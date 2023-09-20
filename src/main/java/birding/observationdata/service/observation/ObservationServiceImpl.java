package birding.observationdata.service.observation;

import birding.observationdata.integration.place.PlaceClient;
import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.ObservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private ObservationJpaRepository obsJpaRepository;
    @Autowired
    private ObservationMapper mapper;
    @Autowired
    private PlaceClient placeClient;

    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dto) {
        //ошибки при возврате save: dtoPlaceRsp = null, type: biotop, location, nestType = null
        // (при вызове findObservationById - все ок)
        return mapper.entityToDto(obsJpaRepository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public void deleteObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            obsJpaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Observation with id " + id + " not found");
        }
    }

    @Override
    public DtoObservationRsp findObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            Observation obsReferencedById = obsJpaRepository.getReferenceById(id);
            DtoObservationRsp dtoObservationRsp = mapper.entityToDto(obsReferencedById);
            dtoObservationRsp.setDtoPlaceRsp(placeClient.getPlaceById(obsReferencedById.getPlaceId()));
            return dtoObservationRsp;
        }
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
        List<Observation> listEntity = obsJpaRepository.findAll();
        List<DtoObservationRsp> listDtoRsp = mapper.listEntityToDto(listEntity);
        for (int i = 0; i < listEntity.size(); i++) {
            listDtoRsp.get(i).setDtoPlaceRsp(placeClient.getPlaceById(listEntity.get(i).getPlaceId()));
        }
        return listDtoRsp;
    }
}
