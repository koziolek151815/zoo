package piotr.koziol.zoo.zones;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import piotr.koziol.zoo.entities.Zone;
import piotr.koziol.zoo.exceptions.ZoneAlreadyExistsException;
import piotr.koziol.zoo.exceptions.ZoneNotFoundException;

@RequiredArgsConstructor
@Service
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Transactional
    public ZoneResponseDto createZone(ZoneRequestDto zoneRequestDto) {
        if (zoneRepository.existsZoneByName(zoneRequestDto.getName())) {
            throw new ZoneAlreadyExistsException();
        }
        Zone zone = zoneRepository.save(zoneMapper.zoneRequestDtoToEntity(zoneRequestDto));
        return zoneMapper.entityToZoneResponseDto(zone);
    }

    @Transactional
    public ZoneResponseDto getZoneByMostFoodCount() {
        return zoneRepository.findZoneByMostFoodCount().stream().findFirst().map(this::updateFoodCountIfNull).orElseThrow(ZoneNotFoundException::new);
    }

    @Transactional
    public ZoneResponseDto getZoneByLeastAnimalsCount() {
        return zoneRepository.findZoneByLeastAnimalsCount().stream().findFirst().map(this::updateFoodCountIfNull).orElseThrow(ZoneNotFoundException::new);
    }

    private ZoneResponseDto updateFoodCountIfNull(ZoneResponseDto zoneResponseDto) {
        if (zoneResponseDto.getFoodCount() == null) {
            zoneResponseDto.setFoodCount(0L);
        }
        return zoneResponseDto;
    }
}
