package by.mikulichhanna.travel.guide.service;

import by.mikulichhanna.travel.guide.core.dto.PageDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionWithTownDTO;
import by.mikulichhanna.travel.guide.core.exception.MultipleErrorResponse;
import by.mikulichhanna.travel.guide.core.exception.SingleErrorResponse;
import by.mikulichhanna.travel.guide.core.exception.Error;
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
        validate(attractionDTO);
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

    public AttractionWithTownDTO getCard(UUID uuid){

        Optional<AttractionEntity> findEntity = attractionRepository.findById(uuid);
        if(findEntity.isEmpty()){
            throw new SingleErrorResponse("Достопримечательности с id " + uuid + " не найдено.");
        }
        AttractionEntity userEntity = findEntity.get();
        return conversionService.convert(userEntity, AttractionWithTownDTO.class);
    }

    public void delete(UUID uuid) {
        if(uuid == null ){
            throw new SingleErrorResponse("Введите параметры для удаления");
        }
        Optional<AttractionEntity> findEntity = attractionRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Достопримечательности с id " + uuid + " для обновления не найдено");
        } else {
            attractionRepository.deleteById(uuid);
        }
    }

    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, AttractionDTO attractionDTO) {
        if(uuid == null || dtUpdate == null){
            throw new SingleErrorResponse("Введите параметры для обновления");
        }
        validate(attractionDTO);
        Optional<AttractionEntity> findEntity = attractionRepository.findById(uuid);
        if (!findEntity.isPresent()) {
            throw new SingleErrorResponse("Достопримечательности с id " + uuid + " для обновления не найдено");
        } else {
            AttractionEntity entity = findEntity.get();
            if (entity.getDtUpdate().isEqual(dtUpdate) && entity.getUuid().equals(uuid)) {
                entity.setName(attractionDTO.getName());
                entity.setAddress(attractionDTO.getAddress());

                attractionRepository.save(entity);
            } else {
                throw new SingleErrorResponse("Версии достопримечательности с id " + uuid + " не совпадают!");
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
    public void validate(AttractionDTO attractionDTO)  {
        MultipleErrorResponse multipleErrorResponse = new MultipleErrorResponse();

        if (attractionDTO.getName() == null || attractionDTO.getName().isBlank()){
            multipleErrorResponse.setErrors(new Error("Name", "Поле не заполнено"));
        }
        if (attractionDTO.getAddress() == null || attractionDTO.getAddress().isBlank()){
            multipleErrorResponse.setErrors(new Error("Address", "Поле не заполнено"));
        }
        if (attractionDTO.getTownUUID()== null ) {
            multipleErrorResponse.setErrors(new Error("TownUUID", "Поле не заполнено"));
        }

        if (!multipleErrorResponse.getErrors().isEmpty()) {
            throw multipleErrorResponse;
        }
    }
}
