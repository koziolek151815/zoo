package piotr.koziol.zoo.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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

import piotr.koziol.zoo.zones.ZoneRequestDto;
import piotr.koziol.zoo.zones.ZoneResponseDto;
import piotr.koziol.zoo.zones.ZoneService;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ZooApplication.class})
public class ZoneControllerUnitTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ZoneService zoneService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void createZoneTest() throws Exception {
        ZoneRequestDto zoneRequestDto = ZoneRequestDto.builder()
                .name("D")
                .build();
        ZoneResponseDto zoneResponseDto = ZoneResponseDto.builder()
                .id(1L)
                .foodCount(0L)
                .animalsCount(0L)
                .name("D")
                .build();
        given(zoneService.createZone(zoneRequestDto)).willReturn(zoneResponseDto);
        this.mockMvc.perform(post("/zones")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(zoneRequestDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void getZoneByMostFoodCountTest() throws Exception {
        ZoneResponseDto zoneResponseDto = ZoneResponseDto.builder()
                .id(1L)
                .foodCount(20L)
                .animalsCount(1L)
                .name("D")
                .build();
        given(zoneService.getZoneByMostFoodCount()).willReturn(zoneResponseDto);
        this.mockMvc.perform(get("/zones/zoneMostFoodCountReport"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("D")))
                .andExpect(jsonPath("$.foodCount", is(20)))
                .andExpect(jsonPath("$.animalsCount", is(1)));
    }

    @Test
    public void getZoneByLeastAnimalsCountTest() throws Exception {
        ZoneResponseDto zoneResponseDto = ZoneResponseDto.builder()
                .id(1L)
                .foodCount(0L)
                .animalsCount(0L)
                .name("D")
                .build();
        given(zoneService.getZoneByLeastAnimalsCount()).willReturn(zoneResponseDto);
        this.mockMvc.perform(get("/zones/zoneLeastAnimalsCountReport"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("D")))
                .andExpect(jsonPath("$.foodCount", is(0)))
                .andExpect(jsonPath("$.animalsCount", is(0)));
    }
}
