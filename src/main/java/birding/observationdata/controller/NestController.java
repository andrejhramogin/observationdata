package birding.observationdata.controller;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.service.nest.NestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Nest API")
public class NestController {
    @Autowired
    private NestService nestService;

    @PostMapping("/nest")
    public DtoNestRsp createNest (@RequestBody DtoNestRq nestRq){
        return nestService.createNewNest(nestRq);
    }

    @GetMapping("/nest")
    public List<DtoNestRsp> getAllNest(){
        return nestService.getAllNest();
    }

    @GetMapping("/nest/{id}")
    public DtoNestRsp getNestById(@PathVariable UUID id){
        return nestService.findNestById(id);
    }
}
