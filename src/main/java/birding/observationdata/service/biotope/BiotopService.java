package birding.observationdata.service.biotope;

import birding.observationdata.entity.Biotope;

import java.util.UUID;

public interface BiotopService {
    Biotope findBiotopeById(UUID id);
}
