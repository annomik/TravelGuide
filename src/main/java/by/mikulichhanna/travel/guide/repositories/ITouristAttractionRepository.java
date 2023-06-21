package by.mikulichhanna.travel.guide.repositories;

import by.mikulichhanna.travel.guide.entity.AttractionEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//PagingAndSortingRepository
@Repository
public interface ITouristAttractionRepository extends ListCrudRepository<AttractionEntity, UUID> {

}
