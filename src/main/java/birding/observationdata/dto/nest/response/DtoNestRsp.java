package birding.observationdata.dto.nest.response;

public class DtoNestRsp {
    private int id;
    private int eggsQuantity;
    private int biotopeId;

    public DtoNestRsp(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
