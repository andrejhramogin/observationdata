package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "biotope")
public class Biotope {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number of table 'biotope'", example = "1")
    private Integer id;

    @Column(name = "type")
    @Schema(description = "description of the biotope",
            example = "Forest")
    private String type;

    public Biotope(){}

    public Biotope(Integer id, String type, Nest nest) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
