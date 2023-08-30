package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.UUID;

// не реализовано
@Entity
@Table(name = "nest_dimension")
public class NestDimension {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id number of the table 'nest_dimension'", example = "27f95641-b7a0-423d-980d-6a5bf843d63d")
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
