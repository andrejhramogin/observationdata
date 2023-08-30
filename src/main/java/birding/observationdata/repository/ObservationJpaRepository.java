package birding.observationdata.repository;

import birding.observationdata.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ObservationJpaRepository extends JpaRepository <Observation, UUID>{

}
