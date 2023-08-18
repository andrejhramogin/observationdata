package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

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

    @OneToOne(optional = true)
    @JoinColumn(name = "nest_id")
    @Schema(description = "ID of table 'nest'", example = "1")
    private Nest nest;


    //    @OneToOne(optional = false)
//    @JoinColumn(name = "place_id", nullable = false)
//    @Schema(description = "ID of table 'place'", example = "3")
    private int placeId;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @Schema(description = "ID of table 'user'", example = "2")
    private int userId;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "species_id", nullable = false)
//    @Schema(description = "ID of table 'species'", example = "2")
    private int speciesId;

    @Column(name = "created_at")
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;

    public Observation(){}

    public Observation(Integer id, Date date, int quantity, String description,
                       int placeId, Nest nest, int userId, int speciesId,
                       Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.description = description;
        this.placeId = placeId;
        this.nest = nest;
        this.userId = userId;
        this.speciesId = speciesId;
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

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
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

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", placeId=" + placeId +
                ", nest=" + nest +
                ", userId=" + userId +
                ", speciesId=" + speciesId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}