package by.mikulichhanna.travel.guide.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TownCreateDTO {
    private String name;
    private String countryName;
    private Integer numberOfPopulation;

}
