package by.mikulichhanna.travel.guide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
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
    private String townName;

    @Column(name = "number_of_population")
    private Integer numberOfPopulation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_uuid")
    private List<TouristAttractionEntity> attractions;

}
