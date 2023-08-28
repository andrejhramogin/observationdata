package birding.observationdata.controller;

import birding.observationdata.dto.nest.request.DtoNestRq;
import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.service.nest.NestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Nest API")
public class NestController {
    @Autowired
    private NestService nestService;

    @PostMapping("/nest")
    public DtoNestRsp createNest (@RequestBody DtoNestRq nestRq){
        return nestService.createNewNest(nestRq);
    }

}
