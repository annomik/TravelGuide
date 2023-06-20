package by.mikulichhanna.travel.guide.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TownCreateDTO {

    private String name;
    private Integer numberOfPopulation;

}
