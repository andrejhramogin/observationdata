package birding.observationdata.service.observation;

import birding.observationdata.integration.place.PlaceClient;
import birding.observationdata.integration.place.dto.response.PlaceDtoResp;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.ObservationJpaRepository;
import birding.observationdata.service.nest.NestService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private ObservationJpaRepository obsJpaRepository;
    @Autowired
    private ObservationMapper observationMapper;
    @Autowired
    private NestMapper nestMapper;
    @Autowired
    private PlaceClient placeClient;
    @Autowired
    private NestService nestService;

//    private static final Logger logger = LoggerFactory.getLogger(ObservationServiceImpl.class);

    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dtoObservationRq) {
        //ошибки при возврате save: dtoPlaceRsp = null, type: biotop, location, nestType = null
        // (при вызове findObservationById - все ок)
        @Valid
        Observation observation = obsJpaRepository.save(observationMapper.dtoToEntity(dtoObservationRq,
                dtoObservationRq.getDtoNestRq()));
        DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(observation, observation.getNest());
        dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(observation.getPlaceId()));
        dtoObservationRsp.setDtoNestRsp(nestMapper.entityToDto(observation.getNest()));
        return dtoObservationRsp;
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
            DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(obsReferencedById, obsReferencedById.getNest());
            dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(obsReferencedById.getPlaceId()));
            return dtoObservationRsp;
        }
        throw new ResourceNotFoundException("Observation with id " + id + " not found");
    }

    @Override
    public DtoObservationRsp updateObservation(DtoObservationRq dtoObservationRq, UUID id) {
        if (obsJpaRepository.existsById(id)) {
            Observation updatedObs = obsJpaRepository.getReferenceById(id);
            UUID nestId = updatedObs.getNest().getId();//т.к созд-ся новый Nest, по этому id удаляется предыдущий
            Timestamp obsCreatedAt = updatedObs.getCreatedAt();//для отображения в dtoObsRsp в ответе (в базе сохраняется верно)

            Observation newObs = observationMapper.dtoToEntity(dtoObservationRq, dtoObservationRq.getDtoNestRq());
            newObs.setId(updatedObs.getId());

            obsJpaRepository.save(newObs);
            nestService.deleteNestById(nestId);
            DtoObservationRsp dtoObservationRsp = findObservationById(updatedObs.getId());
            dtoObservationRsp.setCreatedAt(obsCreatedAt);
            return dtoObservationRsp;
        }
        throw new ResourceNotFoundException("Observation with id " + id + " not found");
    }

    @Override
    public List<DtoObservationRsp> getAllObservation() {
        //ошибка: nest == null
        List<Observation> listObsEntity = obsJpaRepository.findAll();
        List<DtoObservationRsp> listDtoObsRsp = observationMapper.listEntityToDto(listObsEntity);
        Set<UUID> placeIdSet = new HashSet<>(createSetOfPlaceId(listObsEntity));
        List<PlaceDtoResp> listPlaceDtoResp = placeClient.getPlacesByIdList(placeIdSet);

        for (int i = 0; i < listDtoObsRsp.size(); i++) {
            for (PlaceDtoResp placeDtoResp : listPlaceDtoResp) {
                if (listObsEntity.get(i).getPlaceId().equals(placeDtoResp.getId())) {
                    listDtoObsRsp.get(i).setPlaceDtoResp(placeDtoResp);
                    break;
                }
            }
        }
        return listDtoObsRsp;
    }

    public Set<UUID> createSetOfPlaceId(@NotNull List<Observation> listEntity) {
        Set<UUID> placeIdSet = new HashSet<>();
        for (Observation observation : listEntity) {
            placeIdSet.add(observation.getPlaceId());
        }
        return placeIdSet;
    }
}
