package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "nest_dimension")
public class NestDimension {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number of the table 'nest_dimension'", example = "2")
    private Integer id;

    @OneToOne (optional = false, mappedBy = "nestDimension")
    private Nest nest;

    public NestDimension (){}

    public NestDimension(Integer id, Nest nest) {
        this.id = id;
        this.nest = nest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }
}
