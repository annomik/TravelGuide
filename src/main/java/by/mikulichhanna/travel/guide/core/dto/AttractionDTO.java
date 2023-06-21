package by.mikulichhanna.travel.guide.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDTO {

    private String name;
    private String address;
    private TownWithUUID townUUID;

}
