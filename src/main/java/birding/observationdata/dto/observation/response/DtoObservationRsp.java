package birding.observationdata.dto.observation.response;

import birding.observationdata.dto.nest.response.DtoNestRsp;
import birding.observationdata.integration.place.dto.response.PlaceDtoResp;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class DtoObservationRsp {

    @Schema(name = "id", description = "Observation id", example = "abcefe59-d23e-43a5-b538-7de590390c3c")
    private UUID id;
    @Schema(name = "date", description = "date of observation", example = "2023-08-14")
    private LocalDate date;
    @Schema(name = "quantity", description = "number of birds", example = "5")
    private int quantity;
    @Schema(name = "description", description = "description of the observation", example = "A couple on the nest site")
    private String description;
    @Schema(name = "dtoNestRsp", description = "Dto of nest response")
    private DtoNestRsp dtoNestRsp;
    @Schema(name = "speciesId", description = "ID of table 'species'", example = "58e6ccb8-102a-4ecd-b43e-e981968cc833")
    private UUID speciesId;
    @Schema(name = "userId", description = "ID of table 'user'", example = "fb68f075-dec4-44b6-9b44-4ccfc6507d7e")
    private UUID userId;
    @Schema(name = "placeDtoResp", description = "Dto of place response")
    private PlaceDtoResp placeDtoResp;
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

    public DtoNestRsp getDtoNestRsp() {
        return dtoNestRsp;
    }

    public void setDtoNestRsp(DtoNestRsp dtoNestRsp) {
        this.dtoNestRsp = dtoNestRsp;
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

    public PlaceDtoResp getPlaceDtoResp() {
        return placeDtoResp;
    }

    public void setPlaceDtoResp(PlaceDtoResp placeDtoResp) {
        this.placeDtoResp = placeDtoResp;
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
