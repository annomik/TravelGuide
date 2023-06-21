package by.mikulichhanna.travel.guide.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TownWithAttractionsDTO {

    private String name;
    private String countryName;
    private Integer numberOfPopulation;
    private List<AttractionDTO> attractions;

}
