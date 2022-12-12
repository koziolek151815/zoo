package piotr.koziol.zoo.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import piotr.koziol.zoo.ZooApplication;
import piotr.koziol.zoo.animals.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ZooApplication.class})
public class AnimalControllerUnitTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private AnimalService animalService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void createAnimalAndAssignToZoneTest() throws Exception {
        AnimalRequestDto animalRequestDto = AnimalRequestDto.builder()
                .name("John")
                .animalType(AnimalTypeEnum.ELEPHANT)
                .build();
        AnimalResponseDto animalResponseDto = AnimalResponseDto.builder()
                .id(1L)
                .food(20L)
                .name("John")
                .build();

        given(animalService.createAnimalAndAssignToZone(animalRequestDto, "A")).willReturn(animalResponseDto);

        this.mockMvc.perform(post("/animals/A")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(animalRequestDto)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getAnimalsByZoneNameTest() throws Exception {
        AnimalResponseDto animalResponseDto = AnimalResponseDto.builder()
                .id(1L)
                .food(20L)
                .name("John")
                .build();
        List<AnimalResponseDto> animalResponseDtoList = new ArrayList<>();
        animalResponseDtoList.add(animalResponseDto);

        given(animalService.getAnimalsByZoneName("A")).willReturn(animalResponseDtoList);

        this.mockMvc.perform(get("/animals/byZone/A"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getAnimalsByName() throws Exception {
        AnimalResponseDto animalResponseDto = AnimalResponseDto.builder()
                .id(1L)
                .food(20L)
                .name("Don")
                .build();
        List<AnimalResponseDto> animalResponseDtoList = new ArrayList<>();
        animalResponseDtoList.add(animalResponseDto);

        given(animalService.getAnimalsByName("Don")).willReturn(animalResponseDtoList);
        this.mockMvc.perform(get("/animals/byName/Don"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
