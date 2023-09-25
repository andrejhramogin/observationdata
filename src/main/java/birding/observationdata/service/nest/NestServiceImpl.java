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

        //1. При POST запроcе: в БД помещает правильно, но НЕ ВОЗВРАЩАЕТ на фронт biotop type по id из запроса.
        return mapper.entityToDto(nestJpaRepository.save(mapper.dtoToEntity(dto)));

        //2. При POST запросе: в БД помещает правильно и возвращает на фронт biotop type из бд по его id из запроса.
//        Nest nest = mapper.dtoToEntity(dto);
//        DtoNestRsp nestRsp = mapper.entityToDto(nestJpaRepository.save(nest));
////        по id из request находим в таблице biotop соответствующую строку
//        Biotope biotope = biotopeService.findBiotopeById(nest.getBiotope().getId());
//        nestRsp.getBiotope().setType(biotope.getType());
//        return nestRsp;
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
