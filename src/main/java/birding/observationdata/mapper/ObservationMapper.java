package birding.observationdata.mapper;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObservationMapper {

    Observation dtoToEntity(DtoObservationRq obsRq);

    DtoObservationRsp entityToDto(Observation observation);
}
