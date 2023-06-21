package by.mikulichhanna.travel.guide.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TownCreateDTO {
    private String name;
    private String countryName;
    private Integer numberOfPopulation;

}
