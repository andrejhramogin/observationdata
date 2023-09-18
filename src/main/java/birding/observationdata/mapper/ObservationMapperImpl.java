package birding.observationdata.mapper;

import birding.observationdata.dto.observation.request.DtoObservationRq;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.service.placemanager.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObservationMapperImpl implements ObservationMapper {
    @Autowired
    PlaceService placeService;
    @Override
    public Observation dtoToEntity(DtoObservationRq obsRq) {
        if ( obsRq == null ) {
            return null;
        }

        Observation observation = new Observation();

        observation.setDate( obsRq.getDate() );
        observation.setQuantity( obsRq.getQuantity() );
        observation.setDescription( obsRq.getDescription() );
        observation.setNest( obsRq.getNest() );
        observation.setSpeciesId( obsRq.getSpeciesId() );
        observation.setUserId( obsRq.getUserId() );
        observation.setPlaceId( obsRq.getPlaceId() );
        observation.setCreatedAt( obsRq.getCreatedAt() );
        observation.setModifiedAt( obsRq.getModifiedAt() );

        return observation;
    }

    @Override
    public DtoObservationRsp entityToDto(Observation observation) {
        if ( observation == null ) {
            return null;
        }

        DtoObservationRsp dtoObservationRsp = new DtoObservationRsp();

        dtoObservationRsp.setId( observation.getId() );
        dtoObservationRsp.setDate( observation.getDate() );
        dtoObservationRsp.setQuantity( observation.getQuantity() );
        dtoObservationRsp.setDescription( observation.getDescription() );
        dtoObservationRsp.setNest( observation.getNest() );
        dtoObservationRsp.setSpeciesId( observation.getSpeciesId() );
        dtoObservationRsp.setUserId( observation.getUserId() );
        dtoObservationRsp.setDtoPlaceRsp(placeService.getPlaceById(observation.getPlaceId()));
        dtoObservationRsp.setCreatedAt( observation.getCreatedAt() );
        dtoObservationRsp.setModifiedAt( observation.getModifiedAt() );

        return dtoObservationRsp;
    }

    @Override
    public List<DtoObservationRsp> listEntityToDto(List<Observation> list) {
        if ( list == null ) {
            return null;
        }

        List<DtoObservationRsp> list1 = new ArrayList<DtoObservationRsp>( list.size() );
        for ( Observation observation : list ) {
            list1.add( entityToDto( observation ) );
        }

        return list1;
    }
}
