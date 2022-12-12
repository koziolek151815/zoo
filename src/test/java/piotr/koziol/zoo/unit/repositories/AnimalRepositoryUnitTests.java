package piotr.koziol.zoo.unit.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import piotr.koziol.zoo.animals.AnimalFactory;
import piotr.koziol.zoo.animals.AnimalRepository;
import piotr.koziol.zoo.animals.AnimalTypeEnum;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.entities.animals.Elephant;
import piotr.koziol.zoo.zones.ZoneRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalRepositoryUnitTests {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Before
    public void setUp() {
        Zone zoneA = this.zoneRepository.save(Zone.builder().name("A").build());

        Animal elephant = new Elephant();
        elephant.setName("Don");
        elephant.setZone(zoneA);
        animalRepository.save(elephant);
    }

    @Test
    public void findAnimalsByZoneNameTest() {
        Assert.assertEquals(1, animalRepository.findAnimalsByZoneName("A").size());
    }

    @Test
    public void findAnimalsByNameTest() {
        Assert.assertEquals(1, animalRepository.findAnimalsByName("Don").size());
    }
}
