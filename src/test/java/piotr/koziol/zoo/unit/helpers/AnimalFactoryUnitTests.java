package piotr.koziol.zoo.unit.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import piotr.koziol.zoo.animals.AnimalFactory;
import piotr.koziol.zoo.animals.AnimalTypeEnum;
import piotr.koziol.zoo.entities.animals.Animal;

public class AnimalFactoryUnitTests {
    private AnimalFactory animalFactory;

    @Before
    public void setUp() {
        animalFactory = new AnimalFactory();
    }

    @Test
    public void getAnimalTest() {
        Animal lion = animalFactory.getAnimal(AnimalTypeEnum.LION);
        Assert.assertEquals(11L, lion.getFood().longValue());
    }
}
