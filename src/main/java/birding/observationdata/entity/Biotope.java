package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "biotope")
public class Biotope {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id number of table 'biotope'", example = "ea134510-c81c-4d27-a497-2b83d0e970d3")
    private UUID id;

    @Column(name = "type")
    @Schema(description = "description of the biotope",
            example = "Forest")
    private String type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
