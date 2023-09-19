package birding.observationdata.service.observation;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.mapper.ObservationMapper;
import birding.observationdata.repository.ObservationJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObservationServiceImplTest {

    @Mock
    private ObservationMapper mapper;
    @Mock
    private ObservationJpaRepository observationJpaRepository;
    @InjectMocks
    private ObservationServiceImpl service;

    @Test
    void createNewObservation() {

        DtoObservationRq dtoObservationRq = new DtoObservationRq();
        dtoObservationRq.setDate(LocalDate.of(2023, 9, 18));
        dtoObservationRq.setNest(null);
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
        dtoObservationRsp.setNest(null);
        dtoObservationRsp.setQuantity(5);
        dtoObservationRsp.setDescription("description");

        when(mapper.dtoToEntity(dtoObservationRq)).thenReturn(observation);
        when(observationJpaRepository.save(observation)).thenReturn(observation);
        when(mapper.entityToDto(observation)).thenReturn(dtoObservationRsp);

        DtoObservationRsp actualResult = service.createNewObservation(dtoObservationRq);

        int expectedQuantity = 5;
        LocalDate expectedDate = LocalDate.of(2023, 9, 18);
        String expectedDescription = "description";
        Nest expectedNest = null;

        assertEquals(expectedQuantity, actualResult.getQuantity());
        assertEquals(expectedDate, actualResult.getDate());
        assertEquals(expectedDescription, actualResult.getDescription());
        assertEquals(expectedNest, actualResult.getNest());
    }

//    public void deleteObservationById(UUID id) {
//        if (obsJpaRepository.existsById(id)) {
//            obsJpaRepository.deleteById(id);
//        }
//        throw new ResourceNotFoundException("Observation with id " + id + " not found");
//    }
    @Test
    void deleteObservationById() {
        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        when(observationJpaRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteObservationById(testUUID));
    }

    /**
     * Test that checks the correctness of searching for an observation by id
     * @throws Exception
     */
    @Test
    void findObservationById() throws Exception {

        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        Observation observation = new Observation();
        observation.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        observation.setDate(LocalDate.of(2023, 9, 18));
        observation.setNest(null);
        observation.setQuantity(5);
        observation.setDescription("description");

        DtoObservationRsp dtoObservationRsp = new DtoObservationRsp();
        dtoObservationRsp.setId(UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016"));
        dtoObservationRsp.setDate(LocalDate.of(2023, 9, 18));
        dtoObservationRsp.setNest(null);
        dtoObservationRsp.setQuantity(5);
        dtoObservationRsp.setDescription("description");

        when(observationJpaRepository.existsById(testUUID)).thenReturn(true);
        when(observationJpaRepository.getReferenceById(testUUID)).thenReturn(observation);
        when(mapper.entityToDto(observation)).thenReturn(dtoObservationRsp);

        DtoObservationRsp actualResult = service.findObservationById(testUUID);

//         verify(observationJpaRepository);

        UUID expectedUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");
        int expectedQuantity = 5;
        LocalDate expectedDate = LocalDate.of(2023, 9, 18);
        String expectedDescription = "description";
        Nest expectedNest = null;

        assertEquals(expectedUUID, actualResult.getId());
        assertEquals(expectedQuantity, actualResult.getQuantity());
        assertEquals(expectedDate, actualResult.getDate());
        assertEquals(expectedDescription, actualResult.getDescription());
        assertEquals(expectedNest, actualResult.getNest());
    }

    /**
     *Test checking for incorrect observation search by id (existById == null)
     * @throws Exception
     */
    @Test
    void findObservationById_fail() throws Exception {

        UUID testUUID = UUID.fromString("b22e8db0-470a-4078-9f53-e0ffe2476016");

        when(observationJpaRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.findObservationById(testUUID));
    }

    @Test
    void updateObservation() {
    }

    @Test
    void getAllObservation() {
    }
}