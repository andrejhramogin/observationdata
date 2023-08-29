package birding.observationdata.dto.nest.response;

import java.util.UUID;

public class DtoNestRsp {
    private UUID id;
    private int eggsQuantity;
    private UUID biotopeId;

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

    public UUID getBiotopeId() {
        return biotopeId;
    }

    public void setBiotopeId(UUID biotopeId) {
        this.biotopeId = biotopeId;
    }
}
