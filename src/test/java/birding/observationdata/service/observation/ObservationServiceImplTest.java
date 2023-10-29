package birding.observationdata.service.observation;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.integration.place.PlaceClient;
import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.repository.ObservationJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ObservationServiceImplTest {
    @Mock
    private ObservationMapper observationMapper;
    @Mock
    private ObservationJpaRepository observationJpaRepository;
    @Mock
    private PlaceClient placeClient;
    @InjectMocks
    private ObservationServiceImpl observationService;

    /**
     * Test for method: DtoObservationRsp createNewObservation(DtoObservationRq dtoObservationRq)
     * Test that checks the correctness of creation of new observation.
     */
    @Test
    void createNewObservation() {

        DtoObservationRq dtoObservationRq = new DtoObservationRq();
        DtoNestRq dtoNestRq = dtoObservationRq.getDtoNestRq();
        dtoObservationRq.setDate(LocalDate.of(2023, 9, 18));
        dtoObservationRq.setDtoNestRq(null);
        dtoObservationRq.setQuantity(5);
        dtoObservationRq.setDescription("description");

        Observation observation = new Observation();
        observation.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        observation.setDate(LocalDate.of(2023, 9, 18));
        observation.setNest(null);
        observation.setQuantity(5);
        observation.setDescription("description");

        DtoObservationRsp dtoObservationRsp = new DtoObservationRsp();
        dtoObservationRsp.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        dtoObservationRsp.setDate(LocalDate.of(2023, 9, 18));
        dtoObservationRsp.setDtoNestRsp(null);
        dtoObservationRsp.setQuantity(5);
        dtoObservationRsp.setDescription("description");

        when(observationMapper.dtoToEntity(dtoObservationRq, dtoNestRq)).thenReturn(observation);
        when(observationJpaRepository.save(observation)).thenReturn(observation);
        when(observationMapper.entityToDto(observation, observation.getNest())).thenReturn(dtoObservationRsp);

        DtoObservationRsp actualResult = observationService.createNewObservation(dtoObservationRq);

        int expectedQuantity = 5;
        LocalDate expectedDate = LocalDate.of(2023, 9, 18);
        String expectedDescription = "description";
        DtoNestRsp expectedNest = null;

        assertEquals(expectedQuantity, actualResult.getQuantity());
        assertEquals(expectedDate, actualResult.getDate());
        assertEquals(expectedDescription, actualResult.getDescription());
        assertEquals(expectedNest, actualResult.getDtoNestRsp());
    }

    /**
     * Test for method: void deleteObservationById(UUID id)
     */
    @Test
    void deleteObservationById() {
        UUID uuid = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        when(observationJpaRepository.existsById(uuid)).thenReturn(true);
        observationService.deleteObservationById(uuid);
        verify(observationJpaRepository).deleteById(uuid);
    }

    /**
     * Test for method: void deleteObservationById(UUID id)
     * A test that tests the case where an observation with a given id does not exist (existsById == null).
     */
    @Test
    void deleteObservationByIdFail() {
        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        when(observationJpaRepository.existsById(testUUID)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> observationService.deleteObservationById(testUUID));
    }

    /**
     * Test for method: DtoObservationRsp findObservationById(UUID id)
     * Test that checks the correctness of searching for an observation by id.
     */
    @Test
    void findObservationById() {

        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        Nest nest = new Nest();
        nest.setId(UUID.fromString("4bcda65c-57f1-4759-a2ca-fa38efa17bbe"));

        DtoNestRsp dtoNestRsp = new DtoNestRsp();
        dtoNestRsp.setId(UUID.fromString("4bcda65c-57f1-4759-a2ca-fa38efa17bbe"));

        Observation observation = new Observation();
        observation.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        observation.setDate(LocalDate.of(2023, 9, 18));
        observation.setNest(nest);
        observation.setQuantity(5);
        observation.setDescription("description");
        observation.setPlaceId(UUID.fromString("9c154905-5b6d-4bad-96e5-317445a76366"));

        DtoObservationRsp dtoObservationRsp = new DtoObservationRsp();
        dtoObservationRsp.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        dtoObservationRsp.setDate(LocalDate.of(2023, 9, 18));
        dtoObservationRsp.setDtoNestRsp(dtoNestRsp);
        dtoObservationRsp.setQuantity(5);
        dtoObservationRsp.setDescription("description");

        when(observationJpaRepository.existsById(testUUID)).thenReturn(true);
        when(observationJpaRepository.getReferenceById(testUUID)).thenReturn(observation);
        when(observationMapper.entityToDto(observation, nest)).thenReturn(dtoObservationRsp);
        when(placeClient.getPlaceById(observation.getPlaceId())).thenReturn(dtoObservationRsp.getPlaceDtoResp());

        DtoObservationRsp actualResult = observationService.findObservationById(testUUID);

        verify(observationJpaRepository).existsById(testUUID);
        verify(observationJpaRepository).getReferenceById(testUUID);
        verify(observationMapper).entityToDto(observation, nest);
        verify(placeClient).getPlaceById(observation.getPlaceId());

        UUID expectedUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        int expectedQuantity = 5;
        LocalDate expectedDate = LocalDate.of(2023, 9, 18);
        String expectedDescription = "description";

        assertEquals(expectedUUID, actualResult.getId());
        assertEquals(expectedQuantity, actualResult.getQuantity());
        assertEquals(expectedDate, actualResult.getDate());
        assertEquals(expectedDescription, actualResult.getDescription());
        assertEquals(dtoNestRsp, actualResult.getDtoNestRsp());
    }

    /**
     * Test for method: DtoObservationRsp findObservationById(UUID id).
     * Test checking for incorrect observation search by id (existById == false).
     */
    @Test
    void findObservationById_fail() {

        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        when(observationJpaRepository.existsById(testUUID)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> observationService.findObservationById(testUUID));
    }

    /**
     * Test for method: DtoObservationRsp updateObservation(DtoObservationRq dtoObservationRq, UUID id).
     * The test verifies that the observation is updated correctly.
     */
    @Test
    void updateObservation() {

        UUID updatedObsId = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        Observation updatedObservation = new Observation();
        updatedObservation.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        updatedObservation.setDate(LocalDate.of(2023, 10, 1));
        updatedObservation.setQuantity(5);
        updatedObservation.setDescription("description");

        DtoObservationRq dtoNewObservationRq = new DtoObservationRq();
        dtoNewObservationRq.setDate(LocalDate.of(2023, 10, 11));
        dtoNewObservationRq.setQuantity(1);
        dtoNewObservationRq.setDescription("new description");

        Observation newObservation = new Observation();
        newObservation.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        newObservation.setDate(LocalDate.of(2023, 10, 11));
        newObservation.setQuantity(1);
        newObservation.setDescription("new description");

        DtoObservationRsp dtoNewObservationRsp = new DtoObservationRsp();
        dtoNewObservationRsp.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        dtoNewObservationRsp.setDate(LocalDate.of(2023, 10, 11));
        dtoNewObservationRsp.setQuantity(1);
        dtoNewObservationRsp.setDescription("new description");

        when(observationJpaRepository.existsById(updatedObsId)).thenReturn(true);
        when(observationJpaRepository.getReferenceById(updatedObsId)).thenReturn(updatedObservation);
        when(observationMapper.dtoToEntity(dtoNewObservationRq, null)).thenReturn(newObservation);
        when(observationService.findObservationById(updatedObsId)).thenReturn(dtoNewObservationRsp);

        DtoObservationRsp actualResult = observationService.updateObservation(dtoNewObservationRq, updatedObsId);

        UUID expectedUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        int expectedQuantity = 1;
        LocalDate expectedDate = LocalDate.of(2023, 10, 11);
        String expectedDescription = "new description";

        assertEquals(expectedUUID, actualResult.getId());
        assertEquals(expectedQuantity, actualResult.getQuantity());
        assertEquals(expectedDate, actualResult.getDate());
        assertEquals(expectedDescription, actualResult.getDescription());
    }

    /**
     * Test for method: DtoObservationRsp updateObservation(DtoObservationRq dtoObservationRq, UUID id).
     * A test that tests the case where an observation with a given id does not exist (existsById == null).
     */
    @Test
    void updateObservationByIdFail_idNotExist() {
        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        DtoObservationRq dtoNewObservationRq = new DtoObservationRq();
        dtoNewObservationRq.setDate(LocalDate.of(2023, 10, 11));
        dtoNewObservationRq.setQuantity(1);
        dtoNewObservationRq.setDescription("new description");

        when(observationJpaRepository.existsById(testUUID)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class,
                () -> observationService.updateObservation(dtoNewObservationRq, testUUID));
    }

    /**
     * Test for method: List<DtoObservationRsp> getAllObservation()
     * Test that checks the correctness of searching of all observations.
     */
    @Test
    void getAllObservation() {
        UUID testUUID_1 = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        UUID testUUID_2 = UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024");

        Observation observation_1 = new Observation();
        observation_1.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        observation_1.setDate(LocalDate.of(2023, 10, 6));
        observation_1.setQuantity(5);
        observation_1.setDescription("description_1");

        Observation observation_2 = new Observation();
        observation_2.setId(UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024"));
        observation_2.setDate(LocalDate.of(2023, 10, 5));
        observation_2.setQuantity(5);
        observation_2.setDescription("description_2");

        List<Observation> observationList = new ArrayList<>();
        observationList.add(observation_1);
        observationList.add(observation_2);

        DtoObservationRsp dtoObservationRsp_1 = new DtoObservationRsp();
        dtoObservationRsp_1.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        dtoObservationRsp_1.setDate(LocalDate.of(2023, 10, 6));
        dtoObservationRsp_1.setQuantity(5);
        dtoObservationRsp_1.setDescription("description_1");

        DtoObservationRsp dtoObservationRsp_2 = new DtoObservationRsp();
        dtoObservationRsp_2.setId(UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024"));
        dtoObservationRsp_2.setDate(LocalDate.of(2023, 10, 5));
        dtoObservationRsp_2.setQuantity(5);
        dtoObservationRsp_2.setDescription("description_2");

        List<DtoObservationRsp> dtoObservationRspList = new ArrayList<>();
        dtoObservationRspList.add(dtoObservationRsp_1);
        dtoObservationRspList.add(dtoObservationRsp_2);

        when(observationJpaRepository.findAll()).thenReturn(observationList);
        when(observationMapper.entityToDto(observation_1, observation_1.getNest())).thenReturn(dtoObservationRsp_1);
        when(observationMapper.entityToDto(observation_2, observation_2.getNest())).thenReturn(dtoObservationRsp_2);

        List<DtoObservationRsp> actualResultList = observationService.getAllObservation();

        verify(observationJpaRepository).findAll();
        verify(observationMapper).entityToDto(observation_1, observation_1.getNest());
        verify(observationMapper).entityToDto(observation_2, observation_2.getNest());

        assertEquals(dtoObservationRspList.size(), actualResultList.size());
        assertEquals(testUUID_1, actualResultList.get(0).getId());
        assertEquals(testUUID_2, actualResultList.get(1).getId());
        assertEquals(dtoObservationRspList.get(0).getDescription(), actualResultList.get(0).getDescription());
        assertEquals(dtoObservationRspList.get(0).getDate(), actualResultList.get(0).getDate());
    }

    /**
     * Test for method: Set<UUID> createSetOfPlaceId(@NotNull List<Observation> listEntity)
     * Test that checks the correctness of creating Set of placeId from an observation.
     */
    @Test
    void createSetOfPlaceId() {
        List<Observation> listEntity = new ArrayList<>();
        Observation observation_1 = new Observation();
        Observation observation_2 = new Observation();

        observation_1.setPlaceId(UUID.fromString("f79a34bd-8950-4701-acbd-96f6311a981d"));
        observation_2.setPlaceId(UUID.fromString("6b6c58f1-e849-4b3e-98a8-a74ccf44189c"));

        listEntity.add(observation_1);
        listEntity.add(observation_2);

        Set<UUID> expectedSet = new HashSet<>();
        expectedSet.add(UUID.fromString("f79a34bd-8950-4701-acbd-96f6311a981d"));
        expectedSet.add(UUID.fromString("6b6c58f1-e849-4b3e-98a8-a74ccf44189c"));

        Set<UUID> actualSet;
        actualSet = observationService.createSetOfPlaceId(listEntity);

        assertEquals(expectedSet, actualSet);
    }
}