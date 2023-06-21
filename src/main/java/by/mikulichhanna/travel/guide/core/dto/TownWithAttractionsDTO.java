package by.mikulichhanna.travel.guide.core.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class TownWithAttractionsDTO {

    private String name;
    private String countryName;
    private Integer numberOfPopulation;
    private List<AttractionDTO> attractions;

}
