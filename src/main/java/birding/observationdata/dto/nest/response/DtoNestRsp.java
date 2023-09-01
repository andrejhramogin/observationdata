package birding.observationdata.dto.nest.response;

import birding.observationdata.entity.Biotope;
import birding.observationdata.entity.Location;
import birding.observationdata.entity.NestDimension;
import birding.observationdata.entity.NestType;

import java.sql.Timestamp;
import java.util.UUID;

public class DtoNestRsp {
    private UUID id;
    private int eggsQuantity;
    private int chicksQuantity;
    private String description;
    private Biotope biotope;
    private Location location;
    private NestType nestType;
    private NestDimension nestDimension;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

//    public DtoNestRsp(){}

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

    public int getChicksQuantity() {
        return chicksQuantity;
    }

    public void setChicksQuantity(int chicksQuantity) {
        this.chicksQuantity = chicksQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public NestType getNestType() {
        return nestType;
    }

    public void setNestType(NestType nestType) {
        this.nestType = nestType;
    }

    public NestDimension getNestDimension() {
        return nestDimension;
    }

    public void setNestDimension(NestDimension nestDimension) {
        this.nestDimension = nestDimension;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
