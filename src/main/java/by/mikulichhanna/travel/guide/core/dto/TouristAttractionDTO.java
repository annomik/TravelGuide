package by.mikulichhanna.travel.guide.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class TouristAttractionDTO {

    private String name;
    private int address;

}
