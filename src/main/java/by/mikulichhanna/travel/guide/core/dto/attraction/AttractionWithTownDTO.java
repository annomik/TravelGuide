package by.mikulichhanna.travel.guide.core.dto.attraction;

import by.mikulichhanna.travel.guide.core.dto.town.TownWithUUID;
import by.mikulichhanna.travel.guide.service.converters.serializers.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttractionWithTownDTO {

    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;

    private String name;
    private String address;
    private TownWithUUID townUUID;

}
