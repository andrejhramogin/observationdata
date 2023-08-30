package birding.observationdata.service.nest;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.entity.Biotope;
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
        //При POST запрое: в БД помещает правильно, но НЕ ВОЗВРАЩАЕТ на фронт biotop type.
//        return mapper.entityToDto(nestJpaRepository.save(mapper.dtoToEntity(dto)));

        //При POST запросе: в БД помещает правильно и возвращает на фронт biotop type.
        Nest nest = mapper.dtoToEntity(dto);
        DtoNestRsp nestRsp = mapper.entityToDto(nestJpaRepository.save(nest));

        //по id из request находим в таблице biotop соответствующую строку
        Biotope biotope = biotopService.findBiotopeById(nest.getBiotope().getId());
        nestRsp.getBiotope().setType(biotope.getType());
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
