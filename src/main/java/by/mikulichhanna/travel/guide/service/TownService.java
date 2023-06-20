package by.mikulichhanna.travel.guide.service;

import by.mikulichhanna.travel.guide.core.dto.TownCreateDTO;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import by.mikulichhanna.travel.guide.repositories.api.ITownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TownService {

    private final ITownRepository townRepository;
    private final ConversionService conversionService;

    @Transactional
    public void addTown(TownCreateDTO townCreateDTO) {
        //validate(recipeCreateDTO);

        TownEntity townEntity = conversionService.convert(townCreateDTO, TownEntity.class);
        townRepository.save(townEntity);
    }

//    @Override
//    public void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDTO recipeCreateDTO) {
//        if (uuid == null || dtUpdate == null || recipeCreateDTO == null) {
//            throw new SingleErrorResponse("Введите параметры для обновления");
//        }
//        validate(recipeCreateDTO);
//        Optional<TownEntity> findEntity = recipeRepository.findById(uuid);
//        if (!findEntity.isPresent()) {
//            throw new SingleErrorResponse("Рецепта с id " + uuid + " для обновления не найдено!");
//        }
//        TownEntity townEntity = findEntity.get();
//        if (!(townEntity.getDtUpdate().isEqual(dtUpdate) && townEntity.getUuid().equals(uuid))) {
//            throw new SingleErrorResponse("Версии рецепта с id " + uuid + " не совпадают!");
//        }
//        List<CompositionDTO> compositionDTOList = recipeCreateDTO.getComposition();
//        List<CompositionEntity> compositionEntityList = new ArrayList<>();
//        for (CompositionDTO composition : compositionDTOList) {
//            Optional<TouristAttractionEntity> productFoundInDB = productService.findByUUID(composition.getProduct().getUuid());
//            TouristAttractionEntity touristAttractionEntity = productFoundInDB.get();
//            compositionEntityList.add(new CompositionEntity(
//                    UUID.randomUUID(),
//                    touristAttractionEntity,
//                    composition.getWeight()));
//        }
//        townEntity.setTitle(recipeCreateDTO.getTitle());
//        townEntity.setComposition(compositionEntityList);
//        recipeRepository.save(townEntity);
//    }
//
//    @Override
//    public PageDTO<RecipeDTO> getPage(int numberOfPage, int size) {
//        Pageable pageable = PageRequest.of(numberOfPage, size);
//        Page<TownEntity> allEntity = recipeRepository.findAll(pageable);
//        List<RecipeDTO> content = convertListRecipeEntityToDTO(allEntity);
//
//        return new PageDTO<>(allEntity.getNumber(),
//                allEntity.getSize(),
//                allEntity.getTotalPages(),
//                allEntity.getTotalElements(),
//                allEntity.isFirst(),
//                allEntity.getNumberOfElements(),
//                allEntity.isLast(),
//                content
//        );
//    }
//
//    private List<RecipeDTO> convertListRecipeEntityToDTO(Page<TownEntity> allEntity){
//        List<RecipeDTO> content = new ArrayList<>();
//        for (TownEntity townEntity : allEntity) {
//            int weightOfRecipe = 0;
//            double caloriesOfRecipe = 0;
//            double proteinsOfRecipe = 0;
//            double fatsOfRecipe = 0;
//            double carbohydratesOfRecipe = 0;
//            List<CompositionEntity> compositionEntityList = townEntity.getComposition(); // все энтити ингредиентов(продукт+вес
//            List<CompositionWithAllParametersDTO> composition = new ArrayList<>();
//            for(CompositionEntity compositionEntity : compositionEntityList) {
//                Optional<TouristAttractionEntity> productFoundInDB = productService.findByUUID(compositionEntity.getProductEntity().getUuid());
//                TouristAttractionEntity touristAttractionEntity = productFoundInDB.get(); //product from db
//                ProductDTO productDTO = conversionService.convert(touristAttractionEntity, ProductDTO.class);
//                double calories =compositionEntity.getWeight() * productDTO.getCalories() / productDTO.getWeight();
//                double proteins = compositionEntity.getWeight() * productDTO.getProteins() / productDTO.getWeight();
//                double fats = compositionEntity.getWeight() * productDTO.getFats() / productDTO.getWeight();
//                double carbohydrates = compositionEntity.getWeight() * productDTO.getCarbohydrates() / productDTO.getWeight();
//                CompositionWithAllParametersDTO compositionWithAllParameters = new CompositionWithAllParametersDTO(
//                        productDTO,
//                        compositionEntity.getWeight(),
//                        calories,
//                        proteins,
//                        fats,
//                        carbohydrates);
//                composition.add(compositionWithAllParameters);
//                weightOfRecipe +=  compositionEntity.getWeight();
//                caloriesOfRecipe += calories ;
//                proteinsOfRecipe += proteins;
//                fatsOfRecipe += fats ;
//                carbohydratesOfRecipe += carbohydrates;
//            }
//            RecipeDTO recipeDTO = new RecipeDTO(townEntity.getUuid(),
//                    townEntity.getDtCreate(),
//                    townEntity.getDtUpdate(),
//                    townEntity.getTitle(),
//                    composition,
//                    weightOfRecipe,
//                    BigDecimal.valueOf(caloriesOfRecipe).setScale(2, RoundingMode.HALF_UP),
//                    BigDecimal.valueOf(proteinsOfRecipe).setScale(2, RoundingMode.HALF_UP),
//                    BigDecimal.valueOf(fatsOfRecipe).setScale(2, RoundingMode.HALF_UP),
//                    BigDecimal.valueOf(carbohydratesOfRecipe).setScale(2, RoundingMode.HALF_UP)
//            );
//            content.add(recipeDTO);
//        }
//        return content;
//    }
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
