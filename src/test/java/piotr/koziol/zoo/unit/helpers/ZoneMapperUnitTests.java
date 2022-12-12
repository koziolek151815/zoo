package piotr.koziol.zoo.unit.helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.zones.ZoneMapper;
import piotr.koziol.zoo.zones.ZoneRequestDto;
import piotr.koziol.zoo.zones.ZoneResponseDto;

import java.util.HashSet;

public class ZoneMapperUnitTests {

    private ZoneMapper zoneMapper;

    @Before
    public void setUp() {
        zoneMapper = new ZoneMapper();
    }

    @Test
    public void zoneRequestDtoToEntityTest() {
        ZoneRequestDto zoneRequestDto = ZoneRequestDto.builder()
                .name("A")
                .build();
        Zone zone = zoneMapper.zoneRequestDtoToEntity(zoneRequestDto);
        Assert.assertNull(zone.getId());
        Assert.assertEquals("A", zoneRequestDto.getName());
    }

    @Test
    public void entityToZoneResponseDtoTest() {
        Zone zone = Zone.builder()
                .id(1L)
                .name("A")
                .animals(new HashSet<>())
                .build();
        ZoneResponseDto zoneResponseDto = zoneMapper.entityToZoneResponseDto(zone);
        Assert.assertEquals(1L, zoneResponseDto.getId().longValue());
        Assert.assertEquals("A", zoneResponseDto.getName());
        Assert.assertEquals(0L, zoneResponseDto.getFoodCount().longValue());
        Assert.assertEquals(0L, zoneResponseDto.getAnimalsCount().longValue());
    }
}
