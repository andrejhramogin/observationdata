package birding.observationdata.dto.observation.request;

import birding.observationdata.entity.Nest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class DtoObservationRq {
        @NotNull(message = "Date can`t be null")
        @PastOrPresent(message = "Date or time must be in the past or in the present")
        @Schema(name= "date", description = "date of observation", example = "2023-08-14")
        private LocalDate date;
        @NotNull(message = "Quantity can`t be null")
        @Positive(message = "Quantity must be a positive number")
        @Schema(description = "number of birds", example = "5")
        private int quantity;
        @NotBlank(message = "Description name must not be blank")
        @Schema(description = "description of the observation", example = "A couple on the nest site")
        private String description;
        @Schema(description = "Id of table 'nest'", example = "253a3bde-b477-4731-a592-34e5a0482918")
        private Nest nest;
        @NotNull(message = "Species id can`t be null")
        @Schema(description = "ID of table 'species'", example = "58e6ccb8-102a-4ecd-b43e-e981968cc833")
        private UUID speciesId;
        @NotNull(message = "User can`t be null")
        @Schema(description = "ID of table 'user'", example = "fb68f075-dec4-44b6-9b44-4ccfc6507d7e")
        private UUID userId;
        @NotNull(message = "Place id can`t be null")
        @Schema(description = "ID of table 'place'", example = "e17002a5-63aa-4ab9-8ed0-bd379a1c7255")
        private UUID placeId;
        @Schema(description = "Date and time of the record was created",
                example = "2022-10-03 10:20:11.114")
        private Timestamp createdAt;
        @Schema(description = "Date and time of the record was modified",
                example = "2022-10-03 10:20:11.114")
        private Timestamp modifiedAt;

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

        public UUID getPlaceId() {
                return placeId;
        }

        public void setPlaceId(UUID placeId) {
                this.placeId = placeId;
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
