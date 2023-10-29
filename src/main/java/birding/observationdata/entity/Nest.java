package birding.observationdata.entity;

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
    private UUID id;

    @Column(name = "eggs_quantity")
    private int eggsQuantity;

    @Column(name = "chicks_quantity")
    private int chicksQuantity;

    @Column(name = "description")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "biotope_id", referencedColumnName = "id")
    private Biotope biotope;

    @OneToOne(optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToOne(optional = false)
    @JoinColumn(name = "nest_type_id", referencedColumnName = "id")
    private NestType nestType;

    @OneToOne
    @JoinColumn(name = "nest_dimension_id", referencedColumnName = "id")
    private NestDimension nestDimension;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", insertable = false)
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
