package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.repository.NestJpaRepository;
import birding.observationdata.repository.ObservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NestServiceImpl implements NestService{
    @Autowired
    NestJpaRepository nestJpaRepository;
    @Autowired
    NestMapper mapper;

    public NestServiceImpl(NestJpaRepository nestJpaRepository){
        this.nestJpaRepository = nestJpaRepository;
    }
    public NestServiceImpl(){}

    @Override
    public DtoNestRsp createNewNest(DtoNestRq dto) {
        Nest newNest = nestJpaRepository.save(mapper.dtoToEntity(dto));
        return findNestById(newNest.getId());
    }

    @Override
    public DtoNestRsp findNestById(int id) {
        return mapper.entityToDto(nestJpaRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }

    @Override
    public List<DtoNestRsp> getAllNest() {
        return null;
    }
}
