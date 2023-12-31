package by.mikulichhanna.travel.guide.service;

import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionAllDTO;
import by.mikulichhanna.travel.guide.core.dto.PageDTO;
import by.mikulichhanna.travel.guide.core.dto.town.TownCreateDTO;
import by.mikulichhanna.travel.guide.core.dto.town.TownWithAllDTO;
import by.mikulichhanna.travel.guide.core.exception.SingleErrorResponse;
import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import by.mikulichhanna.travel.guide.repositories.ITownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class TownService {

    private final ITownRepository townRepository;
    private final ConversionService conversionService;

    @Transactional
    public void addTown(TownCreateDTO townCreateDTO) {
        TownEntity EntityByName = townRepository.findByName(townCreateDTO.getName());
        if (EntityByName != null){
            throw new SingleErrorResponse("Город с таким именем уже есть в базе");
        }
        //validate(townDTO);
        TownEntity townEntity = conversionService.convert(townCreateDTO, TownEntity.class);
        townRepository.save(townEntity);
    }

    public Optional<TownEntity> findByUUID(UUID uuid){
        return  townRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        if(uuid == null){
            throw new SingleErrorResponse("Введите параметры для удаления");
        } else {
            Optional<TownEntity> findEntity = townRepository.findById(uuid);
            if (!findEntity.isPresent()) {
                throw new SingleErrorResponse("Города с id " + uuid + " для удаления не найдено");
            } else {
                townRepository.deleteById(uuid);
            }
        }
    }

    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, TownCreateDTO townCreateDTO) {
        if(uuid == null || dtUpdate == null){
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
//        validate(townCreateDTO);
        Optional<TownEntity> findEntity = townRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Города с id " + uuid + " для обновления не найдено");
        } else {
            TownEntity entity = findEntity.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
                entity.setName(townCreateDTO.getName());
                entity.setCountryName(townCreateDTO.getCountryName());
                entity.setNumberOfPopulation(townCreateDTO.getNumberOfPopulation());

                townRepository.save(entity);
            } else {
                throw new SingleErrorResponse("Версии города с id " + uuid + " не совпадают!");
            }
        }
    }

    public PageDTO<TownWithAllDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);
        Page<TownEntity> allEntity = townRepository.findAll(pageable);
        List<TownWithAllDTO> content = convertListEntityToDTO(allEntity);

        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content
        );
    }

    private List<TownWithAllDTO> convertListEntityToDTO(Page<TownEntity> allEntity){
        List<TownWithAllDTO> content = new ArrayList<>();

        for (TownEntity townEntity : allEntity) {
            List<AttractionEntity> attractionEntities = townEntity.getAttractions();
            List<AttractionAllDTO> listAttractionDTO = new ArrayList<>();
            for(AttractionEntity attractionEntity: attractionEntities){
                AttractionAllDTO attractionDTO = new AttractionAllDTO( attractionEntity.getUuid(),
                attractionEntity.getDtCreate(),
                attractionEntity.getDtUpdate(),
                attractionEntity.getName(),
                attractionEntity.getAddress()
                );
                listAttractionDTO.add(attractionDTO);
            }
            TownWithAllDTO townDTO = new TownWithAllDTO(townEntity.getUuid(),
                    townEntity.getDtCreate(),
                    townEntity.getDtUpdate(),
                    townEntity.getName(),
                    townEntity.getCountryName(),
                    townEntity.getNumberOfPopulation(),
                    listAttractionDTO
            );
            content.add(townDTO);
        }
        return content;
    }
//
//    @Override
//    public void validate(RecipeCreateDTO recipeCreateDTO)  {
//        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();
//
//        if (recipeCreateDTO.getTitle() == null || recipeCreateDTO.getTitle().isBlank() ) {
//            multipleErrorResponse.setErrors(new Error("Title", "Поле не заполнено"));
//        }
//        TownEntity recipeByTitle = recipeRepository.findByTitle(recipeCreateDTO.getTitle());
//        if (recipeByTitle != null) {
//            multipleErrorResponse.setErrors(new Error("Title", "Рецепт с таким названием уже существует"));
//        }
//        if (recipeCreateDTO.getComposition().size() == 0 ) {
//            multipleErrorResponse.setErrors(new Error("Composition", "Рецепт должен состоять хотя бы из 1 продукта"));
//        }
//        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
//        for(CompositionDTO composition : compositionDTOList) {
//            if (composition.getWeight() <= 0 && composition.getWeight() % 1 == 0) {
//                multipleErrorResponse.setErrors(new Error("Weight", "Введите целое положительное число"));
//            }
//            Optional<TouristAttractionEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
//            if (productFoundInDB.isEmpty()){
//                multipleErrorResponse.setErrors(new Error(
//                        "Product", "Продукта с id " + composition.getProduct().getUuid() + " нет в базе продуктов"));
//            }
//        }
//
//        if ( !multipleErrorResponse.getErrors().isEmpty()) {
//            throw multipleErrorResponse;
//        }
//    }
}
