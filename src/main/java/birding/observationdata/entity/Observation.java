package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number in 'observation' table",
            example = "1")
    private Integer id;

    @Column(name = "date", nullable = false)
    @Schema(description = "date of observation",
            example = "2023-08-14")
    private Date date;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "number of birds",
            example = "5")
    private int quantity;

    @Column(name = "description")
    @Schema(description = "description of the observation",
            example = "A couple on the nest site")
    private String description;

    //    @OneToOne(optional = true)
//    @JoinColumn(name = "id")
//    @Schema(description = "ID of table 'nest'", example = "1")
//    private Nest nest;

    @Column(name="nest_id")
    @Schema(description = "ID of table 'nest'", example = "2")
    private int nestId;

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
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "modified_at", insertable = false)
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;

    public Observation(){}

    public Observation(Integer id, Date date, int quantity, String description,
                       int nestId, int speciesId, int userId, int placeId,
                       LocalDateTime createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.description = description;
        this.nestId = nestId;
        this.speciesId = speciesId;
        this.userId = userId;
        this.placeId = placeId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}