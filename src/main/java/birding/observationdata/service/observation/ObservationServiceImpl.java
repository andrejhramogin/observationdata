package birding.observationdata.service.observation;

import birding.observationdata.entity.*;
import birding.observationdata.integration.place.PlaceClient;
import birding.observationdata.integration.place.dto.response.PlaceDtoResp;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Service with methods for creating, finding, updating, deleting observations.
 */
@Service
@Slf4j
public class ObservationServiceImpl implements ObservationService {
    @Autowired
    private LocationJpaRepository locationJpaRepository;
    @Autowired
    private NestTypeJpaRepository nestTypeJpaRepository;
    @Autowired
    private BiotopeJpaRepository biotopeJpaRepository;
    @Autowired
    private ObservationJpaRepository obsJpaRepository;
    @Autowired
    private NestJpaRepository nestJpaRepository;
    @Autowired
    private ObservationCriteriaRepository observationCriteriaRepository;
    @Autowired
    private ObservationMapper observationMapper;
    @Autowired
    private NestMapper nestMapper;
    @Autowired
    private PlaceClient placeClient;

    /**
     * Method for creating new observation. Fields nest, placeId of Observation can be null.
     *
     * @param dtoObservationRq
     * @return
     */
    @Override
    public DtoObservationRsp createNewObservation(DtoObservationRq dtoObservationRq) {
        Observation observation = obsJpaRepository.save(observationMapper.dtoToEntity(dtoObservationRq,
                dtoObservationRq.getDtoNestRq()));
        DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(observation, observation.getNest());
        if (Objects.nonNull(observation.getPlaceId())) {
            dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(observation.getPlaceId()));
        }
        if (Objects.nonNull(observation.getNest())) {
            Nest nest = observation.getNest();
            Biotope biotope = biotopeJpaRepository.getReferenceById(nest.getBiotope().getId());
            Location location = locationJpaRepository.getReferenceById(nest.getLocation().getId());
            NestType nestType = nestTypeJpaRepository.getReferenceById(nest.getNestType().getId());

            dtoObservationRsp.setDtoNestRsp(nestMapper.entityToDto(nest));

            dtoObservationRsp.getDtoNestRsp().getBiotope().setType(biotope.getType());
            dtoObservationRsp.getDtoNestRsp().getLocation().setType(location.getType());
            dtoObservationRsp.getDtoNestRsp().getNestType().setType(nestType.getType());
        }
        log.info("Observation with id = {} created successfully", dtoObservationRsp.getId());
        return dtoObservationRsp;
    }

    /**
     * Method for deleting observation with @param id.
     *
     * @param id
     */
    @Override
    public void deleteObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            obsJpaRepository.deleteById(id);
            log.info("Observations with id = " + id + " deleted successfully");
        } else {
            throw new ResourceNotFoundException("Observation with id " + id + " not found");
        }
    }

    /**
     * Method for finding observation with @param id. Returning object is DtoObservationRsp.
     *
     * @param id
     * @return
     */
    @Override
    public DtoObservationRsp findObservationById(UUID id) {
        if (obsJpaRepository.existsById(id)) {
            Observation obsReferencedById = obsJpaRepository.getReferenceById(id);
            DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(obsReferencedById, obsReferencedById.getNest());
            if (obsReferencedById.getPlaceId() != null) {
                dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(obsReferencedById.getPlaceId()));
            }
            log.info("Observation with id = " + id + " found successfully");
            return dtoObservationRsp;
        } else {
            throw new ResourceNotFoundException("Observation with id " + id + " was not found");
        }
    }

    /**
     * Method for updating observation with @param id.
     * New observation is transmitted in @param dtoObservationRq.
     *
     * @param dtoObservationRq
     * @param id
     * @return
     */
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
                nestJpaRepository.deleteById(nestId);
            }
            DtoObservationRsp dtoObservationRsp = findObservationById(updatedObs.getId());
            dtoObservationRsp.setCreatedAt(obsCreatedAt);
            log.info("Observation with id = " + id + " updated successfully");
            return dtoObservationRsp;
        } else {
            throw new ResourceNotFoundException("Observation with id " + id + " not found");
        }
    }

    /**
     * Method for get all observations from DB. Observations are returned as List<DtoObservationRsp>.
     *
     * @return
     */
    @Override
    public List<DtoObservationRsp> getAllObservation() {
        List<Observation> listObsEntity = obsJpaRepository.findAll();

        List<DtoObservationRsp> listDtoObsRsp = new ArrayList<>();
        for (Observation observation : listObsEntity) {
            listDtoObsRsp.add(observationMapper.entityToDto(observation, observation.getNest()));
        }

        Set<UUID> placeIdSet = new HashSet<>(createSetOfPlaceId(listObsEntity));
        if (!placeIdSet.isEmpty()) {
            List<PlaceDtoResp> listPlaceDtoResp = placeClient.getPlacesByIdList(placeIdSet);
            for (int i = 0; i < listDtoObsRsp.size(); i++) {
                for (PlaceDtoResp placeDtoResp : listPlaceDtoResp) {
                    if (listObsEntity.get(i).getPlaceId().equals(placeDtoResp.getId())) {
                        listDtoObsRsp.get(i).setPlaceDtoResp(placeDtoResp);
                        break;
                    }
                }
            }
        }
        log.info("Observations from DB found successfully");
        return listDtoObsRsp;
    }

    /**
     * Method for creating a Set (HashSet) of UUID of places from @param listEntity of observations.
     * Returning Set<UUID> placeIdSet.
     *
     * @param listEntity
     * @return
     */
    public Set<UUID> createSetOfPlaceId(@NotNull List<Observation> listEntity) {
        Set<UUID> placeIdSet = new HashSet<>();
        for (Observation observation : listEntity) {
            if (observation.getPlaceId() != null) {
                placeIdSet.add(observation.getPlaceId());
            }
        }
        return placeIdSet;
    }

    @Override
    public Page<DtoObservationRsp> getObservationsWithSortingAndFiltration(ObservationPage observationPage,
                                                                           ObservationSearchCriteria observationSearchCriteria) {
        return observationCriteriaRepository.findAllWithFilters(observationPage, observationSearchCriteria);
    }
}
