package birding.observationdata.dto.mapper;

import birding.observationdata.dto.request.DtoObservationRq;
import birding.observationdata.dto.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObservationMapper {

    Observation dtoToEntity(DtoObservationRq obsRq);

    DtoObservationRsp entityToDto(Observation observation);
}
