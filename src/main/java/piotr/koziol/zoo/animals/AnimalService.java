package piotr.koziol.zoo.animals;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.exceptions.ZoneNotFoundException;
import piotr.koziol.zoo.zones.ZoneRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ZoneRepository zoneRepository;
    private final AnimalMapper animalMapper;
    private final AnimalFactory animalFactory;

    @Transactional
    public AnimalResponseDto createAnimalAndAssignToZone(AnimalRequestDto animalRequestDto, String zoneName) {
        Zone zone = zoneRepository.findZoneByName(zoneName).orElseThrow(ZoneNotFoundException::new);
        Animal animal = animalFactory.getAnimal(animalRequestDto.getAnimalType());
        animal.setZone(zone);
        animal.setName(animalRequestDto.getName());
        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.entityToAnimalResponseDto(savedAnimal);
    }

    @Transactional
    public List<AnimalResponseDto> getAnimalsByZoneName(String zoneName) {
        return animalRepository.findAnimalsByZoneName(zoneName).stream()
                .map(animalMapper::entityToAnimalResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public List<AnimalResponseDto> getAnimalsByName(String name) {
        return animalRepository.findAnimalsByName(name).stream()
                .map(animalMapper::entityToAnimalResponseDto).collect(Collectors.toList());
    }
}
