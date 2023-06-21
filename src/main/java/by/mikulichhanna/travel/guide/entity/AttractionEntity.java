package by.mikulichhanna.travel.guide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "guide", name = "attraction")
public class AttractionEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "town_uuid")
//    //@Column(name = "town_uuid")
//    private TownEntity town;


    public AttractionEntity(UUID uuid, LocalDateTime dtCreate,
                            LocalDateTime dtUpdate, String name,
                            String address) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.address = address;
    }
}
