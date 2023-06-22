package by.mikulichhanna.travel.guide.service;

import by.mikulichhanna.travel.guide.core.dto.PageDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionWithTownDTO;
import by.mikulichhanna.travel.guide.core.exception.SingleErrorResponse;
import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import by.mikulichhanna.travel.guide.repositories.IAttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class AttractionService {

    private final IAttractionRepository attractionRepository;
    private final TownService townService;
    private final ConversionService conversionService;

    @Transactional
    public void addNewAttraction(AttractionDTO attractionDTO) {
       // validate(attractionDTO);
        LocalDateTime dtCreate = LocalDateTime.now();
        Optional<TownEntity> townFromDB = townService.findByUUID(attractionDTO.getTownUUID().getUuid());
        TownEntity townEntity = townFromDB.get();
        AttractionEntity attractionEntity = new AttractionEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                attractionDTO.getName(),
                attractionDTO.getAddress(),
                townEntity
        );
        attractionRepository.save(attractionEntity);
    }

//    @Override
//    public Optional<TouristAttractionEntity> findByUUID(UUID uuid){
//        return  productRepository.findById(uuid);
//    }
//
    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, AttractionDTO attractionDTO) {
        if(uuid == null || dtUpdate == null){
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
//        validate(attractionDTO);
        Optional<AttractionEntity> findEntity = attractionRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Продукта с id " + uuid + " для обновления не найдено");
        } else {
            AttractionEntity entity = findEntity.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
                entity.setName(attractionDTO.getName());
                entity.setAddress(attractionDTO.getAddress());

                attractionRepository.save(entity);
            } else {
                throw new SingleErrorResponse("Версии продукта с id " + uuid + " не совпадают!");
            }
        }
    }

    public PageDTO<AttractionWithTownDTO> getPage(int numberOfPage, int size) {
        Pageable pageable = PageRequest.of(numberOfPage, size);

        Page<AttractionEntity> allEntity = attractionRepository.findAll(pageable);
        List<AttractionWithTownDTO> content = new ArrayList<>();
        for (AttractionEntity entity: allEntity) {
            AttractionWithTownDTO attractionDTO = conversionService.convert(entity, AttractionWithTownDTO.class);
            content.add(attractionDTO);
        }
        return new PageDTO<>(allEntity.getNumber(),
                allEntity.getSize(),
                allEntity.getTotalPages(),
                allEntity.getTotalElements(),
                allEntity.isFirst(),
                allEntity.getNumberOfElements(),
                allEntity.isLast(),
                content );
    }

    public void delete(UUID uuid) {
        attractionRepository.deleteById(uuid);
    }
//
//    @Override
//    public void validate(ProductCreateDTO productCreateDTO)  {
//        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();
//
//        if (productCreateDTO.getTitle() == null || productCreateDTO.getTitle().isBlank()){
//            multipleErrorResponse.setErrors(new Error("Title", "Поле не заполнено"));
//        }
//        if (productCreateDTO.getWeight() <= 0 && productCreateDTO.getWeight() % 1 == 0){
//            multipleErrorResponse.setErrors(new Error("Weight", "Введите целое положительное число"));
//        }
//        if (productCreateDTO.getCalories() <= 0 && productCreateDTO.getCalories() % 1 == 0) {
//            multipleErrorResponse.setErrors(new Error("Calories", "Введите целое положительное число"));
//        }
//        if (productCreateDTO.getProteins() <= 0 ) {
//            multipleErrorResponse.setErrors(new Error("Proteins", "Введите корректное значение. Например: 4.2"));
//        }
//        if (productCreateDTO.getFats() <= 0 ) {
//            multipleErrorResponse.setErrors(new Error("Fats", "Введите корректное значение. Например: 4.2"));
//        }
//        if (productCreateDTO.getCarbohydrates() <= 0 ) {
//            multipleErrorResponse.setErrors(new Error("Carbohydrates", "Введите корректное значение. Например: 50.2"));
//        }
//        if (!multipleErrorResponse.getErrors().isEmpty()) {
//            throw multipleErrorResponse;
//        }
//    }
}
