package birding.observationdata.dto.nest.request;

public class DtoNestRq {
    private int eggsQuantity;
    private int biotopeId;

    public DtoNestRq(){}

    public int getEggsQuantity() {
        return eggsQuantity;
    }

    public void setEggsQuantity(int eggsQuantity) {
        this.eggsQuantity = eggsQuantity;
    }

    public int getBiotopeId() {
        return biotopeId;
    }

    public void setBiotopeId(int biotopeId) {
        this.biotopeId = biotopeId;
    }
}
