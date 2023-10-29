package birding.observationdata.repository;

import birding.observationdata.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, UUID> {
}
