package birding.observationdata.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "nest")
public class Nest {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id number in 'observation' nest",
            example = "1")
    private Integer id;

    @Column
    @Schema(name = "eggs_quantity", example = "4")
    private int eggsQuantity;

    @Column
    @Schema(name = "chicks_quantity", example = "4")
    private int chicksNumber;

    @Column(name = "description")
    @Schema(description = "description of the nest",
            example = "A nest on the ground near tree")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "biotope_id")
    @Schema(description = "ID of table 'biotope'", example = "?")
    private Biotope biotope;

    @OneToOne(optional = false)
    @JoinColumn(name = "location_id")
    @Schema(description = "ID of the table 'location'", example = "?")
    private Location location;

    @OneToOne(optional = false)
    @JoinColumn(name = "nest_type_id")
    @Schema(description = "ID of the table 'nest_type'", example = "?")
    private NestType nestType;

    @OneToOne(optional = false)
    @JoinColumn(name = "nest_dimension_id")
    @Schema(description = "ID of the table 'nest_dimension'")
    private NestDimension nestDimension;

    @Column(name = "created_at")
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    @Schema(description = "Date and time of the record was created",
            example = "2022-10-03 10:20:11.114")
    private Timestamp modifiedAt;

    public Nest(){}

}
