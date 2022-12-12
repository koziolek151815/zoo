package piotr.koziol.zoo.animals;

import org.springframework.stereotype.Component;
import piotr.koziol.zoo.entities.animals.Animal;

@Component
public class AnimalMapper {
    public AnimalResponseDto entityToAnimalResponseDto(Animal animal) {
        return AnimalResponseDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .food(animal.getFood())
                .build();
    }
}
