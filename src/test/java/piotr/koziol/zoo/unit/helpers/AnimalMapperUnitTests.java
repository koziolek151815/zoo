package piotr.koziol.zoo.unit.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import piotr.koziol.zoo.animals.AnimalFactory;
import piotr.koziol.zoo.animals.AnimalMapper;
import piotr.koziol.zoo.animals.AnimalResponseDto;
import piotr.koziol.zoo.animals.AnimalTypeEnum;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.entities.animals.Elephant;
import piotr.koziol.zoo.zones.ZoneResponseDto;

public class AnimalMapperUnitTests {
    private AnimalMapper animalMapper;

    @Before
    public void setUp() {
        animalMapper = new AnimalMapper();
    }

    @Test
    public void entityToAnimalResponseDtoTest() {
        Animal elephant = new Elephant();
        Zone zone = Zone.builder()
                .id(1L)
                .name("A")
                .build();
        elephant.setId(1L);
        elephant.setName("John");
        elephant.setZone(zone);
        AnimalResponseDto animalResponseDto = animalMapper.entityToAnimalResponseDto(elephant);
        Assert.assertEquals(1L, animalResponseDto.getId().longValue());
        Assert.assertEquals("John", animalResponseDto.getName());
        Assert.assertEquals(20L, animalResponseDto.getFood().longValue());
    }
}
