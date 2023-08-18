package birding.observationdata.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media_connect")
public class MediaConnect {
    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
