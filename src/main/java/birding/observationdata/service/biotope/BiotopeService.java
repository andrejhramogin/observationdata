package birding.observationdata.service.biotope;

import birding.observationdata.entity.Biotope;

import java.util.UUID;

public interface BiotopeService {
    Biotope findBiotopeById(UUID id);
}
