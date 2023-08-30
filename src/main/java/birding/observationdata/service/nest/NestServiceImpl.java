package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.entity.Nest;
import birding.observationdata.exception.ResourceNotFoundException;
import birding.observationdata.mapper.NestMapper;
import birding.observationdata.repository.NestJpaRepository;
import birding.observationdata.service.biotope.BiotopService;
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
    @Autowired
    BiotopService biotopService;
    @Override
    public DtoNestRsp createNewNest(DtoNestRq dto) {

        Nest nest = mapper.dtoToEntity(dto);
        nest.setBiotope(biotopService.findBiotopeById(dto.getBiotopeId()));
        nestJpaRepository.save(nest);

        DtoNestRsp nestRsp = mapper.entityToDto(nest);
        nestRsp.setBiotopeId(nest.getBiotope().getId());
        return nestRsp;
    }

    @Override
    public DtoNestRsp findNestById(UUID id) {
        DtoNestRsp nestRsp = mapper.entityToDto(nestJpaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Nest with id " + id + " not found")));

        return nestRsp;
    }

    @Override
    public List<DtoNestRsp> getAllNest() {
        return nestJpaRepository.findAll().stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
