package by.mikulichhanna.travel.guide.service;

import by.mikulichhanna.travel.guide.core.dto.AttractionDTO;
import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import by.mikulichhanna.travel.guide.repositories.ITouristAttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
//@Transactional
public class AttractionService {

    private final ITouristAttractionRepository repository;
    private final TownService townService;
    private final ConversionService conversionService;

    @Transactional
    public void addNewAttraction(AttractionDTO attractionDTO) {
       // validate(attractionDTO);
        //AttractionEntity entity = conversionService.convert(attractionDTO, AttractionEntity.class);
        LocalDateTime dtCreate = LocalDateTime.now().withNano(3);
        Optional<TownEntity> townFromDB = townService.findByUUID(attractionDTO.getTownUUID().getUuid());
        TownEntity townEntity = townFromDB.get();
        AttractionEntity attractionEntity = new AttractionEntity(UUID.randomUUID(),
                dtCreate,
                dtCreate,
                attractionDTO.getName(),
                attractionDTO.getAddress(),
                townEntity
        );

        repository.save(attractionEntity);
    }

//    @Override
//    public Optional<TouristAttractionEntity> findByUUID(UUID uuid){
//        return  productRepository.findById(uuid);
//    }
//
//    @Override
//    public void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDTO productCreateDTO) {
//        if(uuid == null || dtUpdate == null){
//            throw new SingleErrorResponse("Введите параметры для обновления");
//        }
//        validate(productCreateDTO);
//        Optional<TouristAttractionEntity> findEntity = productRepository.findById(uuid);
//        if (!findEntity.isPresent()) {
//            throw new SingleErrorResponse("Продукта с id " + uuid + " для обновления не найдено");
//        } else {
//            TouristAttractionEntity entity = findEntity.get();
//            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
//                entity.setName(productCreateDTO.getTitle());
//                entity.setAddress(productCreateDTO.getWeight());
//                entity.setCalories(productCreateDTO.getCalories());
//                entity.setProteins(productCreateDTO.getProteins());
//                entity.setFats(productCreateDTO.getFats());
//                entity.setCarbohydrates(productCreateDTO.getCarbohydrates());
//
//                productRepository.save(entity);
//            } else {
//                throw new SingleErrorResponse("Версии продукта с id " + uuid + " не совпадают!");
//            }
//        }
//    }
//
//    @Override
//    public PageDTO<ProductDTO> getPage(int numberOfPage, int size) {
//        Pageable pageable = PageRequest.of(numberOfPage, size);
//
//        Page<TouristAttractionEntity> allEntity = productRepository.findAll(pageable);
//        List<ProductDTO> content = new ArrayList<>();
//        for (TouristAttractionEntity entity: allEntity) {
//            ProductDTO productDTO = conversionService.convert(entity, ProductDTO.class);
//            content.add(productDTO);
//        }
//        return new PageDTO<>(allEntity.getNumber(),
//                allEntity.getSize(),
//                allEntity.getTotalPages(),
//                allEntity.getTotalElements(),
//                allEntity.isFirst(),
//                allEntity.getNumberOfElements(),
//                allEntity.isLast(),
//                content );
//    }
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
