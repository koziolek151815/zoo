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
import piotr.koziol.zoo.animals.AnimalFactory;
import piotr.koziol.zoo.animals.AnimalRepository;
import piotr.koziol.zoo.animals.AnimalTypeEnum;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.entities.animals.Animal;
import piotr.koziol.zoo.zones.ZoneRepository;
import piotr.koziol.zoo.zones.ZoneRequestDto;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ZooApplication.class})
public class ZoneControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ZoneRepository zoneRepository;
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

        Animal lion = animalFactory.getAnimal(AnimalTypeEnum.LION);
        lion.setName("Don");
        lion.setZone(zoneA);
        animalRepository.save(lion);
    }

    @Test
    public void createZoneTest() throws Exception {
        ZoneRequestDto zoneRequestDto = ZoneRequestDto.builder()
                .name("D")
                .build();
        this.mockMvc.perform(post("/zones")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(zoneRequestDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("D")))
                .andExpect(jsonPath("$.foodCount", is(0)))
                .andExpect(jsonPath("$.animalsCount", is(0)));

        Assert.assertEquals(true, zoneRepository.existsZoneByName("D"));
    }

    @Test
    public void getZoneByMostFoodCountTest() throws Exception {
        this.mockMvc.perform(get("/zones/zoneMostFoodCountReport"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("A")))
                .andExpect(jsonPath("$.foodCount", is(31)));
    }

    @Test
    public void getZoneByLeastAnimalsCountTest() throws Exception {
        this.mockMvc.perform(get("/zones/zoneLeastAnimalsCountReport"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("B")))
                .andExpect(jsonPath("$.animalsCount", is(1)));
    }
}
