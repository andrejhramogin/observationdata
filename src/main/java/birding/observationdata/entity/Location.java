package birding.observationdata.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type")
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
