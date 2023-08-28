package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;

import java.util.List;

public interface NestService {
    DtoNestRsp createNewNest(DtoNestRq nestRq);
    DtoNestRsp findNestById(int id);
    List<DtoNestRsp> getAllNest();
}

