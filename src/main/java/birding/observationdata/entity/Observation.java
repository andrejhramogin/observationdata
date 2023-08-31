package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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

    //    @OneToOne(optional = true)
    //TODO cascade = CascadeType.ALL?
//    @JoinColumn(name = "id")
//    @Schema(description = "ID of table 'nest'", example = "1")
//    private Nest nest;

    @Column(name="nest_id")
    @Schema(description = "ID of table 'nest'", example = "2")
    private UUID nestId;

    //    @OneToOne(optional = false)
//    @JoinColumn(name = "species_id", nullable = false)
    @Column(name="species_id", nullable = false)
    @Schema(description = "ID of table 'species'", example = "2")
    private int speciesId;


    //    @OneToOne(optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
    @Column(name="user_id", nullable = false)
    @Schema(description = "ID of table 'user'", example = "2")
    private int userId;


    //    @OneToOne(optional = false)
//    @JoinColumn(name = "place_id", nullable = false)
    @Column(name="place_id", nullable = false)
    @Schema(description = "ID of table 'place'", example = "3")
    private int placeId;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Date and time of the record was created",
    example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;

    @CreationTimestamp
    @Column(name = "modified_at", insertable = false)
    @Schema(description = "Date and time of the record was modified",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;
    public Observation(){}

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

    public UUID getNestId() {
        return nestId;
    }

    public void setNestId(UUID nestId) {
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