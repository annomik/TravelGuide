package by.mikulichhanna.travel.guide.service.converters;

import by.mikulichhanna.travel.guide.core.dto.town.TownCreateDTO;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TownToEntity implements Converter<TownCreateDTO, TownEntity> {
    @Override
    public TownEntity convert(TownCreateDTO townCreateDTO) {
        LocalDateTime dtCreate = LocalDateTime.now();

        return new TownEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                townCreateDTO.getName(),
                townCreateDTO.getCountryName(),
                townCreateDTO.getNumberOfPopulation());
    }

}
