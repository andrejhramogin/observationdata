package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "nest_type")
public class NestType {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "id number of table 'nest_type'", example = "8bca55af-9d02-4a42-baa3-abb774c9bd3a")
    private UUID id;

    @Column(name = "type")
    @Schema(description = "description of the nest type", example = "cup - shaped")
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
