package piotr.koziol.zoo.animals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piotr.koziol.zoo.entities.animals.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAnimalsByName(String name);

    @Query("select distinct a from animals a join fetch a.zone z where z.name =:zoneName")
    List<Animal> findAnimalsByZoneName(String zoneName);

}
