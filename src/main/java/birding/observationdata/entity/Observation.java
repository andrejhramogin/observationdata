package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id number in 'observation' table",
            example = "fb68f075-dec4-44b6-9b44-4ccfc6507d7e")
    private UUID id;

    @Column(name = "date", nullable = false)
    @Schema(description = "date of observation",
            example = "2023-08-14")
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "number of birds",
            example = "5")
    private int quantity;

    @Column(name = "description")
    @Schema(description = "description of the observation",
            example = "A couple on the nest site")
    private String description;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "nest_id", referencedColumnName = "id")
    @Schema(description = "ID of table 'nest'", example = "1")
    private Nest nest;

    @Column(name = "species_id", nullable = false)
    @Schema(description = "ID of table 'species'", example = "ecc06efd-1495-424f-a666-7624e8e7415f")
    private UUID speciesId;

    @Column(name = "user_id", nullable = false)
    @Schema(description = "ID of table 'user'", example = "fb68f075-dec4-44b6-9b44-4ccfc6507d7e")
    private UUID userId;

    @Column(name = "place_id", nullable = false)
    @Schema(description = "ID of table 'place'", example = "fb68f075-dec4-44b6-9b44-4ccfc6507d7e")
    private UUID placeId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", insertable = false)
    @Schema(description = "Date and time of the record was modified",
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