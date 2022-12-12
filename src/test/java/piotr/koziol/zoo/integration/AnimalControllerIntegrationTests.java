package piotr.koziol.zoo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import piotr.koziol.zoo.ZooApplication;
import piotr.koziol.zoo.animals.*;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.zones.ZoneRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ZooApplication.class})
public class AnimalControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AnimalFactory animalFactory;
    @Autowired
    private AnimalRepository animalRepository;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        animalRepository.deleteAll();
        zoneRepository.deleteAll();

        Zone zoneA = this.zoneRepository.save(Zone.builder().name("A").build());
        Zone zoneB = this.zoneRepository.save(Zone.builder().name("B").build());

        Animal elephant = animalFactory.getAnimal(AnimalTypeEnum.ELEPHANT);
        elephant.setName("Don");
        elephant.setZone(zoneA);
        animalRepository.save(elephant);

        Animal rabbit = animalFactory.getAnimal(AnimalTypeEnum.RABBIT);
        rabbit.setName("Don");
        rabbit.setZone(zoneB);
        animalRepository.save(rabbit);
    }

    @Test
    public void createAnimalAndAssignToZoneTest() throws Exception {
        AnimalRequestDto animalRequestDto = AnimalRequestDto.builder()
                .name("John")
                .animalType(AnimalTypeEnum.ELEPHANT)
                .build();
        this.mockMvc.perform(post("/animals/A")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(animalRequestDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.food", is(20)));

        Assert.assertEquals(1, animalRepository.findAnimalsByName("John").size());
        Assert.assertEquals(2, animalRepository.findAnimalsByZoneName("A").size());

    }

    @Test
    public void getAnimalsByZoneNameTest() throws Exception {
        this.mockMvc.perform(get("/animals/byZone/A"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getAnimalsByName() throws Exception {
        this.mockMvc.perform(get("/animals/byName/Don"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
