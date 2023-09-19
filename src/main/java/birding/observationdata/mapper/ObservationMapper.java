package birding.observationdata.mapper;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.dto.place.response.DtoPlaceRsp;
import birding.observationdata.entity.Observation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

//@Mapper(componentModel = "spring")
public interface ObservationMapper {

    Observation dtoToEntity(DtoObservationRq obsRq);

//    @Mapping(target = "dtoPlaceRsp", expression = "java(dtoPlaceRsp)")
//    @Mapping(target = "id", expression = "java(observation.getId())")
    DtoObservationRsp entityToDto(Observation observation);

    List<DtoObservationRsp> listEntityToDto(List<Observation> list);
}
