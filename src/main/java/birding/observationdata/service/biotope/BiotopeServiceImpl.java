package birding.observationdata.service.biotope;

import birding.observationdata.entity.Biotope;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.repository.BiotopeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BiotopeServiceImpl implements BiotopeService {
    @Autowired
    private BiotopeJpaRepository biotopeJpaRepository;

    @Override
    public Biotope findBiotopeById(UUID id) {
        return biotopeJpaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Biotope with id " + id + " not found"));
    }
}
