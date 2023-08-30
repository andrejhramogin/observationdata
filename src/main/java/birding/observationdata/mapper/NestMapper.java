package birding.observationdata.mapper;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.entity.Nest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NestMapper {
    Nest dtoToEntity(DtoNestRq nestRq);

    DtoNestRsp entityToDto(Nest nest);
}
