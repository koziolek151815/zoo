package piotr.koziol.zoo.animals;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AnimalRequestDto {
    private String name;
    private AnimalTypeEnum animalType;
}
