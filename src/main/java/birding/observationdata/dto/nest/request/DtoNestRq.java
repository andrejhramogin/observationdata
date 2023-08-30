package birding.observationdata.dto.nest.request;

import birding.observationdata.entity.Biotope;

import java.util.UUID;

public class DtoNestRq {
    private int eggsQuantity;
//    private UUID biotopeId;

    private Biotope biotope;

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
