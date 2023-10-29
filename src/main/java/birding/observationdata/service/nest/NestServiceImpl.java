package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.dto.observation.response.DtoObservationRsp;
import birding.observationdata.entity.Observation;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.repository.NestJpaRepository;
import birding.observationdata.service.biotope.BiotopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NestServiceImpl implements NestService {
    @Autowired
    private NestJpaRepository nestJpaRepository;
    @Autowired
    private NestMapper mapper;
    @Autowired
    BiotopeService biotopeService;

    @Override
    public DtoNestRsp createNewNest(DtoNestRq dto) {
        return mapper.entityToDto(nestJpaRepository.save(mapper.dtoToEntity(dto)));
    }

    public DtoNestRsp findNestById(UUID id) {
        if (nestJpaRepository.existsById(id)) {
            return mapper.entityToDto(nestJpaRepository.getReferenceById(id));
        }
        throw new ResourceNotFoundException("Nest with id " + id + " not found");
    }

    @Override
    public List<DtoNestRsp> getAllNest() {
        return mapper.listEntityToDto(nestJpaRepository.findAll());
    }

    @Override
    public void deleteNestById(UUID id) {
        if (nestJpaRepository.existsById(id)) {
            nestJpaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Nest with id " + id + " not found");
        }
    }
}
