package by.mikulichhanna.travel.guide.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AttractionDTO {

    private String name;
    private String address;
    private TownWithUUID townUUID;

}
