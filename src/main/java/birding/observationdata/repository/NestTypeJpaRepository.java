package birding.observationdata.repository;

import birding.observationdata.entity.NestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NestTypeJpaRepository extends JpaRepository<NestType, UUID> {
}
