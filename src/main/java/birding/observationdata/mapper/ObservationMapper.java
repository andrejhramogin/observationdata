package birding.observationdata.mapper;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.entity.Observation;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObservationMapper {
@Mapping(target = "nest", source = "dtoNestRq")
@Mapping(target = "description", source = "obsRq.description")
@Mapping(target = "createdAt", source = "obsRq.createdAt")
@Mapping(target = "modifiedAt", source = "obsRq.modifiedAt")
    Observation dtoToEntity(DtoObservationRq obsRq, DtoNestRq dtoNestRq);

@Mapping(target = "dtoNestRsp", source = "nest")
@Mapping(target = "id", source = "observation.id")
@Mapping(target = "description", source = "observation.description")
@Mapping(target = "createdAt", source = "observation.createdAt")
@Mapping(target = "modifiedAt", source = "observation.modifiedAt")
    DtoObservationRsp entityToDto(Observation observation, Nest nest);

    List<DtoObservationRsp> listEntityToDto(List<Observation> list);
}
