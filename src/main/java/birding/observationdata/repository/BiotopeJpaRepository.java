package birding.observationdata.repository;

import birding.observationdata.entity.Biotope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BiotopeJpaRepository extends JpaRepository<Biotope, UUID> {
}
