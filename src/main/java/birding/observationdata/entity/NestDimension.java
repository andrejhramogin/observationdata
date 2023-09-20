package birding.observationdata.entity;

import jakarta.persistence.*;

import java.util.UUID;

// не реализовано
@Entity
@Table(name = "nest_dimension")
public class NestDimension {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public NestDimension(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
