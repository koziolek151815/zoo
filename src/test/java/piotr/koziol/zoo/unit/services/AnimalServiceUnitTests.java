package piotr.koziol.zoo.unit.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import piotr.koziol.zoo.animals.*;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.entities.animals.Elephant;
import piotr.koziol.zoo.exceptions.ZoneNotFoundException;
import piotr.koziol.zoo.zones.ZoneRepository;
import piotr.koziol.zoo.zones.ZoneResponseDto;
import piotr.koziol.zoo.zones.ZoneService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class AnimalServiceUnitTests {
    @InjectMocks
    AnimalService animalService;
    @Mock
    ZoneRepository zoneRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = ZoneNotFoundException.class)
    public void createAnimalAndAssignToZoneTest() {
        AnimalRequestDto animalRequestDto = AnimalRequestDto.builder()
                .name("John")
                .animalType(AnimalTypeEnum.ELEPHANT)
                .build();
        given(zoneRepository.findZoneByName("A")).willReturn(Optional.empty());
        animalService.createAnimalAndAssignToZone(animalRequestDto, "A");
    }

}
