package by.mikulichhanna.travel.guide.repositories;

import by.mikulichhanna.travel.guide.entity.TownEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITownRepository extends ListCrudRepository<TownEntity, UUID> {

}
