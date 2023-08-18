package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "nest_type")
public class NestType {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number of table 'nest_type'", example = "1")
    private Integer id;

    @Column(name = "type")
    @Schema(description = "description of the nest type", example = "cup - shaped")
    private String type;

    @OneToOne(optional = false, mappedBy = "nestType")
    private Nest nest;

    public NestType(){}

    public NestType(int id, String type, Nest nest) {
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
