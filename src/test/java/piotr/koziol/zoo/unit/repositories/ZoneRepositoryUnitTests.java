package piotr.koziol.zoo.unit.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import piotr.koziol.zoo.animals.AnimalRepository;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.entities.animals.Elephant;
import piotr.koziol.zoo.zones.ZoneRepository;
import piotr.koziol.zoo.zones.ZoneResponseDto;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ZoneRepositoryUnitTests {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Before
    public void setUp() {
        Zone zoneA = this.zoneRepository.save(Zone.builder().name("A").build());
        this.zoneRepository.save(Zone.builder().name("B").build());
        Animal elephant = new Elephant();
        elephant.setName("Don");
        elephant.setZone(zoneA);
        animalRepository.save(elephant);
    }

    @Test
    public void findZoneByNameTest() {
        Assert.assertEquals("A", zoneRepository.findZoneByName("A").map(Zone::getName).orElse(""));
    }

    @Test
    public void existsZoneByNameTest() {
        Assert.assertEquals(true, zoneRepository.existsZoneByName("A"));
    }

    @Test
    public void findZoneByLeastAnimalsCountTest() {
        ZoneResponseDto zoneResponseDto = zoneRepository.findZoneByLeastAnimalsCount().get(0);
        Assert.assertEquals("B", zoneResponseDto.getName());
        Assert.assertEquals(0, zoneResponseDto.getAnimalsCount().longValue());
    }

    @Test
    public void findZoneByMostFoodCount() {
        ZoneResponseDto zoneResponseDto = zoneRepository.findZoneByMostFoodCount().get(0);
        Assert.assertEquals("A", zoneResponseDto.getName());
        Assert.assertEquals(1, zoneResponseDto.getAnimalsCount().longValue());
    }
}
