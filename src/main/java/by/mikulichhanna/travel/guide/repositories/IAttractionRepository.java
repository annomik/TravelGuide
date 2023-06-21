package by.mikulichhanna.travel.guide.repositories;

import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import by.mikulichhanna.travel.guide.entity.TownEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//PagingAndSortingRepository
@Repository
public interface IAttractionRepository extends ListCrudRepository<AttractionEntity, UUID> {
    Page<AttractionEntity> findAll(Pageable pageable);
}
