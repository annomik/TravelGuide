package by.mikulichhanna.travel.guide.core.dto;

import by.mikulichhanna.travel.guide.service.converters.serializers.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TownWithAllDTO {

    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;

    private String townName;
    private String countryName;
    private Integer numberOfPopulation;
    private List<AttractionWithAllDTO> attractions;



}
