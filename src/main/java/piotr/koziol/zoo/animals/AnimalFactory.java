package piotr.koziol.zoo.animals;

import org.springframework.stereotype.Component;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.entities.animals.Elephant;
import piotr.koziol.zoo.entities.animals.Lion;
import piotr.koziol.zoo.entities.animals.Rabbit;
import piotr.koziol.zoo.exceptions.AnimalTypeNotFoundException;

@Component
public class AnimalFactory {
    public Animal getAnimal(AnimalTypeEnum animalType) {
        if (animalType == AnimalTypeEnum.ELEPHANT) {
            return new Elephant();
        }
        if (animalType == AnimalTypeEnum.LION) {
            return new Lion();
        }
        if (animalType == AnimalTypeEnum.RABBIT) {
            return new Rabbit();
        }
        throw new AnimalTypeNotFoundException();
    }
}
