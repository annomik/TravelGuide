package by.mikulichhanna.travel.guide.service.converters;

import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionDTO;
import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AttractionToEntity implements Converter<AttractionDTO, AttractionEntity>{

        @Override
        public AttractionEntity convert(AttractionDTO attractionDTO) {
            LocalDateTime dtCreate = LocalDateTime.now();

            return new AttractionEntity(UUID.randomUUID(),
                    dtCreate,
                    dtCreate,
                    attractionDTO.getName(),
                    attractionDTO.getAddress()
                    );
        }



}
