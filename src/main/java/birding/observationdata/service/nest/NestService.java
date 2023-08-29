package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;

import java.util.List;
import java.util.UUID;

public interface NestService {
    DtoNestRsp createNewNest(DtoNestRq nestRq);
    DtoNestRsp findNestById(UUID id);
    List<DtoNestRsp> getAllNest();
}

