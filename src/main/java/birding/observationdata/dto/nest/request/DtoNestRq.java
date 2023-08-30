package birding.observationdata.dto.nest.request;

import java.util.UUID;

public class DtoNestRq {
    private int eggsQuantity;
    private UUID biotopeId;

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
