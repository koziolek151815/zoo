package piotr.koziol.zoo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piotr.koziol.zoo.zones.ZoneRequestDto;
import piotr.koziol.zoo.zones.ZoneResponseDto;
import piotr.koziol.zoo.zones.ZoneService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zones")
public class ZoneController {
    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ZoneResponseDto> createZone(@RequestBody ZoneRequestDto zoneRequestDto) {
        return ResponseEntity.ok(zoneService.createZone(zoneRequestDto));
    }

    @GetMapping("/zoneMostFoodCountReport")
    public ResponseEntity<ZoneResponseDto> getZoneByMostFoodCount() {
        return ResponseEntity.ok(zoneService.getZoneByMostFoodCount());
    }

    @GetMapping("/zoneLeastAnimalsCountReport")
    public ResponseEntity<ZoneResponseDto> getZoneByLeastAnimalsCount() {
        return ResponseEntity.ok(zoneService.getZoneByLeastAnimalsCount());
    }
}
