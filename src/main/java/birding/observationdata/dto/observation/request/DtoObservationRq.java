package birding.observationdata.dto.observation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DtoObservationRq {
        @NotNull(message = "Date can`t be null")
        @PastOrPresent(message = "Date or time must be in the past or in the present")
        private LocalDate date;
        @NotNull(message = "Quantity can`t be null")
        @Positive(message = "Quantity must be a positive number")
        private int quantity;
        @NotBlank(message = "Model name must not be blank")
        private String description;
//    private Nest nest;
        private int nestId;
        @NotNull(message = "Species id can`t be null")
        @Positive(message = "Species id must be a positive number")
        private int speciesId;
        @NotNull(message = "User can`t be null")
        @Positive(message = "User id must be a positive number")
        private int userId;
        @NotNull(message = "Place can`t be null")
        @Positive(message = "Place id must be a positive number")
        private int placeId;
        private Timestamp createdAt;
        private Timestamp modifiedAt;

        public DtoObservationRq(){}

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

        public int getNestId() {
                return nestId;
        }

        public void setNestId(int nestId) {
                this.nestId = nestId;
        }

        public int getSpeciesId() {
                return speciesId;
        }

        public void setSpeciesId(int speciesId) {
                this.speciesId = speciesId;
        }

        public int getUserId() {
                return userId;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }

        public int getPlaceId() {
                return placeId;
        }

        public void setPlaceId(int placeId) {
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
