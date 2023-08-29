package birding.observationdata.repository;

import birding.observationdata.entity.Nest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NestJpaRepository extends JpaRepository<Nest, UUID> {
}
