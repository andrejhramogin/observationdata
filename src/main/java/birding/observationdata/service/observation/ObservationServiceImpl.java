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
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
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

    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dtoObservationRq) {
        //ошибки при возврате save: dtoPlaceRsp = null, type: biotop, location, nestType = null
        // (при вызове findObservationById - все ок)
        Observation observation = obsJpaRepository.save(observationMapper.dtoToEntity(dtoObservationRq,
                dtoObservationRq.getDtoNestRq()));
        DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(observation, observation.getNest());
        dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(observation.getPlaceId()));
        dtoObservationRsp.setDtoNestRsp(nestMapper.entityToDto(observation.getNest()));
        log.info("Observation created successfully");
        return dtoObservationRsp;
    }

    @Override
    public void deleteObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            obsJpaRepository.deleteById(id);
            log.info("Observations with id = " + id + " deleted successfully");
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
            log.info("Observation with id = " + id + " found successfully");
            return dtoObservationRsp;
        }
        throw new ResourceNotFoundException("Observation with id " + id + " was not found");
    }

    @Override
    public DtoObservationRsp updateObservation(DtoObservationRq dtoObservationRq, UUID id) {
        if (obsJpaRepository.existsById(id)) {
            Observation updatedObs = obsJpaRepository.getReferenceById(id);
            boolean isNestExist = false;
            UUID nestId = null;
            if (updatedObs.getNest() != null) {
                nestId = updatedObs.getNest().getId();//т.к созд-ся новый Nest, по этому id удаляется предыдущий
                isNestExist = true;
            }
            Timestamp obsCreatedAt = updatedObs.getCreatedAt();//для отображения в dtoObsRsp в ответе (в базе сохраняется верно)

            Observation newObs = observationMapper.dtoToEntity(dtoObservationRq, dtoObservationRq.getDtoNestRq());
            newObs.setId(updatedObs.getId());

            obsJpaRepository.save(newObs);
            if (isNestExist) {
                nestService.deleteNestById(nestId);
            }
            DtoObservationRsp dtoObservationRsp = findObservationById(updatedObs.getId());
            dtoObservationRsp.setCreatedAt(obsCreatedAt);
            log.info("Observation with id = " + id + " updated successfully");
            return dtoObservationRsp;
        }
        throw new ResourceNotFoundException("Observation with id " + id + " not found");
    }

    @Override
    @Transactional
    public List<DtoObservationRsp> getAllObservation() {
        //ошибка: nest == null
        List<Observation> listObsEntity = obsJpaRepository.findAll();

        List<DtoObservationRsp> listDtoObsRsp = new ArrayList<>();
        for (Observation observation : listObsEntity) {
            listDtoObsRsp.add(observationMapper.entityToDto(observation, observation.getNest()));
        }
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
        log.info("Observations found successfully");
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
