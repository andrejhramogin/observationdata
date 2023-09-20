package birding.observationdata.mapper;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObservationMapper {

    Observation dtoToEntity(DtoObservationRq obsRq);

    DtoObservationRsp entityToDto(Observation observation);

    List<DtoObservationRsp> listEntityToDto(List<Observation> list);
}
