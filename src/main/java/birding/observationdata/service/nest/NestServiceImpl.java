package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.repository.NestJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NestServiceImpl implements NestService{
    @Autowired
    private NestJpaRepository nestJpaRepository;
    @Autowired
    private NestMapper mapper;

    @Override
    public DtoNestRsp createNewNest(DtoNestRq dto) {
        return mapper.entityToDto(nestJpaRepository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public DtoNestRsp findNestById(UUID id) {
        return mapper.entityToDto(nestJpaRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Observation with id " + id + " not found")));
    }

    @Override
    public List<DtoNestRsp> getAllNest() {
        return null;
    }
}
