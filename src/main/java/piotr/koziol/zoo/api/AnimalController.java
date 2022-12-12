package piotr.koziol.zoo.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piotr.koziol.zoo.animals.AnimalRequestDto;
import piotr.koziol.zoo.animals.AnimalResponseDto;
import piotr.koziol.zoo.animals.AnimalService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping("/{zoneName}")
    public ResponseEntity<AnimalResponseDto> createAnimalAndAssignToZone(@RequestBody AnimalRequestDto animalRequestDto, @PathVariable String zoneName) {
        return ResponseEntity.ok(animalService.createAnimalAndAssignToZone(animalRequestDto, zoneName));
    }

    @GetMapping("/byZone/{zoneName}")
    public ResponseEntity<List<AnimalResponseDto>> getAnimalsByZoneName(@PathVariable String zoneName) {
        return ResponseEntity.ok(animalService.getAnimalsByZoneName(zoneName));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<AnimalResponseDto>> getAnimalsByName(@PathVariable String name) {
        return ResponseEntity.ok(animalService.getAnimalsByName(name));
    }
}
