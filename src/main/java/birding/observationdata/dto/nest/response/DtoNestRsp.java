package birding.observationdata.dto.nest.response;

import birding.observationdata.entity.Biotope;
import birding.observationdata.entity.Location;
import birding.observationdata.entity.NestType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.UUID;

public class DtoNestRsp {
    @Schema(name = "id", description = "Nest id", example = "abcefe59-d23e-43a5-b538-7de590390c3c")
    private UUID id;
    @Schema(name = "eggsQuantity", description = "Quantity of eggs", example = "3")
    private int eggsQuantity;
    @Schema(name = "chicksQuantity", description = "Quantity of chicks", example = "4")
    private int chicksQuantity;
    @Schema(name = "description", description = "description of the nest", example = "A nest of the sparrow")
    private String description;
    @Schema(name = "biotope", description = "Biotope type", example = "Lake")
    private Biotope biotope;
    @Schema(name = "location", description = "Location type", example = "On the ground")
    private Location location;
    @Schema(name = "nestType", description = "Type of the nest", example = "Cup-shaped")
    private NestType nestType;
    @Schema(name = "createdAt", description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;
    @Schema(name = "modifiedAt", description = "Date and time of the record was modified",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;

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

//    public NestDimension getNestDimension() {
//        return nestDimension;
//    }
//
//    public void setNestDimension(NestDimension nestDimension) {
//        this.nestDimension = nestDimension;
//    }

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
