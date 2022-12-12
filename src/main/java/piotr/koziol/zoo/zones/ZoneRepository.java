package piotr.koziol.zoo.zones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piotr.koziol.zoo.entities.Zone;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Optional<Zone> findZoneByName(String name);

    Boolean existsZoneByName(String name);

    @Query("select new piotr.koziol.zoo.zones.ZoneResponseDto(z.id, z.name, count(a.id), sum(a.food))from zones z left outer join animals a group by z.id order by count(a.id) asc")
    List<ZoneResponseDto> findZoneByLeastAnimalsCount();

    @Query("select new piotr.koziol.zoo.zones.ZoneResponseDto(z.id, z.name, count(a.id), sum(a.food)) from zones z left outer join animals a group by z.id order by count(a.food) desc")
    List<ZoneResponseDto> findZoneByMostFoodCount();
}
