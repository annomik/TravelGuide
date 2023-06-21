package by.mikulichhanna.travel.guide.core.dto.attraction;

import by.mikulichhanna.travel.guide.core.dto.town.TownWithUUID;
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
