package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number of table 'location'", example = "1")
    private Integer id;

    @Column(name = "type")
    @Schema(description = "description of the nest location",
            example = "Ground")
    private String type;

    @OneToOne(optional = false, mappedBy = "location")
    private Nest nest;

    public Location(){}

    public Location(int id, String type, Nest nest) {
        this.id = id;
        this.type = type;
        this.nest = nest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }
}
