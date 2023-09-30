package birding.observationdata.dto.nest.request;

import birding.observationdata.entity.Biotope;
import birding.observationdata.entity.Location;
import birding.observationdata.entity.NestDimension;
import birding.observationdata.entity.NestType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public class DtoNestRq {
    @Size(min = 1, max = 50, message = "Eggs quantity myst be between 1 and 50")
    @Schema(name = "eggsQuantity", description = "Quantity of eggs", example = "3")
    private int eggsQuantity;
    @Size(min = 1, max = 50, message = "Chicks quantity myst be between 1 and 50")
    @Schema(name = "chicksQuantity", description = "Quantity of chicks", example = "4")
    private int chicksQuantity;
    @Size(max = 500, message = "500 characters max")
    @Schema(name = "description", description = "description of the nest", example = "A nest of the sparrow")
    private String description;
    @NotNull(message = "Biotope can`t be null")
    @Schema(name = "biotope", description = "Biotope type", example = "Lake")
    private Biotope biotope;
    @NotNull(message = "Location can`t be null")
    @Schema(name = "location", description = "Location type", example = "On the ground")
    private Location location;
    @NotNull(message = "Nest type can`t be null")
    @Schema(name = "nestType", description = "Type of the nest", example = "Cup-shaped")
    private NestType nestType;
    @Schema(name = "nestDimension", description = "Dimensions of the nest")
    private NestDimension nestDimension;
    @Schema(name = "createdAt", description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;
    @Schema(name = "modifiedAt", description = "Date and time of the record was modified",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;


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
