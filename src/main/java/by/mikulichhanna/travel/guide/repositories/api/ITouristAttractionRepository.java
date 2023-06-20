package by.mikulichhanna.travel.guide.repositories.api;

import by.mikulichhanna.travel.guide.entity.TouristAttractionEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//PagingAndSortingRepository
@Repository
public interface ITouristAttractionRepository extends ListCrudRepository<TouristAttractionEntity, UUID> {

}
