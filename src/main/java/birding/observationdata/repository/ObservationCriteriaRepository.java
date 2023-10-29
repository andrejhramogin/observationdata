package birding.observationdata.repository;

import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.entity.ObservationPage;
import birding.observationdata.entity.ObservationSearchCriteria;
import birding.observationdata.integration.place.PlaceClient;
import birding.observationdata.mapper.ObservationMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository
public class ObservationCriteriaRepository {
    @Autowired
    private ObservationMapper observationMapper;
    @Autowired
    private PlaceClient placeClient;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ObservationCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<DtoObservationRsp> findAllWithFilters(ObservationPage observationPage,
                                                      ObservationSearchCriteria observationSearchCriteria){
        CriteriaQuery<Observation> criteriaQuery = criteriaBuilder.createQuery(Observation.class);
        Root<Observation> observationRoot = criteriaQuery.from(Observation.class);
        Predicate predicate = getPredicate(observationSearchCriteria, observationRoot);
        criteriaQuery.where(predicate);
        setOrder(observationPage, criteriaQuery, observationRoot);

        TypedQuery<Observation> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(observationPage.getPageNumber()*observationPage.getPageSize());
        typedQuery.setMaxResults(observationPage.getPageSize());

        Pageable pageable = getPageable(observationPage);
        long observationCount = getObservationsCount(predicate);

        List<DtoObservationRsp> dtoObservationRspList = new ArrayList<>();
        for (Observation observation : typedQuery.getResultList()) {
            DtoObservationRsp dtoObservationRsp = observationMapper.entityToDto(observation, observation.getNest());
            if(observation.getPlaceId()!=null) {
                dtoObservationRsp.setPlaceDtoResp(placeClient.getPlaceById(observation.getPlaceId()));
            }
            dtoObservationRspList.add(dtoObservationRsp);
        }
        return new PageImpl<>(dtoObservationRspList, pageable, observationCount);
    }

    private Predicate getPredicate(ObservationSearchCriteria observationSearchCriteria,
                                   Root<Observation> observationRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(observationSearchCriteria.getDate())){
            predicates.add(
                    criteriaBuilder.equal(observationRoot.get("date"),
                            observationSearchCriteria.getDate())
            );
        }
        if(Objects.nonNull(observationSearchCriteria.getSpeciesId())){
            predicates.add(
                    criteriaBuilder.equal(observationRoot.get("speciesId"),
                            observationSearchCriteria.getSpeciesId())
            );
        }
        if(Objects.nonNull(observationSearchCriteria.getUserId())){
            predicates.add(
                    criteriaBuilder.equal(observationRoot.get("userId"),
                            observationSearchCriteria.getUserId())
            );
        }
        if(Objects.nonNull(observationSearchCriteria.getPlaceId())){
            predicates.add(
                    criteriaBuilder.equal(observationRoot.get("placeId"),
                            observationSearchCriteria.getPlaceId())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    private void setOrder(ObservationPage observationPage,
                          CriteriaQuery<Observation> criteriaQuery,
                          Root<Observation> observationRoot) {
        if(observationPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(observationRoot.get(observationPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(observationRoot.get(observationPage.getSortBy())));
        }
    }
    private Pageable getPageable(ObservationPage observationPage) {
        Sort sort = Sort.by(observationPage.getSortDirection(), observationPage.getSortBy());
        return PageRequest.of(observationPage.getPageNumber(), observationPage.getPageSize(), sort);
    }
    private long getObservationsCount(Predicate predicate){
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Observation>countRoot = countQuery.from(Observation.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
