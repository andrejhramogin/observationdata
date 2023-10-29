package birding.observationdata.controller;

import birding.observationdata.entity.Observation;
import birding.observationdata.repository.ObservationJpaRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class with PostgreSQLContainer (Testcontainer) for testing ObservationController.class
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ExtendWith(MockitoExtension.class)
public class ObservationControllerTest {

    @Autowired
    private ObservationJpaRepository observationJpaRepository;
    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(@NotNull DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        observationJpaRepository.deleteAll();
    }

    /**
     * Test to check if postgres is running
     */
    @Test
    void isPostgresStart() {
        System.out.println("Postgres isRunning +++++++++++===============");
        assertThat(postgres.isRunning()).isTrue();
    }

    /**
     * Test for @GetMapping("/observation"): get all observations.
     * Expected status 200.
     */
    @Test
    void shouldGetAllObservation() {

        Observation observation_1 = new Observation();
        observation_1.setDate(LocalDate.of(2023, 10, 2));
        observation_1.setNest(null);
        observation_1.setQuantity(5);
        observation_1.setDescription("description1");
        observation_1.setSpeciesId(UUID.fromString("4bcda65c-57f1-4759-a2ca-fa38efa17bbe"));
        observation_1.setUserId(UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024"));

        Observation observation_2 = new Observation();
        observation_2.setDate(LocalDate.of(2023, 9, 18));
        observation_2.setNest(null);
        observation_2.setQuantity(1);
        observation_2.setDescription("description2");
        observation_2.setSpeciesId(UUID.fromString("4bcda65c-57f1-4759-a2ca-fa38efa17bbe"));
        observation_2.setUserId(UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024"));

        List<Observation> observations = new ArrayList<>();
        observations.add(observation_1);
        observations.add(observation_2);

        observationJpaRepository.saveAll(observations);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/observation")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }

    /**
     * Test for @GetMapping("/observation/{id}"): get observation by id.
     * Expected status 200.
     *
     * @throws Exception
     */

    @Test
    void shouldGetObservationById() throws Exception {

        Observation testObservation = createAndPostToDbTestObservation();

        UUID uuidOfTestObservation = testObservation.getId();

        mockMvc.perform(
                        get("/observation/{id}", uuidOfTestObservation))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.description").value("observation description"))
                .andExpect(jsonPath("$.userId").value("6a61b1f4-7dcd-4b79-a344-5f246fabe024"))
                .andExpect(jsonPath(("$.id")).value(String.valueOf(uuidOfTestObservation)));
    }

    /**
     * Test for fail @GetMapping("/observation/{id}"): id does not exist.
     * Expected status 404.
     *
     * @throws Exception
     */
    @Test
    public void shouldGetObservationByIdFail() throws Exception {
        UUID uuidNotExisting = UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024");
        mockMvc.perform(
                        get("/observation/{id}", uuidNotExisting))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    /**
     * Test for @DeleteMapping("/observation/{id}"): deleting observation by id.
     * Expected status 200.
     *
     * @throws Exception
     */
    @Test
    public void shouldDeleteObservationByID() throws Exception {
        Observation testObservation = createAndPostToDbTestObservation();
        mockMvc.perform(
                        delete("/observation/{id}", testObservation.getId()))
                .andExpect(status().isOk());
    }

    /**
     * Test for fail @DeleteMapping("/observation/{id}"): id does not exist. Expected status 404.
     *
     * @throws Exception
     */
    @Test
    public void shouldDeleteObservationByIDFail() throws Exception {
        UUID uuidNotExisting = UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024");
        mockMvc.perform(
                        delete("/observation/{id}", uuidNotExisting))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    /**
     * Test for  @PostMapping("/observation"): observation creation  and posting it to the DB.
     * Expected status 200.
     *
     * @throws Exception
     */
    @Test
    void shouldPostObservation() throws Exception {

        var requestBuilder = post("/observation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "date":"2023-10-02",
                            "quantity":5,
                            "description":"observation description",
                            "dtoNestRq":{
                                "eggsQuantity": 10,
                                "chicksQuantity": 10,
                                "description":"nest description",
                                "biotope": {
                                    "id": "6a61b1f4-7dcd-4b79-a344-5f246fabe024"
                                    },
                                "location": {
                                    "id": "4bcda65c-57f1-4759-a2ca-fa38efa17bbe"
                                    },
                                "nestType": {
                                    "id": "d15f9237-187a-4c0f-9dff-a405b88716b5"
                                    },
                                "nestDimension": null
                                },
                            "speciesId":"4bcda65c-57f1-4759-a2ca-fa38efa17bbe",
                            "userId":"6a61b1f4-7dcd-4b79-a344-5f246fabe024"
                        }
                        """);

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "description": "observation description",
                                    "speciesId": "4bcda65c-57f1-4759-a2ca-fa38efa17bbe",
                                    "userId":"6a61b1f4-7dcd-4b79-a344-5f246fabe024"
                                }
                                """),
                        jsonPath("$.id").exists()
                );
    }

    /**
     * Test for @PutMapping("/observation/{id}"): Updates the observation with the id number and returns it from the DB.
     * Expected status 200.
     *
     * @throws Exception
     */
    @Test
    void shouldUpdateObservation() throws Exception {
        Observation testObservation = createAndPostToDbTestObservation();
        UUID idOfTestObservation = testObservation.getId();

        var requestBuilder = put("/observation/{id}", idOfTestObservation)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "date":"2023-10-03",
                            "quantity":7,
                            "description":"PUT observation description PUT",
                            "dtoNestRq":{
                                "eggsQuantity": 10,
                                "chicksQuantity": 10,
                                "description":"nest description",
                                "biotope": {
                                    "id": "6a61b1f4-7dcd-4b79-a344-5f246fabe024"
                                    },
                                "location": {
                                    "id": "4bcda65c-57f1-4759-a2ca-fa38efa17bbe"
                                    },
                                "nestType": {
                                    "id": "d15f9237-187a-4c0f-9dff-a405b88716b5"
                                    },
                                "nestDimension": null
                                },
                            "speciesId":"6a61b1f4-7dcd-4b79-a344-5f246fabe024",
                            "userId":"4bcda65c-57f1-4759-a2ca-fa38efa17bbe"
                        }
                        """);
        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                "quantity":7,
                                "description":"PUT observation description PUT",
                                "speciesId":"6a61b1f4-7dcd-4b79-a344-5f246fabe024",
                                "userId":"4bcda65c-57f1-4759-a2ca-fa38efa17bbe"
                                }
                                """),
                        jsonPath("$.id").value(String.valueOf(idOfTestObservation))
                );
    }

    /**
     * Test for fail @PutMapping("/observation/{id}"): id does not exist.
     * Expected status 404.
     *
     * @throws Exception
     */
    @Test
    public void shouldUpdateObservationFail_IdNotExist() throws Exception {
        UUID uuidNotExisting = UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024");
        var requestBuilder = put("/observation/{id}", uuidNotExisting)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "date":"2023-10-03",
                            "quantity":7,
                            "description":"PUT observation description PUT",
                            "dtoNestRq":null,
                            "speciesId":"6a61b1f4-7dcd-4b79-a344-5f246fabe024",
                            "userId":"4bcda65c-57f1-4759-a2ca-fa38efa17bbe",
                            "placeId":"7cab8008-2b37-4123-a712-ad5914b60465"
                        }
                        """);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    /**
     * Helper method for observation creating and posting to the database.
     *
     * @return
     */
    private @NotNull Observation createAndPostToDbTestObservation() {

        Observation observation = new Observation();

        observation.setDate(LocalDate.of(2023, 10, 2));
        observation.setNest(null);
        observation.setQuantity(5);
        observation.setDescription("observation description");
        observation.setSpeciesId(UUID.fromString("4bcda65c-57f1-4759-a2ca-fa38efa17bbe"));
        observation.setUserId(UUID.fromString("6a61b1f4-7dcd-4b79-a344-5f246fabe024"));

        return observationJpaRepository.save(observation);
    }
}