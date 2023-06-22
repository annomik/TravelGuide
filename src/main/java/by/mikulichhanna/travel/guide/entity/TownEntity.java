package by.mikulichhanna.travel.guide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "guide", name = "town")

public class TownEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

    @NotEmpty
    @Column(name = "town_name")
    private String name;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "number_of_population")
    private Integer numberOfPopulation;

    @OneToMany (fetch=FetchType.LAZY, mappedBy = "town", cascade = CascadeType.ALL )
    private List<AttractionEntity> attractions;

    public TownEntity(UUID uuid,
                      LocalDateTime dtCreate,
                      LocalDateTime dtUpdate,
                      String name,
                      String countryName,
                      Integer numberOfPopulation) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.countryName = countryName;
        this.numberOfPopulation = numberOfPopulation;
    }


}
