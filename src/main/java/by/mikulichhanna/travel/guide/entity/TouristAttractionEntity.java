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
public class TouristAttractionEntity {

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
    private int address;


}