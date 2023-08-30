package birding.observationdata.dto.nest.response;

import birding.observationdata.entity.Biotope;

import java.util.UUID;

public class DtoNestRsp {
    private UUID id;
    private int eggsQuantity;
//    private UUID biotopeId;

    private Biotope biotope;
    public DtoNestRsp(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getEggsQuantity() {
        return eggsQuantity;
    }

    public void setEggsQuantity(int eggsQuantity) {
        this.eggsQuantity = eggsQuantity;
    }

    public Biotope getBiotope() {
        return biotope;
    }
    public void setBiotope(Biotope biotope) {
        this.biotope = biotope;
    }
}
