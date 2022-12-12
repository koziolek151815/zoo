package piotr.koziol.zoo.zones;

import org.springframework.stereotype.Component;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;

@Component
public class ZoneMapper {
    public Zone zoneRequestDtoToEntity(ZoneRequestDto zoneRequestDto) {
        return Zone.builder()
                .name(zoneRequestDto.getName())
                .build();
    }

    public ZoneResponseDto entityToZoneResponseDto(Zone zone) {
        return ZoneResponseDto.builder()
                .id(zone.getId())
                .name(zone.getName())
                .animalsCount(zone.getAnimals() != null ? zone.getAnimals().size() : 0L)
                .foodCount(zone.getAnimals() != null ? zone.getAnimals().stream().map(Animal::getFood).mapToLong(Long::longValue).sum() : 0L)
                .build();
    }
}
