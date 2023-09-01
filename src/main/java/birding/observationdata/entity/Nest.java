package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "nest")
public class Nest {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id number in 'observation' nest",
            example = "fce26092-6759-47b8-b0fd-c6796d6705fb")
    private UUID id;

    @Column(name="eggs_quantity")
    @Schema(name = "eggs_quantity", example = "4")
    private int eggsQuantity;

    @Column
    @Schema(name = "chicks_quantity", example = "4")
    private int chicksQuantity;

    @Column(name = "description")
    @Schema(description = "description of the nest",
            example = "A nest on the ground near tree")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "biotope_id", referencedColumnName = "id")
    @Schema(description = "ID of table 'biotope'", example = "6a61b1f4-7dcd-4b79-a344-5f246fabe024")
    private Biotope biotope;

    @OneToOne(optional = false)
    @JoinColumn(name = "location_id")
    @Schema(description = "ID of the table 'location'", example = "6a61b1f4-7dcd-4b79-a344-5f246fabe024")
    private Location location;

    @OneToOne(optional = false)
    @JoinColumn(name = "nest_type_id")
    @Schema(description = "ID of the table 'nest_type'", example = "fce26092-6759-47b8-b0fd-c6796d6705fb")
    private NestType nestType;

    @OneToOne(optional = false)
    @JoinColumn(name = "nest_dimension_id")
    @Schema(description = "ID of the table 'nest_dimension'")
    private NestDimension nestDimension;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", insertable = false)
    @Schema(description = "Date and time of the record was created",
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

    public Biotope getBiotope() {
        return biotope;
    }

    public void setBiotope(Biotope biotope) {
        this.biotope = biotope;
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
