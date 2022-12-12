package piotr.koziol.zoo.unit.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import piotr.koziol.zoo.exceptions.ZoneAlreadyExistsException;
import piotr.koziol.zoo.exceptions.ZoneNotFoundException;
import piotr.koziol.zoo.zones.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

public class ZoneServiceUnitTests {
    @InjectMocks
    ZoneService zoneService;

    @Mock
    ZoneRepository zoneRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = ZoneAlreadyExistsException.class)
    public void createZoneTest() {
        ZoneRequestDto zoneRequestDto = ZoneRequestDto.builder()
                .name("D")
                .build();
        given(zoneRepository.existsZoneByName("D")).willReturn(true);
        zoneService.createZone(zoneRequestDto);

    }

    @Test
    public void getZoneByMostFoodCount() {
        ZoneResponseDto zoneResponseDto = ZoneResponseDto.builder()
                .id(1L)
                .foodCount(20L)
                .animalsCount(1L)
                .name("D")
                .build();
        List<ZoneResponseDto> zoneResponseDtoList = new ArrayList<>();
        zoneResponseDtoList.add(zoneResponseDto);
        given(zoneRepository.findZoneByMostFoodCount()).willReturn(zoneResponseDtoList);
        Assert.assertEquals(20L, zoneService.getZoneByMostFoodCount().getFoodCount().longValue());
    }

    @Test
    public void getZoneByLeastAnimalsCount() {
        ZoneResponseDto zoneResponseDto = ZoneResponseDto.builder()
                .id(1L)
                .foodCount(20L)
                .animalsCount(1L)
                .name("D")
                .build();
        List<ZoneResponseDto> zoneResponseDtoList = new ArrayList<>();
        zoneResponseDtoList.add(zoneResponseDto);
        given(zoneRepository.findZoneByLeastAnimalsCount()).willReturn(zoneResponseDtoList);
        Assert.assertEquals(20L, zoneService.getZoneByLeastAnimalsCount().getFoodCount().longValue());
    }
}
