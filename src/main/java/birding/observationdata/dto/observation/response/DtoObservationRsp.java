package birding.observationdata.dto.observation.response;

import birding.observationdata.integration.place.dto.response.DtoPlaceRsp;
import birding.observationdata.entity.Nest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class DtoObservationRsp {

    private UUID id;
    private LocalDate date;
    private int quantity;
    private String description;
    private Nest nest;
    private UUID speciesId;
    private UUID userId;
    private DtoPlaceRsp dtoPlaceRsp;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public UUID getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(UUID speciesId) {
        this.speciesId = speciesId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public DtoPlaceRsp getDtoPlaceRsp() {
        return dtoPlaceRsp;
    }

    public void setDtoPlaceRsp(DtoPlaceRsp dtoPlaceRsp) {
        this.dtoPlaceRsp = dtoPlaceRsp;
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
