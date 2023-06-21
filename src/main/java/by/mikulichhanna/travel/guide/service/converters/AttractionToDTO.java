package by.mikulichhanna.travel.guide.service.converters;

import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionWithTownDTO;
import by.mikulichhanna.travel.guide.core.dto.town.TownWithUUID;
import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttractionToDTO implements Converter<AttractionEntity, AttractionWithTownDTO> {
    @Override
    public AttractionWithTownDTO convert(AttractionEntity attractionEntity) {

        return new AttractionWithTownDTO(attractionEntity.getUuid(),
                attractionEntity.getDtCreate(),
                attractionEntity.getDtUpdate(),
                attractionEntity.getName(),
                attractionEntity.getAddress(),
                new TownWithUUID(attractionEntity.getTown().getUuid())
        );
    }


}
